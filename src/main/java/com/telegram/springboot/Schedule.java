package com.telegram.springboot;

import javax.persistence.*;
import java.sql.*;

@Entity
@Table(name = "tbl_schedule")
public class Schedule {

    @Id
    @GeneratedValue
    public Integer id;

    @Column
    public String group_;

    @Column
    public String data_;

    @Column
    public String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup() {
        return group_;
    }

    public void setGroup(String group_) {
        this.group_ = group_;
    }

    public String getData() {
        return data_;
    }

    public void setData(String data_) {
        this.data_ = data_;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", group='" + group_ + '\'' +
                ", data='" + data_ + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
