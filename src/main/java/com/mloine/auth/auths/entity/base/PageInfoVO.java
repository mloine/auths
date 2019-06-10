package com.mloine.auth.auths.entity.base;

import io.swagger.annotations.ApiModelProperty;

/**
 * Author     : Luda Zhuang
 * Date       : 2017/12/16
 * Version    :
 * Description:
 */
public class PageInfoVO {
    @ApiModelProperty(value = "页数, 从 1 开始计数, 小于第一页的将返回第一页数据, 大于最后一页的将返回最后一页的数据, 默认值为 1")
    private int pageNum = 1;
    @ApiModelProperty(value = "每页数量, 默认值为 10")
    private int pageSize = 10;

    public PageInfoVO() {}

    public PageInfoVO(int pageNum, int pageSize) {
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

    @Override
    public String toString() {
        return "PageInfoVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
