package com.akz.posapp.model;

public class BrandModel {
   public int Id,ColorId;
   public String Name;

    public BrandModel() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getColorId() {
        return ColorId;
    }

    public void setColorId(int colorId) {
        ColorId = colorId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BrandModel(int id, int colorId, String name) {
        Id = id;
        ColorId = colorId;
        Name = name;
    }
}
