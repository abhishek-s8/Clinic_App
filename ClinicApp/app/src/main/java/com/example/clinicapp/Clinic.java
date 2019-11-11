package com.example.clinicapp;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Clinic {

    private static List<String> service1 = new ArrayList<>(Arrays.asList("General Diagnostic"," by nurse"));
    private static List<String> service2 = new ArrayList<>(Arrays.asList("Receive Prescription"," by doctor"));
    private static List<String> service3 = new ArrayList<>(Arrays.asList("Optometry"," by staff"));

    private static ArrayList<List> services = new ArrayList<List>(Arrays.asList(service1, service2, service3));

    public Clinic(){
    }

    public ArrayList<List> getServices()
    {
        return services;
    }

    public boolean deleteService(String service,String sen)
    {

        List<String>newService=new ArrayList<>(Arrays.asList(service,sen));

        if(services.contains(newService))
        {
            services.remove(newService);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean addService(String service,String sen)
    {

        List<String>newService=new ArrayList<>(Arrays.asList(service,sen));

        if ( services.contains(newService) )
        {
            return false;
        }
        else{
            services.add( newService );
            return true;
        }
    }

    public boolean editService(String service,String newService,String sen)
    {
        boolean i = this.deleteService(service,sen);
        boolean j = this.addService(newService,sen);
        return i && j;
    }
}