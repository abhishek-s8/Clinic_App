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

public class SearchByWorkingHour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_working_hour);
    }

    public void ByWH(View view){
        DataBase dataBase = new DataBase(this);
        ArrayList<String> clinics = new ArrayList<>();
        EditText workingHour = (EditText)findViewById(R.id.editText5);
        String workingHourS = workingHour.getText().toString();
        if(workingHourS.equals("")){
            Toast.makeText(SearchByWorkingHour.this, "Fail, you haven't inout anything", Toast.LENGTH_LONG).show();
            workingHour.setText("");
            return;
        }
        else if(workingHourS.length() != 8){
            Toast.makeText(SearchByWorkingHour.this, "Fail, invalid info", Toast.LENGTH_LONG).show();
            workingHour.setText("");
            return;
        }
        else{
            for(int i = 0; i < workingHour.getText().toString().length(); i++){
                if(!Character.isDigit(workingHourS.charAt(i))){
                    Toast.makeText(SearchByWorkingHour.this, "Fail, invalid info", Toast.LENGTH_LONG).show();
                    workingHour.setText("");
                    return;
                }
            }
        }
        clinics = dataBase.ByWorkingHour(workingHour.getText().toString());
        String c = "";
        if(clinics == null){
            c = "Sorry, there is no clinic working at this time";
        }
        else {
            for (int i = 0; i < clinics.size(); i++) {
                c += i + 1;
                c += ". Dr.";
                if (i != clinics.size()) {
                    c += clinics.get(i);
                    c += "\n";
                } else {
                    c += clinics.get(i);
                }
            }
        }
        String time = workingHour.getText().toString();
        StringBuilder to = new StringBuilder(time);
        to.insert(4, "/");
        to.insert(7, "/");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("List of doctors working at " + to.toString());
        builder.setMessage(c);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });
        AlertDialog b = builder.create();
        workingHour.setText("");
        b.show();
    }
}
