package com.cyt.domain;

import java.util.List;
import java.util.Map;

/**
 * 由多个对象组成一个查询条件
 */
public class QueryVo {
    private User user;
    private List<Integer> ids;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
