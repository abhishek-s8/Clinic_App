package com.example.clinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.clinicapp.Clinics.Admin;
import com.example.clinicapp.Clinics.Clients;
import com.example.clinicapp.Clinics.Employee;
import com.example.clinicapp.DataBase.DataBase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void loginOnClick(View view){
        EditText userName = (EditText)findViewById(R.id.userName);
        EditText password = (EditText)findViewById(R.id.password);

        DataBase dataBase  = new DataBase(this);
        Clients client = dataBase.clientExist(userName.getText().toString(), password.getText().toString());
        Employee employee = dataBase.employeeExist(userName.getText().toString(), password.getText().toString());
        Admin admin = dataBase.adminExist(userName.getText().toString(), password.getText().toString());

        if (client == null && employee == null && admin == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Login failed");
            builder.setMessage("You may entered wrong username or password. Please try again");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });
            AlertDialog b = builder.create();
            b.show();
        }
        else if(client != null){
            userName.setText("");
            password.setText("");
            String name = client.getName();
            Intent intent = new Intent(this, ClientLogin.class);
            intent.putExtra("Name", name);
            startActivity(intent);

        }
        else if(employee!= null){
            userName.setText("");
            password.setText("");
            String name = employee.getName();
            Intent intent = new Intent(this, EmployeeLogin.class);
            intent.putExtra("Name", name);
            intent.putExtra("userName", employee.getUserName());
            startActivity(intent);
        }
        else if(admin != null){
            userName.setText("");
            password.setText("");
            String name = admin.getName();
            Intent intent = new Intent(this, AdminLogin.class);
            intent.putExtra("Name", name);
            startActivity(intent);
        }
    }

    public void createOnClick(View view) {
        EditText userName = (EditText)findViewById(R.id.userName);
        EditText password = (EditText)findViewById(R.id.password);

        userName.setText("");
        password.setText("");
        Intent intent = new Intent(this, EmployeeORClient.class);
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
        builder.setTitle("All the services provided by our clinic");
        builder.setMessage(service);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });
        AlertDialog b = builder.create();
        b.show();
    }
}
