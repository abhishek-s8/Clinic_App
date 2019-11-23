package com.example.clinicapp;

import java.util.ArrayList;

public class Employee implements Account
{
    private ArrayList<String> workingHours;
    private String name;
    private String userName;
    private String password;
    private long phoneNumber;
    private String clinicName;
    private String insuranceTypes;
    private String paymentMethod;
    private String address;

    public Employee(){}
    public Employee(String userName,String password,String name){
        this.password = password;
        this.userName = userName;
        this.name = name;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getPassword()
    {
        return password;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getName()
    {
        return name;
    }
    public void setPhoneNumber(long phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public long getPhoneNumber(){ return phoneNumber; }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
    public void setClinicName(String nameOfClinic){
        this.clinicName = clinicName;
    }
    public String getClinicName(){
        return clinicName;
    }
    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }
    public String getPaymentMethod(){
        return paymentMethod;
    }
    public void setInsuranceTypes(String insuranceTypes){
        this.insuranceTypes = insuranceTypes;
    }
    public String getInsuranceTypes(){
        return insuranceTypes;
    }
    public void addWorkingHour(String workingHour){
        this.workingHours.add(workingHour);
    }
    public void removeWorkingHour(String workingHour){
        this.workingHours.remove(workingHour);
    }
    public void setWorkingHours(ArrayList<String> workingHours){ this.workingHours = workingHours; }
    public ArrayList<String> getWorkingHours(){
        return workingHours;
    }
    public void editWorkingHour(String before,String to){
        this.workingHours.remove(before);
        this.workingHours.add(to);
    }
}
