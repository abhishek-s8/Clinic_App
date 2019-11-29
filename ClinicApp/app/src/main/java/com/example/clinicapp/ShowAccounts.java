package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.clinicapp.DataBase.DataBase;

public class ShowAccounts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_accounts);
        DataBase dataBase = new DataBase(this);
        String[] accountsC = dataBase.showAllClients();
        String[] accountsE = dataBase.showAllEmployees();
        TextView textView = (TextView)findViewById(R.id.textView9);
        if (accountsC == null && accountsE == null) {
            textView.setText("There is no account yet.");
        }
        else if(accountsC == null){
            String e = "Employees:\n";
            for(int i = 0; i < accountsE.length; i++){
                e += accountsE[i];
                if(i != accountsE.length-1){
                    e += "\n";
                }
            }
            textView.setText(e);
        }
        else if(accountsE == null){
            String c = "Patients:\n";
            for(int i = 0; i < accountsC.length; i++){
                c += accountsC[i];
                if(i != accountsC.length-1){
                    c += "\n";
                }
            }
            textView.setText(c);
        }
        else{
            String c = "Patients:\n";
            for(int i = 0; i < accountsC.length; i++){
                c += accountsC[i];
                if(i != accountsC.length-1){
                    c += "\n";
                }
            }
            String e = "\n\nEmployees:\n";
            for(int i = 0; i < accountsE.length; i++){
                e += accountsE[i];
                if(i != accountsE.length-1){
                    e += "\n";
                }
            }
            textView.setText(c + e);
        }
    }

    public void fiOnClick(View view){
        finish();
    }
}
