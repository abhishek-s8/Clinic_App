package com.example.clinicapp.Clinics;

import java.util.ArrayList;

public class Clinic {
    private ArrayList<Service> services;
    private String name;
    private String address;
    private double rate;
    private boolean rated;
    private ArrayList<ArrayList> workingHour;

    public Clinic(){ }

    public Clinic(String name, String address){
        this.name = name;
        this.address =  address;
        services = new ArrayList<>();
        rate = 0.0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }


    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public boolean addService(Service service){
        return services.add(service);
    }

    public boolean removeService(Service service){
        return services.remove(service);
    }

    public boolean editService(Service before, Service after){
        boolean remove = services.remove(before);
        if(remove){
            boolean add = services.add(after);
            if(add){
                return true;
            }
            else{
                services.add(before);
                return false;
            }
        }
        else{
            return false;
        }
    }

    public double getRate() {
        return rate;
    }

    public void rate(double clientRate){
        if(rated){
            rate += clientRate;
            rate = rate / 2;
        }
        else{
            rate = clientRate;
            rated = true;
        }
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public ArrayList<ArrayList> getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(ArrayList<ArrayList> workingHour) {
        this.workingHour = workingHour;
    }

    public void addWorkingHour(ArrayList<String> workingHour){
        this.workingHour.add(workingHour);
    }
}
