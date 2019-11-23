package com.example.clinicapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddService extends AppCompatActivity {

    private RadioButton doctor;
    private RadioButton staff;
    private RadioButton nurse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_service);
    }

    public void finishClick(View view)
    {
        myDBHelper dataBase = new myDBHelper(this);

        doctor = (RadioButton)findViewById(R.id.doctorBtn);
        staff = (RadioButton)findViewById(R.id.staffBtn);
        nurse = (RadioButton)findViewById(R.id.nurseBtn);

        EditText userInput = (EditText)findViewById(R.id.addText);

        if(userInput.getText().toString().equals("")){
            Toast.makeText(AddService.this,"No service was inputted!", Toast.LENGTH_LONG).show();

            dataBase.close();
            finish();
        }
        else if(nurse.isChecked()){

            boolean success = dataBase.addService(userInput.getText().toString(), " by nurse");

            if(success){
                Toast.makeText(AddService.this,"Complete", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(AddService.this,"This service has exist", Toast.LENGTH_LONG).show();
            }

            dataBase.close();
            finish();
        }
        else if(doctor.isChecked()){

            boolean success=dataBase.addService(userInput.getText().toString(), " by doctor");

            if(success){
                Toast.makeText(AddService.this,"Complete", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(AddService.this, "This service has exist", Toast.LENGTH_LONG).show();
            }

            dataBase.close();
            finish();
        }
        else if(staff.isChecked()){

            boolean success=dataBase.addService(userInput.getText().toString(), " by staff");

            if(success){
                Toast.makeText(AddService.this,"Complete", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(AddService.this,"This service has exist", Toast.LENGTH_LONG).show();
            }

            dataBase.close();
            finish();
        }
        else{

            Toast.makeText(AddService.this, "No role was chosen for this service.", Toast.LENGTH_LONG).show();

            dataBase.close();
            finish();
        }

    }

    public void cancelClick(View view)
    {
        finish();
    }
}
