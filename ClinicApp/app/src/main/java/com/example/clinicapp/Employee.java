package com.example.clinicapp;

public class Employee
{
    private String name;
    private String userName;
    private String password;

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
}
