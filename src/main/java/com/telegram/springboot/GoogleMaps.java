package com.telegram.springboot;

import javax.persistence.*;



@Entity
@Table(name = "tbl_google_img")
public class GoogleMaps {

    @Id
    @GeneratedValue
    public Integer id;

    @Column
    public String url;

    @Column
    public String index;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {

        this.index = index;
    }

    @Override
    public String toString() {
        return "GoogleMaps{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
