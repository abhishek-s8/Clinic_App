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
        TextView textView = (TextView)findViewById(R.id.textView5);
        TextView welcome = (TextView)findViewById(R.id.textView17);
        TextView profiles = (TextView)findViewById(R.id.textView16);
        welcome.setText("Welcome " + name + "!\n");
        textView.setText("You are logged in as employee.");
        profiles.setText("\nYour address is " + address + ".\nYour phone number is " + phoneNum + ".\nName of the clinic is " + nameOfClinic + "." +
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
        ArrayList<String> services = dataBase.showService(employee.getUserName());
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
        alertDialogBuilder.setTitle("Edit your working hour");
        alertDialogBuilder.setView(deleteWHView);
        final EditText beforeStart = (EditText) deleteWHView.findViewById(R.id.editText3);
        final EditText beforeEnd = (EditText) deleteWHView.findViewById(R.id.editText8);
        final EditText toStart = (EditText) deleteWHView.findViewById(R.id.editText4);
        final EditText toEnd = (EditText)deleteWHView.findViewById(R.id.editText10);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                boolean pass = true;
                String beforeSS = beforeStart.getText().toString();
                String beforeES = beforeEnd.getText().toString();
                String toSS = toStart.getText().toString();
                String toES = toEnd.getText().toString();

                if(beforeSS.equals("") || beforeES.equals("") || toSS.equals("") || toES.equals("")){
                    Toast.makeText(EmployeeLogin.this, "Fail, you haven't inout anything", Toast.LENGTH_LONG).show();
                    pass = false;
                }
                else if(beforeSS.length() != 8 || beforeES.length() != 8 || toSS.length() != 8 || toES.length() != 8){
                    Toast.makeText(EmployeeLogin.this, "Fail, you have input invalid info", Toast.LENGTH_LONG).show();
                    pass = false;
                }
                else{
                    for(int i = 0; i < beforeSS.length(); i++){
                        if(!Character.isDigit(beforeSS.charAt(i))){
                            Toast.makeText(EmployeeLogin.this, "Fail, you have input invalid info", Toast.LENGTH_LONG).show();
                            pass = false;
                        }
                    }
                    for(int i = 0; i < beforeES.length(); i++){
                        if(!Character.isDigit(beforeES.charAt(i))){
                            Toast.makeText(EmployeeLogin.this, "Fail, you have input invalid info", Toast.LENGTH_LONG).show();
                            pass = false;
                        }
                    }
                    for(int i = 0; i < toSS.length(); i++){
                        if(!Character.isDigit(toSS.charAt(i))){
                            Toast.makeText(EmployeeLogin.this, "Fail, you have input invalid info", Toast.LENGTH_LONG).show();
                            pass = false;
                        }
                    }
                    for(int i = 0; i < toES.length(); i++){
                        if(!Character.isDigit(toES.charAt(i))){
                            Toast.makeText(EmployeeLogin.this, "Fail, you have input invalid info", Toast.LENGTH_LONG).show();
                            pass = false;
                        }
                    }

                    if(pass) {
                        boolean delete = dataBase.deleteWorkingHour(beforeSS, beforeES, userName);
                        if (delete) {
                            boolean add = dataBase.addWorkingHour(toSS, toES, userName);
                            if (add) {
                                Toast.makeText(EmployeeLogin.this, "Success!!!", Toast.LENGTH_LONG).show();
                                ArrayList<String> d = new ArrayList<>();
                                d.add(beforeSS);
                                d.add(beforeES);
                                dataBase.editWorkingHourClinic(userName, employee.getNameOfClinic(), d);
                            } else {
                                dataBase.addWorkingHour(beforeSS, beforeES, userName);
                                Toast.makeText(EmployeeLogin.this, "Fail", Toast.LENGTH_LONG).show();
                            }
                        }
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
