package com.example.clinicapp;

public interface Account {
    public abstract void setName(String name);
    public abstract void setUserName(String userName);
    public abstract void setPassword(String password);
    public abstract String getPassword();
    public abstract String getName();
    public abstract String getUserName();
}
