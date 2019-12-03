package com.example.clinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicapp.Clinics.Employee;
import com.example.clinicapp.DataBase.DataBase;

import java.util.ArrayList;
import java.util.List;

public class ClientLogin extends AppCompatActivity {
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_login);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        userName = intent.getStringExtra("userName");
        TextView textView = (TextView)findViewById(R.id.textView5);
        TextView welcome = (TextView)findViewById(R.id.textView20);
        welcome.setText("Welcome "+ name + "!");
        textView.setText("You are logged in as patient");
    }

    public void searchByAddress(View view){
        Intent intent = new Intent(this, SearchByAddress.class);
        startActivity(intent);
    }

    public void searchByService(View view){
        Intent intent = new Intent(this, SearchByService.class);
        startActivity(intent);
    }

    public void searchByWH(View view){
        Intent intent = new Intent(this, SearchByWorkingHour.class);
        startActivity(intent);
    }

    public void rateClinic(View view){
        Intent intent = new Intent(this, RateClinic.class);
        startActivity(intent);
    }

    public void makeAppointment(View view){
        Intent intent = new Intent(this, MakeAppointment.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }

    public void searchByName(View view){
        Intent intent = new Intent(this, SearchByName.class);
        startActivity(intent);
    }

    public void AWT(View view){
        Intent intent = new Intent(this, ApproximateWaitingTime.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }

    public void deleteAppointment(View view){
        final DataBase dataBase = new DataBase(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete your appointment");
        builder.setMessage("Are you sure you want to delete your appointment?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){
                ArrayList<String> appointment = dataBase.getAppointment(userName);
                if(appointment == null){
                    Toast.makeText(ClientLogin.this, "Sorry, you don't have any appointment", Toast.LENGTH_LONG).show();
                }
                else {
                    String userNameEmployee = appointment.get(1);
                    dataBase.deleteAppointment(userName);
                    dataBase.deleteAppointmentEmployee(userNameEmployee);
                    Toast.makeText(ClientLogin.this, "Your have successfully delete your appointment", Toast.LENGTH_LONG).show();
                }
            }
        });
        AlertDialog b = builder.create();
        b.show();
    }

    public void checkIn(View view){
        final DataBase dataBase = new DataBase(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Check in");
        builder.setMessage("Are you sure you want to check in?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){
                if (dataBase.getAppointment(userName) == null){
                    Toast.makeText(ClientLogin.this, "Your don't have any appointment yet", Toast.LENGTH_LONG).show();
                }
                else {
                    String userNameEmployee = dataBase.getAppointment(userName).get(1);
                    dataBase.deleteAppointment(userName);
                    dataBase.deleteAppointmentEmployee(userNameEmployee);
                    Toast.makeText(ClientLogin.this, "Your have successfully check in", Toast.LENGTH_LONG).show();
                }
            }
        });
        AlertDialog b = builder.create();
        b.show();
    }

    public void viewAD(View view){
        DataBase dataBase = new DataBase(this);
        List<Employee> accountsE = dataBase.showAllEmployees();
        String doctors = "";
        if(accountsE == null){
            doctors = "Sorry, there is no doctor sign up yet";
        }
        else {
            for (int i = 0; i < accountsE.size(); i++) {
                doctors += i + 1;
                doctors += ". Dr.";
                if (i != accountsE.size()) {
                    doctors += accountsE.get(i).getName();
                    doctors += " of ";
                    doctors += accountsE.get(i).getNameOfClinic();
                    doctors += "\n";
                } else {
                    doctors += accountsE.get(i).getName();
                    doctors += " of ";
                    doctors += accountsE.get(i).getNameOfClinic();
                }
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("All doctors");
        builder.setMessage(doctors);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){ }
        });
        AlertDialog b = builder.create();
        b.show();
    }
}
