package com.example.clinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinicapp.DataBase.DataBase;

import java.util.ArrayList;

public class SearchByAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_address);
    }

    public void searchByAddress(View view){
        DataBase dataBase = new DataBase(this);
        ArrayList<String> clinics = new ArrayList<>();
        EditText addressOfClinic = (EditText)findViewById(R.id.editText7);
        if(addressOfClinic.getText().toString().equals("")){
            Toast.makeText(SearchByAddress.this, "Fail, you haven't inout anything", Toast.LENGTH_LONG).show();
            addressOfClinic.setText("");
            return;
        }
        else{
            for(int i = 0; i < addressOfClinic.getText().toString().length(); i++){
                if(!Character.isDigit(addressOfClinic.getText().toString().charAt(i)) && !Character.isLetter(addressOfClinic.getText().toString().charAt(i)) && !Character.isSpace(addressOfClinic.getText().toString().charAt(i))){
                    Toast.makeText(SearchByAddress.this, "Fail, invalid info", Toast.LENGTH_LONG).show();
                    addressOfClinic.setText("");
                    return;
                }
            }
        }

        clinics = dataBase.ByAddress(addressOfClinic.getText().toString());
        String c = "";
        if(clinics == null){
            c = "Sorry, there is no clinic at this location";
        }
        else {
            for (int i = 0; i < clinics.size(); i++) {
                if (i != clinics.size()) {
                    c += clinics.get(i);
                    c += "\n";
                } else {
                    c += clinics.get(i);
                }
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("List of clinic at " + addressOfClinic.getText().toString());
        builder.setMessage(c);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });
        AlertDialog b = builder.create();
        addressOfClinic.setText("");
        b.show();
    }
}
