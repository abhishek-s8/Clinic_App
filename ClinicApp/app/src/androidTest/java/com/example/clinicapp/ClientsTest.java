package com.example.clinicapp;

import com.example.clinicapp.Clinics.Clients;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientsTest {
    @Test
    public void clientUserNameTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        assertEquals("Check the user name of the client account", "Ralph", clients.getUserName());
    }

    @Test
    public void changeUserNameTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        clients.setUserName("Huo");
        assertEquals("Check the user name of the client account", "Huo", clients.getUserName());
    }

    @Test
    public void clientNameTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        assertEquals("Check the name of the client account", "Ralph", clients.getName());
    }

    @Test
    public void changeNameTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        clients.setName("Huo");
        assertEquals("Check the user name of the client account", "Huo", clients.getName());
    }

    @Test
    public void clientPasswordTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        assertEquals("Check the password of the client account", "123", clients.getPassword());
    }

    @Test
    public void changePasswrodTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        clients.setPassword("456");
        assertEquals("Check the user name of the client account", "456", clients.getPassword());
    }

    @Test
    public void clientAgeTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        assertEquals("Check the age of the client account", 19, clients.getAge());
    }

    @Test
    public void changeAgeTest(){
        Clients clients = new Clients("Ralph", "123", "Ralph", 19);
        clients.setAge(20);
        assertEquals("Check the user name of the client account", 20, clients.getAge());
    }
}
