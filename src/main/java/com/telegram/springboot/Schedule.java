package com.telegram.springboot;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_schedule")
public class Schedule {

    private String group_;
    private String day_;
    private String schedule;

    public Schedule(){

    }
    public Schedule (String group,String day,String schedule){
        this.group_=group;
        this.day_=day;
        this.schedule=schedule;
    }

    public String getGroup() {
        return group_;
    }

    public void setGroup(String group) {
        this.group_ = group;
    }

    public String getDay() {
        return day_;
    }

    public void setDay(String day) {
        this.day_ = day;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    @Override
    public String toString() {
        return "Schedule{" +
                "group=" + group_ +
                ", day='" + day_ + '\'' +
                ", schedule=" + schedule +
                '}';
    }
}
