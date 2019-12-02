package com.example.clinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.clinicapp.Clinics.Clinic;
import com.example.clinicapp.DataBase.DataBase;

import java.util.ArrayList;

public class AdminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        TextView textView = (TextView)findViewById(R.id.textView5);
        TextView welcome = (TextView)findViewById(R.id.textView19);
        welcome.setText("Welcome " + name + "!");
        textView.setText("You are logged in as Admin");
    }

    public void addOnClick(View view){
        Intent intent = new Intent(this, addService.class);
        startActivity(intent);
    }

    public void editOnClick(View view){
        Intent intent = new Intent(this, editService.class);
        startActivity(intent);
    }

    public void deleteOnClick(View view){
        Intent intent = new Intent(this, deleteService.class);
        startActivity(intent);
    }

    public void servicesOnClick(View view){
        DataBase dataBase = new DataBase(this);
        String[] services = dataBase.showServices();
        String service = "";
        for(int i = 0; i < services.length; i++){
            service += i+1;
            service += ". ";
            if(i != services.length){
                service += services[i];
                service += "\n";
            }
            else{
                service += services[i];
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("All the services provided clinics");
        builder.setMessage(service);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });
        AlertDialog b = builder.create();
        b.show();
    }

    public void accountOnClick(View view){
        Intent intent = new Intent(this, AccountManager.class);
        startActivity(intent);
    }

    public void showOnClick(View view){
        Intent intent = new Intent(this, ShowAccounts.class);
        startActivity(intent);
    }

    public void SAC(View view){
        DataBase dataBase = new DataBase(this);
        ArrayList<Clinic> c = dataBase.showAllClinics();
        String clinics = "";
        for(int i = 0; i < c.size(); i++){
            clinics += i+1;
            clinics += ". ";
            if(i != c.size()){
                clinics += c.get(i).getName();
                clinics += "\n";
            }
            else{
                clinics += c.get(i).getName();
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("All the clinics");
        builder.setMessage(clinics);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });
        AlertDialog b = builder.create();
        b.show();
    }

    public void ServiceByClinic(View view){
        Intent intent = new Intent(this, ServiceByClinic.class);
        startActivity(intent);
    }
}
