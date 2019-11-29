package com.example.clinicapp.Clinics;

public class Admin implements Account{
    private String userName;
    private String password;
    private String name;

    public Admin(){
        this.userName = "admin";
        this.password = "5T5ptQ";
        this.name = "admin";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
