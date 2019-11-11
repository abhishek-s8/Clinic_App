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
        nurse = (RadioButton)findViewById(R.id.nurseBtn);
        doctor = (RadioButton)findViewById(R.id.doctorBtn);
        staff = (RadioButton)findViewById(R.id.staffBtn);

        EditText Input1 = (EditText) findViewById(R.id.deleteText);
        EditText Input2 = (EditText) findViewById(R.id.newService);

        Clinic clinic = new Clinic();
        if(Input1.getText().toString().equals("")||Input2.getText().toString().equals(""))
        {

            Toast.makeText(EditService.this,"No Service Entered",Toast.LENGTH_LONG).show();
            finish();
        }
        else if(nurse.isChecked())
        {

            boolean success=clinic.editService(Input1.getText().toString(),Input2.getText().toString()," by nurse");

            if(success)
            {
                Toast.makeText(EditService.this,"Complete",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(EditService.this,"Service DOES NOT exist",Toast.LENGTH_LONG).show();
            }
            finish();
        }
        else if(staff.isChecked())
        {

            boolean success=clinic.editService(Input1.getText().toString(),Input2.getText().toString()," by staff");

            if(success)
            {
                Toast.makeText(EditService.this, "Complete", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(EditService.this, "service DOES NOT exist", Toast.LENGTH_LONG).show();
            }
            finish();
        }
        else if(doctor.isChecked())
        {

            boolean success=clinic.editService(Input1.getText().toString(),Input2.getText().toString()," by doctor");

            if(success)
            {
                Toast.makeText(EditService.this, "Complete", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(EditService.this, "Service DOES NOT exist", Toast.LENGTH_LONG).show();
            }

            finish();
        }
        else {
            Toast.makeText(EditService.this, "DID NOT choose role for service.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void cancelClick(View view)
    {
        finish();
    }
}
