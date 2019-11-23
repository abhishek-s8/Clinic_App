package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class EditEmployeeServices extends AppCompatActivity {
    private String userName;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_services_employee);

        myDBHelper dataBase=new myDBHelper(this);
        Intent theIntent=getIntent();

        userName = theIntent.getStringExtra("userName");
        employee = dataBase.getEmployee(userName);

        dataBase.close();
    }

    public void EFOnClick(View view){
        myDBHelper dataBase=new myDBHelper(this);
        RadioButton add=(RadioButton)findViewById(R.id.AddEBtn);

        RadioButton delete=(RadioButton)findViewById(R.id.radioButton2);
        EditText addService=(EditText)findViewById(R.id.addEText);
        EditText deleteService=(EditText)findViewById(R.id.editText2);

        if(addService.getText().toString().equals("")&&deleteService.getText().toString().equals("")){

            Toast.makeText(EditEmployeeServices.this,"Nothing was inputted.", Toast.LENGTH_LONG).show();

            finish();
        }

        if(add.isChecked()){
            if(dataBase.serviceExist(addService.getText().toString())){

                dataBase.update("Service", "service", addService.getText().toString(), "person", employee.getName());
                Toast.makeText(EditEmployeeServices.this,"Complete", Toast.LENGTH_LONG).show();

                finish();
            }else{

                Toast.makeText(EditEmployeeServices.this,"The service does NOT exist.", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        else if(delete.isChecked()){
            if(dataBase.serviceExist(deleteService.getText().toString())){

                dataBase.update("Service", "service", deleteService.getText().toString(), "person", null);
                Toast.makeText(EditEmployeeServices.this,"Complete", Toast.LENGTH_LONG).show();

                finish();
            }
            else{

                Toast.makeText(EditEmployeeServices.this,"The service does NOT exist.", Toast.LENGTH_LONG).show();

                finish();
            }
        }
    }
}

