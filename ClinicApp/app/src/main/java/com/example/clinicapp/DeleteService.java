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
        myDBHelper dataBase = new myDBHelper(this);

        RadioButton doctor=(RadioButton)findViewById(R.id.doctorBtn);
        RadioButton staff=(RadioButton)findViewById(R.id.staffBtn);
        RadioButton nurse=(RadioButton)findViewById(R.id.nurseBtn);

        EditText userInput = (EditText) findViewById(R.id.deleteText);

        if(userInput.getText().toString().equals("")){

            Toast.makeText(DeleteService.this,"No service was inputted.", Toast.LENGTH_LONG).show();

            dataBase.close();
            finish();
        }
        else{
            boolean success=dataBase.deleteService(userInput.getText().toString());
            if(success){
                Toast.makeText(DeleteService.this, "Complete", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(DeleteService.this, "Service does NOT exist", Toast.LENGTH_LONG).show();
            }
            dataBase.close();

            finish();
        }
    }
    public void cancelOnClick(View view){
        finish();
    }
}
