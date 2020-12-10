package com.quy.mockapi.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Work {
    private String id;
    private String name;
    private long create;
    private boolean complete;

    public Work(String id, String name, long create, boolean complete) {
        this.id = id;
        this.name = name;
        this.create = create;
        this.complete = complete;
    }

    public Work(String name, long create, boolean complete) {
        this.name = name;
        this.create = create;
        this.complete = complete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreate() {
        return create;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", create=" + create +
                ", complete=" + complete +
                '}';
    }
}
