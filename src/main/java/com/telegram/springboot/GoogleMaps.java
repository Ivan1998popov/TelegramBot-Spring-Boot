package com.telegram.springboot;

import javax.persistence.*;
import java.awt.*;
import java.sql.Types;
import java.util.Arrays;

@Entity
@Table(name = "tbl_google_img")
public class GoogleMaps {

    @Id
    @GeneratedValue
    public Integer id;

    @Column( columnDefinition = "varbinary(MAX)")
    public byte[] image;

    @Column
    public String url;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "GoogleMaps{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                ", url='" + url + '\'' +
                '}';
    }
}
