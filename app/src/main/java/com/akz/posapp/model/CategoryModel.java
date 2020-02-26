package com.akz.posapp.model;

public class CategoryModel {
 public    int Id,ColorId;
    public String Name;

    public CategoryModel() {
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

    public CategoryModel(int id, int colorId, String name) {
        Id = id;
        ColorId = colorId;
        Name = name;
    }
}
