package com.example.clinicapp;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myDBHelper dataBase=new myDBHelper(this);
    }
    public void loginOnClick(View view){
        EditText userName = (EditText)findViewById(R.id.userName);
        EditText password = (EditText)findViewById(R.id.password);

        myDBHelper dataBase=new myDBHelper(this);
        Admin admin= dataBase.adminExist(userName.getText().toString(), password.getText().toString());
        Clients client=dataBase.clientExist(userName.getText().toString(), password.getText().toString());
        Employee employee =dataBase.employeeExist(userName.getText().toString(), password.getText().toString());

        if (client == null && employee == null && admin == null)
        {
            AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);

            theBuilder.setTitle("Login Failed");
            theBuilder.setMessage("Please Try Again");

            theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });
            AlertDialog builder = theBuilder.create();
            builder.show();
        }
        else if(employee!= null){

            userName.setText("");
            password.setText("");

            String name = employee.getName();
            Intent temp = new Intent(this, EmployeeLogin.class);

            temp.putExtra("Name", name);
            startActivity(temp);
        }
        else if(client != null){

            password.setText("");
            userName.setText("");

            String name = client.getName();
            Intent temp = new Intent(this, ClientLogin.class);

            temp.putExtra("Name", name);
            startActivity(temp);
        }
        else if(admin != null){

            password.setText("");
            userName.setText("");

            String name = admin.getName();
            Intent temp = new Intent(this, AdminLogin.class);

            temp.putExtra("Name", name);
            startActivity(temp);
        }
    }

    public void servicesOnClick(View view){

        AlertDialog.Builder theBuilder=new AlertDialog.Builder(this);
        theBuilder.setTitle("All services provided by clinic");

        Clinic clinic = new Clinic();
        String service = "";
        ArrayList<List> services = clinic.getServices();

        for(int i = 0; i < services.size(); i++)
        {

            service+=i+1;
            service+=". ";

            if(i != services.size())
            {
                service+=services.get(i).get(0);
                service+=services.get(i).get(1);
                service+="\n";
            }
            else {
                service+=services.get(i).get(0);
                service+=services.get(i).get(1);
            }
        }

        theBuilder.setMessage(service);

        theBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });

        AlertDialog builder=theBuilder.create();

        builder.show();
    }

    public void createOnClick(View view) {
        Intent temp=new Intent(this, LoginOptions.class);
        startActivity(temp);
    }
}
