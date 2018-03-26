package com.telegram.springboot;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name = "tbl_google_img")
public class GoogleMaps {

    @Id
    @GeneratedValue
    public Integer id;

    @Column
    public byte[] image;

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
                ", image=" + image +
                '}';
    }
}
