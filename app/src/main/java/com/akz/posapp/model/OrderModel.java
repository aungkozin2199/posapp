package com.akz.posapp.model;

public class OrderModel {
    public  int Id,Sr,Total,CustomerId;
    public String OrderDate,OrderNo;

    public OrderModel(int id, int sr, int total, int customerId, String orderDate, String orderNo) {
        Id = id;
        Sr = sr;
        Total = total;
        CustomerId = customerId;
        OrderDate = orderDate;
        OrderNo = orderNo;
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

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public OrderModel() {
    }
}
