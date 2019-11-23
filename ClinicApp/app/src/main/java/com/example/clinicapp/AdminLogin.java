package com.example.clinicapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class AdminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        Intent var=getIntent();
        String name=var.getStringExtra("Name");

        TextView textView = (TextView)findViewById(R.id.textView10);
        TextView welcome = (TextView)findViewById(R.id.textView11);
        welcome.setText("Welcome "+name+"!");
        textView.setText("You are logged in as Admin.");
    }

    public void editClick(View view)
    {
        Intent var=new Intent(this, EditService.class);
        startActivity(var);
    }

    public void showClick(View view)
    {
        Intent var=new Intent(this, ShowAccounts.class);
        startActivity(var);
    }

    public void accountClick(View view)
    {
        Intent var=new Intent(this, AccountManager.class);
        startActivity(var);
    }

    public void addClick(View view)
    {
        Intent var=new Intent(this, AddService.class);
        startActivity(var);
    }

    public void deleteClick(View view)
    {
        Intent var=new Intent(this, DeleteService.class);
        startActivity(var);
    }

    public void servicesClick(View view)
    {
        AlertDialog.Builder theBuilder=new AlertDialog.Builder(this);
        theBuilder.setTitle("Services provided:");
        String service="";

        myDBHelper dataBase=new myDBHelper(this);
        String[] services=dataBase.showServices();

        for(int i=0; i<services.length;i++)
        {
            service+=i+1;
            service+=". ";

            if(i!=services.length)
            {
                service+=services[i];
                service+="\n";
            }
            else{
                service+=services[i];
            }
        }

        theBuilder.setMessage(service);

        theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });

        AlertDialog build=theBuilder.create();

        build.show();
    }

}
