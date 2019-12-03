package com.example.clinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinicapp.Clinics.Service;
import com.example.clinicapp.DataBase.DataBase;

import java.util.ArrayList;

public class ServiceByClinic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_by_clinic);
    }

    public void dOnC(View view){
        EditText nameOfClinic = (EditText)findViewById(R.id.editText6);
        if(nameOfClinic.getText().toString().equals("")){
            Toast.makeText(ServiceByClinic.this, "You haven't input anything", Toast.LENGTH_LONG).show();
            nameOfClinic.setText("");
            return;
        }
        else{
            for(int i = 0; i < nameOfClinic.getText().toString().length(); i ++){
                if(!Character.isDigit(nameOfClinic.getText().toString().charAt(i)) && !Character.isLetter(nameOfClinic.getText().toString().charAt(i))){
                    Toast.makeText(ServiceByClinic.this, "Invalid info", Toast.LENGTH_LONG).show();
                    nameOfClinic.setText("");
                    return;
                }
            }
        }
        DataBase dataBase = new DataBase(this);
        ArrayList<Service> services = dataBase.getServiceOfClinic(nameOfClinic.getText().toString());
        String service = "";
        if(services == null){
            service = "This clinic doesn't have any service yet";
        }
        else {
            for (int i = 0; i < services.size(); i++) {
                service += i + 1;
                service += ". ";
                if (i != services.size()) {
                    service += services.get(i).getName();
                    service += "\n";
                } else {
                    service += services.get(i).getName();
                }
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
}
