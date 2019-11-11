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

        Intent var = getIntent();
        String name = var.getStringExtra("Name");

        TextView textView = (TextView)findViewById(R.id.textView5);
        textView.setText("Welcome "+name+"! You are logged in as Admin.");
    }

    public void editClick(View view)
    {
        Intent var=new Intent(this, EditService.class);
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

    public void showClick(View view)
    {
        Intent var =new Intent(this, ShowAccounts.class);
        startActivity(var);
    }

    public void accountClick(View view)
    {
        Intent var=new Intent(this, AccountManager.class);
        startActivity(var);
    }

    public void servicesClick(View view)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Services provided:");

        String service = "";
        Clinic theClinic=new Clinic();
        ArrayList<List> theServices=theClinic.getServices();

        for (int i=0; i<theServices.size(); i++)
        {
            service+=i+1;
            service+=". ";

            if (i!=theServices.size())
            {
                service += theServices.get(i).get(0);
                service += theServices.get(i).get(1);
                service += "\n";
            }
            else {
                service += theServices.get(i).get(0);
                service += theServices.get(i).get(1);
            }
        }
        builder.setMessage(service);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        AlertDialog b = builder.create();
        b.show();
    }

}
