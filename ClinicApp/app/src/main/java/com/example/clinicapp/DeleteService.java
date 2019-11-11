package com.example.clinicapp;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DeleteService extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_service);
    }
    public void finishOnClick(View view)
    {

        RadioButton doctor=(RadioButton)findViewById(R.id.doctorBtn);
        RadioButton staff=(RadioButton)findViewById(R.id.staffBtn);
        RadioButton nurse=(RadioButton)findViewById(R.id.nurseBtn);

        Clinic clinic=new Clinic();

        EditText userInput=(EditText) findViewById(R.id.deleteText);

        if(userInput.getText().toString().equals(""))
        {
            Toast.makeText(DeleteService.this,"No Service Inputted",Toast.LENGTH_LONG).show();
            finish();
        }
        else if(nurse.isChecked())
        {

            boolean success=clinic.deleteService(userInput.getText().toString()," by nurse");

            if(success)
            {
                Toast.makeText(DeleteService.this,"Complete",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(DeleteService.this,"Service DOES NOT exist",Toast.LENGTH_LONG).show();
            }

            finish();
        }
        else if(doctor.isChecked())
        {

            boolean success=clinic.deleteService(userInput.getText().toString()," by doctor");

            if(success)
            {
                Toast.makeText(DeleteService.this,"Complete", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(DeleteService.this,"Service DOES NOT exist",Toast.LENGTH_LONG).show();
            }

            finish();
        }
        else if(staff.isChecked())
        {

            boolean success=clinic.deleteService(userInput.getText().toString(), " by staff");

            if(success)
            {
                Toast.makeText(DeleteService.this, "Complete", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(DeleteService.this, "Service DOES NOT exist", Toast.LENGTH_LONG).show();
            }

            finish();
        }
        else {
            Toast.makeText(DeleteService.this,"Role NOT CHOSEN for service.",Toast.LENGTH_LONG).show();
            finish();
        }
    }
    public void cancelOnClick(View view){
        finish();
    }
}
