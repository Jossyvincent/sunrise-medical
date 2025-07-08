package com.example.healthapp.models;

public class Medicine {
    private String name;
    private double price;

    // the constructor
    public Medicine(String name, double price){
        this.name = name;
        this.price = price;
    }
    //getter and setters
    public String getName() {
        return name;
    }
    public double getPrice(){
        return price;
    }
    @Override
    public String toString(){
        return  name + "- ksh - " + price;
    }
}
