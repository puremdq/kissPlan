package com.aojiaoo.core.mybatis.plugins.paging;

import java.util.List;

/**
 * Page 参数信息
 *
 * @author puremdq
 */
public class Page<T> {

    private int offset = 0;//增加偏移量
    private int pageSize = 10;
    private int pageNo = 1;
    private int total;//在查询后自动绑定进去
    private List<T> list;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int totalSize) {
        this.total = totalSize;
    }

    public int getTotalPageNum() {
        if (pageSize <= 0) {
            return 0;
        } else {
            return total / pageSize + 1;
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "offset=" + offset +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}
