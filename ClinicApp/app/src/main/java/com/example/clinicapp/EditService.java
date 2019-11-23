package com.example.clinicapp;

import android.widget.EditText;
import android.widget.RadioButton;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class EditService extends AppCompatActivity {

    private RadioButton doctor;
    private RadioButton staff;
    private RadioButton nurse;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_service);
    }

    public void finishClick(View view)
    {
        myDBHelper dataBase = new myDBHelper(this);

        nurse = (RadioButton)findViewById(R.id.nurseBtn);
        doctor = (RadioButton)findViewById(R.id.doctorBtn);
        staff = (RadioButton)findViewById(R.id.staffBtn);

        EditText Input1 = (EditText) findViewById(R.id.deleteText);
        EditText Input2 = (EditText) findViewById(R.id.newService);

        if(Input1.getText().toString().equals("")||Input2.getText().toString().equals("")){

            Toast.makeText(EditService.this,"You haven't input service", Toast.LENGTH_LONG).show();

            dataBase.close();
            finish();
        }
        else if(nurse.isChecked()){

            boolean success=dataBase.editService(Input1.getText().toString(),Input2.getText().toString(), " by nurse");

            if(success){
                Toast.makeText(EditService.this,"Complete",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(EditService.this, "This service doesn't exist",Toast.LENGTH_LONG).show();
            }

            dataBase.close();
            finish();
        }
        else if(doctor.isChecked()){

            boolean success=dataBase.editService(Input1.getText().toString(), Input2.getText().toString(), " by doctor");

            if(success){
                Toast.makeText(EditService.this, "Complete",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(EditService.this, "This service doesn't exist",Toast.LENGTH_LONG).show();
            }

            dataBase.close();
            finish();
        }
        else if(staff.isChecked()){

            boolean success=dataBase.editService(Input1.getText().toString(), Input2.getText().toString(), " by staff");

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

    public void cancelClick(View view)
    {
        finish();
    }
}
