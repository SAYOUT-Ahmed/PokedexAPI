package com.example.pokdexahmed.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {
    private int number;

    private String height;
    private String weight;

    @SerializedName("stats")
    private List<Stat> stats;

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String urlParts[] = url.split("/");
        return Integer.parseInt( urlParts[urlParts.length-1] );
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
