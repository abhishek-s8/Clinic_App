package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.clinicapp.DataBase.DataBase;

public class EditService extends AppCompatActivity {
    private RadioButton nurse;
    private RadioButton doctor;
    private RadioButton staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_service);
    }

    public void cancelClick(View view){
        finish();
    }

    public void finishClick(View view){
        DataBase dataBase = new DataBase(this);
        EditText userInput1 = (EditText) findViewById(R.id.deleteText);
        EditText userInput2 = (EditText) findViewById(R.id.newService);
        nurse = (RadioButton)findViewById(R.id.nurseBtn);
        doctor = (RadioButton)findViewById(R.id.doctorBtn);
        staff = (RadioButton)findViewById(R.id.staffBtn);
        if(userInput1.getText().toString().equals("") || userInput2.getText().toString().equals("")){
            Toast.makeText(EditService.this, "You haven't input service", Toast.LENGTH_LONG).show();
            dataBase.close();
            finish();
        }
        else if(nurse.isChecked()){
            boolean success = dataBase.editService(userInput1.getText().toString(), userInput2.getText().toString(), " by nurse");
            if(success){
                Toast.makeText(EditService.this, "Complete", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(EditService.this, "This service doesn't exist", Toast.LENGTH_LONG).show();
            }
            dataBase.close();
            finish();
        }
        else if(doctor.isChecked()){
            boolean success = dataBase.editService(userInput1.getText().toString(), userInput2.getText().toString(), " by doctor");
            if(success){
                Toast.makeText(EditService.this, "Complete", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(EditService.this, "This service doesn't exist", Toast.LENGTH_LONG).show();
            }
            dataBase.close();
            finish();
        }
        else if(staff.isChecked()){
            boolean success = dataBase.editService(userInput1.getText().toString(), userInput2.getText().toString(), " by staff");
            if(success){
                Toast.makeText(EditService.this, "Complete", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(EditService.this, "This service doesn't exist", Toast.LENGTH_LONG).show();
            }
            dataBase.close();
            finish();
        }
        else{
            Toast.makeText(EditService.this, "You didn't choose any role for this service", Toast.LENGTH_LONG).show();
            dataBase.close();
            finish();
        }
    }
}
