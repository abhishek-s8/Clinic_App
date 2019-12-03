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

public class SearchByService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_service);
    }

    public void searchByService(View view){
        DataBase dataBase = new DataBase(this);
        ArrayList<String> clinics = new ArrayList<>();
        EditText service = (EditText)findViewById(R.id.editText);
        if(service.getText().toString().equals("")){
            Toast.makeText(SearchByService.this, "Fail, you haven't inout anything", Toast.LENGTH_LONG).show();
            service.setText("");
            return;
        }
        else{
            for(int i = 0; i < service.getText().toString().length(); i++){
                if(!Character.isDigit(service.getText().toString().charAt(i)) && !Character.isLetter(service.getText().toString().charAt(i)) && service.getText().toString().charAt(i) != ' '){
                    Toast.makeText(SearchByService.this, "Fail, invalid info", Toast.LENGTH_LONG).show();
                    service.setText("");
                    return;
                }
            }
        }
        clinics = dataBase.ByService(service.getText().toString());
        String c = "";
        if(clinics == null){
            c = "Sorry, there is no clinic provide this service";
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
        builder.setTitle("List of clinic provide " + service.getText().toString());
        builder.setMessage(c);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });
        AlertDialog b = builder.create();
        service.setText("");
        b.show();
    }
}
