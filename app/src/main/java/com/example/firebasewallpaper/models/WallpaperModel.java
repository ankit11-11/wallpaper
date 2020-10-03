package com.example.firebasewallpaper.models;

import java.util.ArrayList;

public class WallpaperModel {

    private Brand brand;

    public WallpaperModel() {
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public static class Brand {
        private ArrayList<String> url;
        private String model;
        private String info;
        private String brandName;

        public Brand() {
        }

        public String getBrandName() {
            return brandName;
        }

        public String getInfo() {
            return info;
        }

        public ArrayList<String> getUrl() {
            return url;
        }

        public String getModel() {
            return model;
        }

    }
}
