package com.example.clinicapp.Clinics;

public interface Account {
    public abstract void setUserName(String userName);
    public abstract String getUserName();

    public abstract void setPassword(String password);
    public abstract String getPassword();

    public abstract void setName(String name);
    public abstract String getName();
}
