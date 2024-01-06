package com.kevin.gateway.infrustructs.common;

/**
 * @author wang
 * @create 2024-01-06-17:08
 */
public class PageRequest {
    private int pageNum;

    private int pageSize;

    private int pageStart;

    private int pageEnd;

    public PageRequest() {
        this.pageSize = 10;
        this.pageNum = 0;
        this.pageStart = pageNum;
        this.pageEnd = pageSize;
    }

    public PageRequest(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageStart(){
        this.pageStart = (this.pageNum) * this.pageSize;
    }

    public void setPageEnd(){
        this.pageEnd = (this.pageNum + 1) * this.pageSize;
    }

    public int getPageStart() {
        return pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }
}
