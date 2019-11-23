package com.example.clinicapp;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private EditText password;
    private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void loginOnClick(View view){
        userName = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.password);

        myDBHelper dataBase=new myDBHelper(this);
        Admin admin=dataBase.adminExist(userName.getText().toString(), password.getText().toString());
        Clients client=dataBase.clientExist(userName.getText().toString(), password.getText().toString());
        Employee employee=dataBase.employeeExist(userName.getText().toString(), password.getText().toString());

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

        myDBHelper dataBase=new myDBHelper(this);
        String[] services=dataBase.showServices();
        String service="";

        for(int i=0;i<services.length;i++) {

            service+=i+1;
            service+=". ";

            if (i!=services.length)
            {
                service+=services[i];
                service+="\n";
            } else {
                service+=services[i];
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
        EditText password = (EditText)findViewById(R.id.password);
        EditText userName = (EditText)findViewById(R.id.userName);
        userName.setText("");
        password.setText("");
        Intent intent=new Intent(this,LoginOptions.class);
        startActivity(intent);
    }
}
