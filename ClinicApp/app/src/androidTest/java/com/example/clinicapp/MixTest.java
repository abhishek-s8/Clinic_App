package com.example.clinicapp;

import com.example.clinicapp.Clinics.Admin;
import com.example.clinicapp.Clinics.Clients;
import com.example.clinicapp.Clinics.Employee;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MixTest {
    @Test
    public void adminUserNameTest(){
        Admin admin = new Admin();
        assertEquals("Check the user name of the admin account", "admin", admin.getUserName());
    }

    @Test
    public void setWorkingHourTest(){
        Employee employee = new Employee("Steve", "abc", "Steve");
        ArrayList<String> wh = new ArrayList<>(Arrays.asList("11/26/2019 to 11/27/2019"));
        employee.setWorkingHours(wh);
        assertEquals("Check the working hour of the employee account", wh, employee.getWorkingHours());
    }

    @Test
    public void clientNameTest(){
        Clients clients = new Clients("Tyson", "abc", "Tyson", 30);
        assertEquals("Check the name of the client account", "Tyson", clients.getName());
    }

    @Test
    public void deleteWorkingHourTest(){
        Employee employee = new Employee("George", "123", "George");
        ArrayList<String> wh = new ArrayList<>(Arrays.asList("11/19/2019 to 11/23/2019"));
        employee.setWorkingHours(wh);
        employee.removeWorkingHour("11/19/2019 to 11/23/2019");
        wh.remove("11/19/2019 to 11/23/2019");
        assertEquals("Check the working hour of the employee account", wh, employee.getWorkingHours());
    }

    @Test
    public void addWorkingHourTest(){
        Employee employee = new Employee("George", "123", "George");
        ArrayList<String> wh = new ArrayList<>(Arrays.asList("11/19/2019 to 11/23/2019"));
        employee.setWorkingHours(wh);
        employee.addWorkingHour("11/26/2019 to 11/27/2019");
        wh.add("11/26/2019 to 11/27/2019");
        assertEquals("Check the working hour of the employee account", wh, employee.getWorkingHours());
    }

    @Test
    public void editWorkingHourTest(){
        Employee employee = new Employee("George", "123", "George");
        ArrayList<String> wh = new ArrayList<>(Arrays.asList("11/26/2019 to 11/27/2019"));
        employee.setWorkingHours(wh);
        employee.editWorkingHour("11/26/2019 to 11/27/2019", "11/19/2019 to 11/23/2019");
        wh.remove("11/26/2019 to 11/27/2019");
        wh.add("11/19/2019 to 11/23/2019");
        assertEquals("Check the working hour of the employee account", wh, employee.getWorkingHours());
    }

    @Test
    public void adminNameTest(){
        Admin admin = new Admin();
        assertEquals("Check the name of the admin account", "admin", admin.getName());
    }

    @Test
    public void clientPasswordTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        assertEquals("Check the password of the client account", "123", clients.getPassword());
    }

    @Test
    public void employeeUserNameTest(){
        Employee employee = new Employee("George", "123", "George");
        assertEquals("Check the user name of the employee account", "George", employee.getUserName());
    }

    @Test
    public void adminPasswordTest(){
        Admin admin = new Admin();
        assertEquals("Check the password of the admin account", "5T5ptQ", admin.getPassword());
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
    public void clientAgeTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        assertEquals("Check the age of the client account", 19, clients.getAge());
    }
}
