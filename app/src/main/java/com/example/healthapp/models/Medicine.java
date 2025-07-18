package com.example.healthapp.models;

public class Medicine {
    private String name;
    private float price;

    // the constructor
    public Medicine(String name, float price){
        this.name = name;
        this.price = price;
    }
    //getter and setters
    public String getName() {
        return name;
    }
    public float getPrice(){
        return price;
    }
    @Override
    public String toString(){
        return  name + "- ksh - " + price;
    }
}
