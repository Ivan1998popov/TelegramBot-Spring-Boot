package com.telegram.springboot.Json;

public class Clouds {
    public double all;



    public Clouds(double all) {
        this.all = all;
    }

    public double getAll() {
        return all;
    }

    public void setAll(double all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
