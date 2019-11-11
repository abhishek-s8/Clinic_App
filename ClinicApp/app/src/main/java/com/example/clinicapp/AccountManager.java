package com.example.clinicapp;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.Toast;

public class AccountManager extends AppCompatActivity {

    private RadioButton employee;
    private RadioButton client;

    private RadioButton create;
    private RadioButton delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_manager);
    }

    public void finishClick(View view){
        employee = (RadioButton)findViewById(R.id.employeeBtn);
        client = (RadioButton)findViewById(R.id.clientBtn);

        delete = (RadioButton)findViewById(R.id.deleteBtn);
        create = (RadioButton)findViewById(R.id.createBtn);

        myDBHelper dataBase = new myDBHelper(this);

        if(delete.isChecked())
        {
            EditText userName = (EditText)findViewById(R.id.UserName);

            if(userName.getText().toString().equals(""))
            {
                Toast.makeText(AccountManager.this, "User name was NOT entered", Toast.LENGTH_LONG).show();
                finish();
            }
            else if(client.isChecked())
            {
                dataBase.remove("Client", userName.getText().toString());
                Toast.makeText(AccountManager.this, "Complete", Toast.LENGTH_LONG).show();
                finish();
            }
            else if(employee.isChecked())
            {
                dataBase.remove("Employee", userName.getText().toString());
                Toast.makeText(AccountManager.this, "Complete", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        else if(create.isChecked()){
            Intent intent = new Intent(this, LoginOptions.class);
            startActivity(intent);
            finish();
        }
    }

    public void cancelClick(View view)
    {
        finish();
    }
}
