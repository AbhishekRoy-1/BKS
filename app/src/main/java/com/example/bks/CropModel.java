package com.example.bks;

public class CropModel {
    private String crop_name;
    private String crop_image;

    public CropModel(String crop_name, String crop_image) {
        this.crop_name = crop_name;
        this.crop_image = crop_image;
    }



    public String getCrop_name() {
        return crop_name;
    }

    public void setCrop_name(String crop_name) {
        this.crop_name = crop_name;
    }

    public String getCrop_image() {
        return crop_image;
    }

    public void setCrop_image(String crop_image) {
        this.crop_image = crop_image;
    }
}
