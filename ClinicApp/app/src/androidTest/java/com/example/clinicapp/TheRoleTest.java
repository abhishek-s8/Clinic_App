package com.example.clinicapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TheRoleTest {
    @Test
    public void clientUserNameTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        assertEquals("Check the user name of the client account", "Ralph", clients.getUserName());
    }
    @Test
    public void clientNameTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        assertEquals("Check the name of the client account", "Ralph", clients.getName());
    }
    @Test
    public void clientPasswordTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        assertEquals("Check the password of the client account", "123", clients.getPassword());
    }
    @Test
    public void clientAgeTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        assertEquals("Check the age of the client account", 19, clients.getAge());
    }
    @Test
    public void employeeUserNameTest(){
        Employee employee = new Employee("George", "123", "George");
        assertEquals("Check the user name of the employee account", "George", employee.getUserName());
    }
    @Test
    public void employeeNameTest(){
        Employee employee = new Employee("George", "123", "George");
        assertEquals("Check the name of the employee account", "George", employee.getName());
    }
    @Test
    public void employeePasswordTest(){
        Employee employee = new Employee("George", "123", "George");
        assertEquals("Check the password of the employee account", "123", employee.getPassword());
    }
    @Test
    public void adminUserNameTest() {
        Admin admin = new Admin();
        assertEquals("Check the user name of admin", "admin", admin.getUserName());
    }
    @Test
    public void adminNameTest() {
        Admin admin = new Admin();
        assertEquals("Check the name of admin", "admin", admin.getName());
    }
    @Test
    public void adminPasswordTest() {
        Admin admin = new Admin();
        assertEquals("Check the password of admin", "5T5ptQ", admin.getPassword());
    }
}