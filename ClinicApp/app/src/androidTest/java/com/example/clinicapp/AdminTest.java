package com.example.clinicapp;

import com.example.clinicapp.Clinics.Admin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdminTest {
    @Test
    public void adminUserNameTest(){
        Admin admin = new Admin();
        assertEquals("Check the user name of the admin account", "admin", admin.getUserName());
    }

    @Test
    public void adminNameTest(){
        Admin admin = new Admin();
        assertEquals("Check the name of the admin account", "admin", admin.getName());
    }

    @Test
    public void adminPasswordTest(){
        Admin admin = new Admin();
        assertEquals("Check the password of the admin account", "5T5ptQ", admin.getPassword());
    }
}
