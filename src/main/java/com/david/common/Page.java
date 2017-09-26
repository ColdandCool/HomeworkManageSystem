package com.david.common;

import com.david.common.config.JConfig;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Paging tools
 *
 * @author David
 */
public class Page<T> {

    private int pageNo = 0;
    private int pageSize = Integer.valueOf(JConfig.getConfig("page.pageSize"));
    private int pageNumber = 1;
    private int total = 0;

    private int first;
    private int last;
    private int prev;
    private int next;

    private boolean hasFirstPage;
    private boolean hasLastPage;

    private List<T> list = Lists.newArrayList();
    private T entity;

    private String orderBy = "a.id desc";
    private String key = "";

    /**
     * init method
     */
    public Page() {
        initialize();
    }

    /**
     * init method
     *
     * @param pageNo   pageNo
     * @param pageSize pageSize
     */
    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        initialize();
    }

    /**
     * init method
     *
     * @param pageNo   pageNo
     * @param pageSize pageSize
     * @param total    total
     * @param list     list
     */
    public Page(int pageNo, int pageSize, int total, List<T> list) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
        initialize();
    }

    /**
     * init config
     */
    public void initialize() {
        this.first = 0;
        this.last = (int) (total / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);
        this.pageNumber = (total + this.pageSize - 1) / pageSize;
        if (this.total % this.pageSize != 0 || this.last == 0) {
            this.last++;
        }

        if (this.last < this.first) {
            this.last = this.first;
        }

        if (this.pageNo <= 0) {
            this.pageNo = this.first;
            this.hasFirstPage = true;
        } else {
            this.hasFirstPage = false;
        }

        if (this.pageNo >= this.last) {
            this.pageNo = this.last;
            this.hasLastPage = true;
        } else {
            this.hasLastPage = false;
        }

        if (this.pageNo < this.last - 1) {
            this.next = this.pageNo + 1;
        } else {
            this.next = this.last;
        }

        if (this.pageNo > 1) {
            this.prev = this.pageNo - 1;
        } else {
            this.prev = this.first;
        }

        if (this.pageNo < this.first) {
            this.pageNo = this.first;
        }

        if (this.pageNo > this.last) {
            this.pageNo = this.last;
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isHasFirstPage() {
        return hasFirstPage;
    }

    public void setHasFirstPage(boolean hasFirstPage) {
        this.hasFirstPage = hasFirstPage;
    }

    public boolean isHasLastPage() {
        return hasLastPage;
    }

    public void setHasLastPage(boolean hasLastPage) {
        this.hasLastPage = hasLastPage;
    }

    public List<T> getList() {
        return list;
    }

    public Page<T> setList(List<T> list) {
        this.list = list;
        initialize();
        return this;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public String getOrderBy() {
        // SQL inject
        String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|" + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
        Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        if (sqlPattern.matcher(orderBy).find()) {
            return "";
        }
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
