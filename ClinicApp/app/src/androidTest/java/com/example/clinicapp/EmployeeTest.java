package com.example.clinicapp;

import com.example.clinicapp.Clinics.Employee;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {
    @Test
    public void employeeUserNameTest(){
        Employee employee = new Employee("George", "123", "George");
        assertEquals("Check the user name of the employee account", "George", employee.getUserName());
    }

    @Test
    public void changeUserNameTest(){
        Employee employee = new Employee("George", "123", "George");
        employee.setUserName("Wang");
        assertEquals("Check the user name of the employee account", "Wang", employee.getUserName());
    }

    @Test
    public void employeeNameTest(){
        Employee employee = new Employee("George", "123", "George");
        assertEquals("Check the name of the employee account", "George", employee.getName());
    }

    @Test
    public void changeNameTest(){
        Employee employee = new Employee("George", "123", "George");
        employee.setName("Wang");
        assertEquals("Check the user name of the employee account", "Wang", employee.getName());
    }

    @Test
    public void employeePasswordTest(){
        Employee employee = new Employee("George", "123", "George");
        assertEquals("Check the password of the employee account", "123", employee.getPassword());
    }

    @Test
    public void changePasswordTest(){
        Employee employee = new Employee("George", "123", "George");
        employee.setPassword("456");
        assertEquals("Check the user name of the employee account", "456", employee.getPassword());
    }

    @Test
    public void setWorkingHourTest(){
        Employee employee = new Employee("George", "123", "George");
        ArrayList<String> wh = new ArrayList<>(Arrays.asList("11/19/2019 to 11/23/2019"));
        employee.setWorkingHours(wh);
        assertEquals("Check the working hour of the employee account", wh, employee.getWorkingHours());
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
}
