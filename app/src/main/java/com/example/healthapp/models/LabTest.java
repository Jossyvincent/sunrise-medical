package com.example.healthapp.models;

import java.io.Serializable;

public class LabTest implements Serializable {
    private String name;
    private double price;
    private boolean selected;

    public LabTest(String name, double price) {
        this.name = name;
        this.price = price;
        this.selected = false;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
}
