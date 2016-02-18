package com.example.nina.shopper2;

/**
 * Created by Nina Longasa on 2/18/2016.
 */
public class ShoppingListItem {

    private int id;
    private String name;
    private Double price;
    private Integer quantity;
    private  String has;
    private int list_id;

    public ShoppingListItem(int id, String name, Double price, Integer quantity, String has, int list_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.has = has;
        this.list_id = list_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getHas() {
        return has;
    }

    public void setHas(String has) {
        this.has = has;
    }

    public int getList_id() {
        return list_id;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
    }
}
