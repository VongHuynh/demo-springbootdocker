package com.example.springbootdocker.dto;

import java.io.Serializable;

public class YourResponse implements Serializable {
    private String name;

    public YourResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
