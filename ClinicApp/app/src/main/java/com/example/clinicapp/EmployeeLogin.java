package com.example.clinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicapp.Clinics.Employee;
import com.example.clinicapp.DataBase.DataBase;

import java.util.ArrayList;

public class EmployeeLogin extends AppCompatActivity {
    private String userName;
    private Employee employee;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_login);
        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        userName = intent.getStringExtra("userName");
        DataBase dataBase = new DataBase(this);
        employee = dataBase.getEmployee(userName);
        String address = employee.getAddress();
        long phoneNum = employee.getPhoneNum();
        String nameOfClinic = employee.getNameOfClinic();
        String insurance = employee.getInsuranceTypes();
        String paymentMethod = employee.getPaymentMethod();
        TextView welcome = (TextView)findViewById(R.id.textView17);
        TextView textView = (TextView)findViewById(R.id.textView5);
        TextView profiles = (TextView)findViewById(R.id.textView16);
        welcome.setText("Welcome " + name + "!\n");
        textView.setText("You are logged in as employee.");
        profiles.setText("\nYour address is " + address + ".\nYour phone number is " + phoneNum + ".\nYour name of the clinic is " + nameOfClinic + "." +
                "\nYour insurance type is " + insurance + ".\nYour payment method is " + paymentMethod);
    }

    public void editProfileOnClick(View view){
        Intent intent = new Intent(this, editEmployeeProfiles.class);
        intent.putExtra("userName", userName);
        intent.putExtra("Name", name);
        startActivity(intent);
        finish();
    }

    public void EYSOnClick(View view){
        Intent intent = new Intent(this, EditServicesEmployee.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }

    public void SYSOnClick(View view){
        DataBase dataBase = new DataBase(this);
        ArrayList<String> services = dataBase.showService(employee.getName());
        String service = "";
        for(int i = 0; i < services.size(); i++){
            service += i+1;
            service += ". ";
            if(i != services.size()){
                service += services.get(i);
                service += "\n";
            }
            else{
                service += services.get(i);
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String name = employee.getName();
        builder.setTitle("All the services provided by " + name);
        builder.setMessage(service);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });
        AlertDialog b = builder.create();
        b.show();
    }

    public void addWorkingHour(View view){
        Intent intent = new Intent(this, addWorkingHour.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }

    public void deleteWorkingHour(View view){
        Intent intent = new Intent(this, deleteWorkingHour.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }

    public void showWorkingHours(View view){
        DataBase dataBase = new DataBase(this);
        ArrayList<String> workingHours = dataBase.getWorkingHours(userName);
        String WHs = "";
        if(workingHours != null){
            for(int i = 0; i < workingHours.size(); i++){
                WHs += i+1;
                WHs += ". ";
                if(i != workingHours.size()){
                    WHs += workingHours.get(i);
                    WHs += "\n";
                }
                else{
                    WHs += workingHours.get(i);
                }
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("All your working hours");
        builder.setMessage(WHs);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });
        AlertDialog b = builder.create();
        b.show();
    }

    public void editWorkingHour(View view){
        final DataBase dataBase = new DataBase(this);
        LayoutInflater li = LayoutInflater.from(this);
        View deleteWHView = li.inflate(R.layout.edit_working_hour, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete your working hour");
        alertDialogBuilder.setView(deleteWHView);
        final EditText before = (EditText) deleteWHView.findViewById(R.id.editText3);
        final EditText to = (EditText) deleteWHView.findViewById(R.id.editText4);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                if(before.getText().toString().equals("") || to.getText().toString().equals("")){
                    Toast.makeText(EmployeeLogin.this, "Fail, you haven't inout anything", Toast.LENGTH_LONG).show();
                }
                else if(before.getText().toString().length() <= 1 || to.getText().toString().length() <= 1){
                    Toast.makeText(EmployeeLogin.this, "Fail, you have input invalid info", Toast.LENGTH_LONG).show();
                }
                else{
                    String b = before.getText().toString();
                    boolean delete = dataBase.deleteWorkingHour(before.getText().toString(), userName);
                    if(delete){
                        if(to.getText().toString().length() <= 1){
                            dataBase.addWorkingHour(b, userName);
                            Toast.makeText(EmployeeLogin.this, "Faild", Toast.LENGTH_LONG).show();
                        }
                        else{
                            boolean add = dataBase.addWorkingHour(to.getText().toString(), userName);
                            if(add){
                                Toast.makeText(EmployeeLogin.this, "Success!!!", Toast.LENGTH_LONG).show();
                            }
                            else{
                                dataBase.addWorkingHour(b, userName);
                                Toast.makeText(EmployeeLogin.this, "Faild", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else{
                        Toast.makeText(EmployeeLogin.this, "Faild", Toast.LENGTH_LONG).show();
                    }
                }
                dataBase.close();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void ShowServices(View view){
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
        dataBase.close();
    }
}
