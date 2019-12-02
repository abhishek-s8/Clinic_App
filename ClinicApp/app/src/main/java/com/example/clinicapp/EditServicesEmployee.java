package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.clinicapp.Clinics.Employee;
import com.example.clinicapp.DataBase.DataBase;

public class EditServicesEmployee extends AppCompatActivity {
    private String userName;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_services_employee);
        DataBase dataBase = new DataBase(this);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        employee = dataBase.getEmployee(userName);
        dataBase.close();
    }

    public void EFOnClick(View view){
        DataBase dataBase = new DataBase(this);
        RadioButton add = (RadioButton)findViewById(R.id.AddEBtn);
        RadioButton delete = (RadioButton)findViewById(R.id.radioButton2);
        EditText addService = (EditText)findViewById(R.id.addEText);
        EditText deleteService = (EditText)findViewById(R.id.editText2);

        if(addService.getText().toString().equals("") && deleteService.getText().toString().equals("")){
            Toast.makeText(EditServicesEmployee.this, "You didn't input anything.", Toast.LENGTH_LONG).show();
            finish();
        }

        if(add.isChecked()){
            if(dataBase.serviceExist(addService.getText().toString())){
                dataBase.update("Service", "service", addService.getText().toString(), "person", employee.getUserName());
                Toast.makeText(EditServicesEmployee.this, "Success!!!", Toast.LENGTH_LONG).show();
                dataBase.addService2Clinic(employee.getNameOfClinic(), userName);
                finish();
            }else{
                Toast.makeText(EditServicesEmployee.this, "The service doesn't exist", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        else if(delete.isChecked()){
            if(dataBase.serviceExist(deleteService.getText().toString())){
                dataBase.update("Service", "service", deleteService.getText().toString(), "person", null);
                Toast.makeText(EditServicesEmployee.this, "Success!!!", Toast.LENGTH_LONG).show();
                dataBase.deleteServiceClinic(employee.getNameOfClinic(), deleteService.getText().toString());
                finish();
            }
            else{
                Toast.makeText(EditServicesEmployee.this, "The service doesn't exist", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
