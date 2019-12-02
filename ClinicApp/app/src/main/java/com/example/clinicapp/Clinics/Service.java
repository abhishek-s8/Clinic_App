package com.example.clinicapp.Clinics;

public class Service {
    private String name;
    private String role;
    private Employee employee;

    public Service(String name, String role){
        this.name = name;
        this.role = role;
    }

    public Service(){ }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
