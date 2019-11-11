package com.example.clinicapp;

public class Admin {
    private String password;
    private String name;
    private String userName;

    public Admin(){
        this.userName = "admin";
        this.password = "5T5ptQ";
        this.name = "admin";
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return password;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getUserName()
    {
        return userName;
    }
}
