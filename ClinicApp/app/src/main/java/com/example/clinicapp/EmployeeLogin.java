package com.example.clinicapp;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;

public class EmployeeLogin extends AppCompatActivity
{
    private String name;
    private String userName;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_login);

        Intent theIntent = getIntent();
        name = theIntent.getStringExtra("Name");
        userName = theIntent.getStringExtra("userName");

        myDBHelper dataBase = new myDBHelper(this);
        employee = dataBase.getEmployee(userName);

        String nameOfClinic = employee.getClinicName();
        String insurance = employee.getInsuranceTypes();
        String address = employee.getAddress();
        long phoneNum = employee.getPhoneNumber();
        String paymentMethod = employee.getPaymentMethod();

        TextView textView = (TextView)findViewById(R.id.textView5);
        TextView textView2 = (TextView)findViewById(R.id.textView19);
        TextView welcome = (TextView)findViewById(R.id.textView17);
        TextView profiles = (TextView)findViewById(R.id.textView16);

        welcome.setText("Welcome " + name + "!\n");
        textView.setText("You are logged in as employee.");

        profiles.setText("\nYour address is " + address + ".\nYour phone number is " + phoneNum + ".\nYour name of the clinic is " + nameOfClinic + "." + "\nYour insurance type is " + insurance + ".\nYour payment method is " + paymentMethod);

        textView2.setText("Employee Privileges");
    }

    public void editProfileOnClick(View view){

        Intent theIntent=new Intent(this,EditEmployeeProfile.class);

        theIntent.putExtra("userName", userName);
        theIntent.putExtra("Name", name);

        startActivity(theIntent);

        finish();
    }

    public void EYSOnClick(View view){

        Intent theIntent=new Intent(this, EditEmployeeServices.class);

        theIntent.putExtra("userName", userName);

        startActivity(theIntent);
    }

    public void SYSOnClick(View view){

        myDBHelper dataBase=new myDBHelper(this);

        ArrayList<String> services=dataBase.showService(employee.getName());

        String service = "";

        for(int i=0;i<services.size();i++){

            service+=i+1;
            service+=". ";

            if(i!=services.size()){
                service+=services.get(i);
                service+="\n";
            }
            else{
                service+=services.get(i);
            }
        }

        AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);
        String name = employee.getName();

        theBuilder.setTitle("All the services provided by "+name);
        theBuilder.setMessage(service);

        theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });

        AlertDialog build = theBuilder.create();
        build.show();
    }

    public void addWorkingHour(View view){

        Intent intent = new Intent(this,AddWorkingHour.class);
        intent.putExtra("userName", userName);

        startActivity(intent);
    }

    public void deleteWorkingHour(View view){

        Intent intent = new Intent(this,DeleteWorkingHour.class);
        intent.putExtra("userName", userName);

        startActivity(intent);
    }

    public void showWorkingHours(View view){

        myDBHelper dataBase=new myDBHelper(this);
        ArrayList<String> workingHours=dataBase.getWorkingHours(userName);
        String temp ="";

        if(workingHours!=null){
            for(int i = 0; i<workingHours.size(); i++){
                temp+=i+1;
                temp+=". ";
                if(i!=workingHours.size()){
                    temp+=workingHours.get(i);
                    temp+="\n";
                }
                else{
                    temp+=workingHours.get(i);
                }
            }
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("All your working hours.");

        builder.setMessage(temp);

        builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0,int arg1){}
        });

        AlertDialog build = builder.create();
        build.show();
    }

    public void editWorkingHour(View view){

        final myDBHelper dataBase = new myDBHelper(this);
        LayoutInflater li = LayoutInflater.from(this);

        View deleteView = li.inflate(R.layout.edit_working_hour, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete your working hour");
        alertDialogBuilder.setView(deleteView);

        final EditText before = (EditText) deleteView.findViewById(R.id.editText3);
        final EditText to = (EditText) deleteView.findViewById(R.id.editText4);

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {

                if(before.getText().toString().equals("")||to.getText().toString().equals("")){

                    Toast.makeText(EmployeeLogin.this,"You have NOT input anything",Toast.LENGTH_LONG).show();
                }
                else if(before.getText().toString().length() <= 1||to.getText().toString().length() <= 1){

                    Toast.makeText(EmployeeLogin.this,"Invalid Info",Toast.LENGTH_LONG).show();
                }
                else{
                    String b=before.getText().toString();
                    boolean delete=dataBase.deleteWorkingHour(before.getText().toString(), userName);
                    if(delete){
                        if(to.getText().toString().length() <= 1){

                            dataBase.addWorkingHour(b,userName);
                            Toast.makeText(EmployeeLogin.this, "Failed", Toast.LENGTH_LONG).show();

                        }
                        else{
                            boolean add=dataBase.addWorkingHour(to.getText().toString(), userName);

                            if(add){
                                Toast.makeText(EmployeeLogin.this, "Complete", Toast.LENGTH_LONG).show();
                            }
                            else{
                                dataBase.addWorkingHour(b, userName);
                                Toast.makeText(EmployeeLogin.this, "Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else{
                        Toast.makeText(EmployeeLogin.this, "Failed", Toast.LENGTH_LONG).show();
                    }
                }
                dataBase.close();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void ShowServices(View view){

        String service="";
        myDBHelper dataBase=new myDBHelper(this);
        String[] services=dataBase.showServices();

        for(int i = 0; i < services.length; i++){

            service+=i+1;
            service+=". ";

            if(i!=services.length){
                service+=services[i];
                service+="\n";
            }
            else{
                service+=services[i];
            }
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setTitle("All the services provided by our clinic");
        builder.setMessage(service);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });

        AlertDialog build = builder.create();
        build.show();
        dataBase.close();
    }
}
