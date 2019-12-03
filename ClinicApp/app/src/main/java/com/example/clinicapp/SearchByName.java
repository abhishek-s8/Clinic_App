package com.example.clinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinicapp.Clinics.Clinic;
import com.example.clinicapp.DataBase.DataBase;

public class SearchByName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_name);
    }

    public void searchByN(View view){
        DataBase dataBase = new DataBase(this);
        EditText name = (EditText)findViewById(R.id.editText11);

        if(name.getText().toString().equals("")){
            Toast.makeText(SearchByName.this, "Fail, you haven't inout anything", Toast.LENGTH_LONG).show();
            name.setText("");
            return;
        }
        else{
            for(int i = 0; i < name.getText().toString().length(); i++){
                if(!Character.isDigit(name.getText().toString().charAt(i)) && !Character.isLetter(name.getText().toString().charAt(i))){
                    Toast.makeText(SearchByName.this, "Fail, invalid info", Toast.LENGTH_LONG).show();
                    name.setText("");
                    return;
                }
            }
        }

        String nameS = name.getText().toString();
        Clinic clinic;
        String display = "";
        if(dataBase.clinicExist(nameS)){
            clinic = dataBase.getClinic(nameS);
            display += "Name: " + clinic.getName() + "\nAddress: " + clinic.getAddress() + "\nRate: " + clinic.getRate();
        }
        else{
            display = "Sorry, this clinic doesn't exist";
        }
        name.setText("");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Clinic " + nameS);
        builder.setMessage(display);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });
        AlertDialog b = builder.create();
        b.show();
    }
}
