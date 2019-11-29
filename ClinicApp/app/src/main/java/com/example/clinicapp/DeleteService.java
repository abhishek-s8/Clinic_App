package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinicapp.DataBase.DataBase;

public class deleteService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_service);
    }

    public void cancelClick(View view){
        finish();
    }

    public void finishClick(View view){
        DataBase dataBase = new DataBase(this);
        EditText userInput = (EditText) findViewById(R.id.deleteText);
        if(userInput.getText().toString().equals("")){
            Toast.makeText(deleteService.this, "You haven't input any service", Toast.LENGTH_LONG).show();
            dataBase.close();
            finish();
        }
        else{
            boolean success = dataBase.deleteService(userInput.getText().toString());
            if(success){
                Toast.makeText(deleteService.this, "Success!!!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(deleteService.this, "This service doesn't exist", Toast.LENGTH_LONG).show();
            }
            dataBase.close();
            finish();
        }
    }
}
