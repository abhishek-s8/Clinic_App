package com.example.clinicapp;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
public class LoginOptions extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_options);
    }
    public void employeeClick(View view){
        Intent temp=new Intent(this,SignupEmployee.class);
        startActivity(temp);
        finish();
    }
    public void clientClick(View view){
        Intent temp=new Intent(this,SignupClient.class);
        startActivity(temp);
        finish();
    }
}
