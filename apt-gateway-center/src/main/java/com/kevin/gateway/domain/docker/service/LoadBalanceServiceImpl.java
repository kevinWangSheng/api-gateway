package com.kevin.gateway.domain.docker.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.kevin.gateway.domain.docker.model.aggregates.NginxConfig;
import com.kevin.gateway.domain.docker.model.vo.LocationVO;
import com.kevin.gateway.domain.docker.model.vo.UpstreamVO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2024-01-07-13:40
 */
public class LoadBalanceServiceImpl extends AbstractLoadBalanceService{
    @Override
    protected String createNginxConfigFile(NginxConfig nginxConfig) throws IOException {
        String buildNginxConfigStr = buildNginxConfig(nginxConfig.getUpstreamList(), nginxConfig.getLocationList());
        File file = new File("/data/nginx/nginx.conf");
        if(!file.exists()){
            boolean success = file.createNewFile();
            if(success){
                logger.info("创建nginx配置文件成功");
            }else{
                logger.error("创建nginx配置文件失败");
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(buildNginxConfigStr);
        fileWriter.close();
        return file.getAbsolutePath();
    }

    @Override
    protected void copyDockerFile(String applicationName, String containerFilePath, String localNginxPath) throws InterruptedException, IOException {

    }

    @Override
    protected void reflushNginxConfig(String nginxName) throws InterruptedException, IOException {
        DockerClientConfig clientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerConfig("unix:///var/run/docker.sock").build();

        DockerClient client = DockerClientBuilder.getInstance(clientConfig).build();
        // 获取容器ID
        client.listContainersCmd().withNameFilter(new ArrayList<String>() {{
            add(nginxName);
        }}).exec().get(0).getId();
        ExecCreateCmdResponse response = client.execCreateCmd("nginx").withCmd("nginx", "-s", "reload").exec();

        client.execStartCmd(response.getId()).exec(new ResultCallback.Adapter<>()).awaitCompletion();
        client.close();
    }

    private String buildNginxConfig(List<UpstreamVO> upstreamList, List<LocationVO> locationList){
        String config = "\n" +
                "user  nginx;\n" +
                "worker_processes  auto;\n" +
                "\n" +
                "error_log  /var/log/nginx/error.log notice;\n" +
                "pid        /var/run/nginx.pid;\n" +
                "\n" +
                "\n" +
                "events {\n" +
                "    worker_connections  1024;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "http {\n" +
                "    include       /etc/nginx/mime.types;\n" +
                "    default_type  application/octet-stream;\n" +
                "\n" +
                "    log_format  main  '$remote_addr - $remote_user [$time_local] \"$request\" '\n" +
                "                      '$status $body_bytes_sent \"$http_referer\" '\n" +
                "                      '\"$http_user_agent\" \"$http_x_forwarded_for\"';\n" +
                "\n" +
                "    access_log  /var/log/nginx/access.log  main;\n" +
                "\n" +
                "    sendfile        on;\n" +
                "    #tcp_nopush     on;\n" +
                "\n" +
                "    keepalive_timeout  65;\n" +
                "\n" +
                "    #gzip  on;\n" +
                "\n" +
                "    include /etc/nginx/conf.d/*.conf;\n" +
                "\n" +
                "    # 设定负载均衡的服务器列表\n" +
                "upstream_config_placeholder" +
                "\n" +
                "    # HTTP服务器\n" +
                "    server {\n" +
                "        # 监听80端口，用于HTTP协议\n" +
                "        listen  80;\n" +
                "\n" +
                "        # 定义使用IP/域名访问\n" +
                "        server_name 192.168.1.102;\n" +
                "\n" +
                "        # 首页\n" +
                "        index index.html;\n" +
                "\n" +
                "        # 反向代理的路径（upstream绑定），location 后面设置映射的路径\n" +
                "        location / {\n" +
                "            proxy_pass http://192.168.1.102:9001;\n" +
                "        }\n" +
                "\n" +
                "location_config_placeholder" +
                "    }\n" +
                "}\n";

        // 组装配置 Upstream
        StringBuilder upstreamStr = new StringBuilder();
        for (UpstreamVO upstream : upstreamList) {
            upstreamStr.append("\t").append("upstream").append(" ").append(upstream.getName()).append(" {\r\n");
            upstreamStr.append("\t").append("\t").append(upstream.getStrategy()).append("\r\n").append("\r\n");
            List<String> servers = upstream.getServers();
            for (String server : servers) {
                upstreamStr.append("\t").append("\t").append("server").append(" ").append(server).append("\r\n");
            }
            upstreamStr.append("\t").append("}").append("\r\n").append("\r\n");
        }

        // 组装配置 Location
        StringBuilder locationStr = new StringBuilder();
        for (LocationVO location : locationList) {
            locationStr.append("\t").append("\t").append("location").append(" ").append(location.getName()).append(" {\r\n");
            locationStr.append("\t").append("\t").append("\t").append("proxy_pass").append(" ").append(location.getProxy_pass()).append("\r\n");
            locationStr.append("\t").append("\t").append("}").append("\r\n").append("\r\n");
        }

        // 替换配置
        config = config.replace("upstream_config_placeholder", upstreamStr.toString());
        config = config.replace("location_config_placeholder", locationStr.toString());
        return config;
    }
}
