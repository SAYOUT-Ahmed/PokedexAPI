package com.example.pokdexahmed.models;

import com.google.gson.annotations.SerializedName;

public class StatInfo {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
