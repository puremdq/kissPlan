package com.aojiaoo.core.mybatis.plugins.paging;

import java.util.List;

/**
 * Page 参数信息
 *
 * @author puremdq
 */
public class Page<T> {

    private int extraOffset = 0;//手动设置 额外的偏移量
    private int pageSize = 10;
    private int pageNo = 1;
    private int total;//在查询后自动绑定进去
    private List<T> list;

    public int getExtraOffset() {
        return extraOffset;
    }

    public void setExtraOffset(int extraOffset) {
        this.extraOffset = extraOffset;
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


    //得到该分页总的偏移量
    public int getOffset() {
        return (this.pageNo - 1) * pageSize + extraOffset;
    }


    @Override
    public String toString() {
        return "Page{" +
                "extraOffset=" + extraOffset +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}
