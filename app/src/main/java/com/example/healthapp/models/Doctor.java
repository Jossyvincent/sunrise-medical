package com.example.healthapp.models;

public class Doctor {
    private int id;
    private String name;
    private String specialty;
    private String hospital;
    private double fee;
    private String phone;
    private String email;

    // Getters and setters
    public int getId() {
        return id;}

    public void setId(int id) {
        this.id = id;}

    public String getHospital() {
        return hospital;}

    public void setHospital(String hospital) {
        this.hospital = this.hospital;
    }
    public double getFee(){
        return fee;
    }
    public void setFee(double fee) {
        this.fee = this.fee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;}

    public String getSpecialty() {
        return specialty;}

    public void setSpecialty(String specialty) {
        this.specialty = specialty;}

    public String getPhone() {
        return phone;}

    public void setPhone(String phone) {
        this.phone = phone;}
    public String getEmail(){
        return email;}
    public void setEmail(String email){
        this.email = email;}
}
