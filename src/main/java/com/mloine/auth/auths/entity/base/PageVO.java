package com.mloine.auth.auths.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.Page;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author     : Luda Zhuang
 * Date       : 2017/12/16
 * Version    :
 * Description:
 */
public class PageVO<T> {
    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 起始行
     */
    @JsonIgnore
    private int startRow;
    /**
     * 末行
     */
    @JsonIgnore
    private int endRow;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;

    /**
     * 数据行
     */
    private List<T> rows;

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

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public PageVO() {
    }

    public PageVO(Page<T> page) {
        loadFromPage(page);
    }

    public PageVO(List<T> list) {
        if (list instanceof Page) {
            loadFromPage((Page<T>) list);
        } else {
            this.rows = list;
        }
    }

    private void loadFromPage(Page<T> page) {
        this.setPageNum(page.getPageNum());
        this.setPageSize(page.getPageSize());
        this.setStartRow(page.getStartRow());
        this.setEndRow(page.getEndRow());
        this.setTotal(page.getTotal());
        this.setPages(page.getPages());
        this.setRows(page);
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                ", total=" + total +
                ", pages=" + pages +
                ", rows=" + rows +
                '}';
    }

    public <D extends T> PageVO<D> changeTypeTo(Class<D> t) {
        PageVO<D> newPage = new PageVO<>();
        newPage.setPageNum(this.getPageNum());
        newPage.setPageSize(this.getPageSize());
        newPage.setStartRow(this.getStartRow());
        newPage.setEndRow(this.getEndRow());
        newPage.setTotal(this.getTotal());
        newPage.setPages(this.getPages());

        newPage.rows = new ArrayList<>();

        if (this.rows != null && !this.rows.isEmpty()) {
            this.rows.forEach(row -> {
                try {
                    D instance = t.newInstance();
                    BeanUtils.copyProperties(instance, row);
                    newPage.rows.add(instance);
                } catch (Exception e) {
                    ReflectionUtils.rethrowRuntimeException(e);
                }
            });
        }

        return newPage;
    }
}
