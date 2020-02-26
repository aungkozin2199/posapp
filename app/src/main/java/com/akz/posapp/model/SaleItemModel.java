package com.akz.posapp.model;

public class SaleItemModel {
    public static int Id,Sr,OrderId,SPrice,OPrice,Qty,ItemId;

    public SaleItemModel(int id, int sr, int orderId, int SPrice, int OPrice, int qty) {
        Id = id;
        Sr = sr;
        OrderId = orderId;
        this.SPrice = SPrice;
        this.OPrice = OPrice;
        this.Qty = qty;
    }

    public SaleItemModel() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSr() {
        return Sr;
    }

    public void setSr(int sr) {
        Sr = sr;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getSPrice() {
        return SPrice;
    }

    public void setSPrice(int SPrice) {
        this.SPrice = SPrice;
    }

    public int getOPrice() {
        return OPrice;
    }

    public void setOPrice(int OPrice) {
        this.OPrice = OPrice;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }
}
