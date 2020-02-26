package com.akz.posapp.model;

public class ColorModel {
    public int Id,ColorRed,ColorGreen,ColorBlue;

    public ColorModel(int id, int colorRed, int colorGreen, int colorBlue) {
        Id = id;
        ColorRed = colorRed;
        ColorGreen = colorGreen;
        ColorBlue = colorBlue;
    }

    public ColorModel() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getColorRed() {
        return ColorRed;
    }

    public void setColorRed(int colorRed) {
        ColorRed = colorRed;
    }

    public int getColorGreen() {
        return ColorGreen;
    }

    public void setColorGreen(int colorGreen) {
        ColorGreen = colorGreen;
    }

    public int getColorBlue() {
        return ColorBlue;
    }

    public void setColorBlue(int colorBlue) {
        ColorBlue = colorBlue;
    }
}
