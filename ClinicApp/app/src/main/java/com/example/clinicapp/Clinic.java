package com.example.clinicapp;

import java.util.ArrayList;

public class Clinic {
    private ArrayList<Service> services;
    private String name;
    private String address;

    public Clinic(String name,String address){
        this.name = name;
        this.address =  address;
        services = new ArrayList<>();
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
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
}
