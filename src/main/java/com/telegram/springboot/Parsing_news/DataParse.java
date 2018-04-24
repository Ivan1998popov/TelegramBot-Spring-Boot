package com.telegram.springboot.Parsing_news;

public class DataParse {

    private String url;
    private String name;

    public DataParse(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataParse{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
