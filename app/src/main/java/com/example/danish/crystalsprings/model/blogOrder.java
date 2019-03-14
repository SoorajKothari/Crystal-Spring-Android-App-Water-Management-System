package com.example.danish.crystalsprings.model;

public class blogOrder {
    private String date,items,orderno,price,quantity,status;


    public blogOrder(String date, String items, String orderno, String price, String quantity, String status) {
        this.date = date;
        this.items = items;
        this.orderno = orderno;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public blogOrder()
    {

    }

}
