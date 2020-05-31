package com.ego.commons;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
    private List<T> rows;
    private long total;

    public List<T> getRows() {
        return this.rows;
    }

    public void setRows(final List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(final long total) {
        this.total = total;
    }
}
