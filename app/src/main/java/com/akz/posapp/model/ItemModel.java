package com.akz.posapp.model;

public class ItemModel {
    public int Id,ColorId,CategoryId,BrandId,
                UnitId,OriginalPrice,SalePrice;
    public String Name,Picture;

    public ItemModel(int id, int colorId, int categoryId, int brandId, int unitId, int originalPrice, int salePrice, String name, String picture) {
        Id = id;
        ColorId = colorId;
        CategoryId = categoryId;
        BrandId = brandId;
        UnitId = unitId;
        OriginalPrice = originalPrice;
        SalePrice = salePrice;
        Name = name;
        Picture = picture;
    }

    public ItemModel() {
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

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public int getBrandId() {
        return BrandId;
    }

    public void setBrandId(int brandId) {
        BrandId = brandId;
    }

    public int getUnitId() {
        return UnitId;
    }

    public void setUnitId(int unitId) {
        UnitId = unitId;
    }

    public int getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        OriginalPrice = originalPrice;
    }

    public int getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(int salePrice) {
        SalePrice = salePrice;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }
}
