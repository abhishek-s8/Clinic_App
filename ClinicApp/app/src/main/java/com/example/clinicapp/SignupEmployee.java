package com.example.clinicapp;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_employee);
    }

    public void doneClick(View view) {

        EditText userName = (EditText) findViewById(R.id.UserName);
        EditText password = (EditText) findViewById(R.id.Password);
        EditText name = (EditText) findViewById(R.id.Name);
        String passwordS = password.getText().toString();
        String nameS = name.getText().toString();
        String userNameS = userName.getText().toString();

        final myDBHelper dataBase = new myDBHelper(this);
        final Employee employee = new Employee();

        if(userNameS.equals("") || passwordS.equals("") || nameS.equals("")){

            AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);
            theBuilder.setTitle("Invalid Input");
            theBuilder.setMessage("No information entered. Try again.");

            theBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });

            AlertDialog builder = theBuilder.create();

            userName.setText("");
            password.setText("");
            name.setText("");

            builder.show();
            return;
        }
        else if(userNameS.length()>25||passwordS.length()>25||nameS.length()>25){

            AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);
            theBuilder.setTitle("Invalid Input");

            theBuilder.setMessage("Too long. Try again with 25 characters or less.");

            theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });

            AlertDialog builder = theBuilder.create();

            password.setText("");
            userName.setText("");
            name.setText("");

            builder.show();
            return;
        }
        else {
            for(int i = 0; i <= userNameS.length()-1; i++){
                if(!Character.isDigit(userNameS.charAt(i))&&!Character.isLetter(userNameS.charAt(i)))
                {

                    AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);

                    theBuilder.setTitle("Invalid Input");
                    theBuilder.setMessage("Entry invalid. Try again.");

                    theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });

                    AlertDialog builder = theBuilder.create();

                    userName.setText("");
                    password.setText("");
                    name.setText("");

                    builder.show();
                    return;
                }
            }
            for(int i = 0; i <= passwordS.length()-1; i++)
            {
                if(!Character.isDigit(passwordS.charAt(i)) &&!Character.isLetter(passwordS.charAt(i)))
                {
                    AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);
                    theBuilder.setTitle("Invalid Input");
                    theBuilder.setMessage("Entry invalid. Try again.");

                    theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });

                    AlertDialog builder = theBuilder.create();

                    userName.setText("");
                    password.setText("");
                    name.setText("");

                    builder.show();
                    return;
                }
            }
            for(int i = 0; i <= nameS.length()-1; i++){
                if(!Character.isDigit(nameS.charAt(i)) && !Character.isLetter(nameS.charAt(i)))
                {
                    AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);

                    theBuilder.setTitle("Invalid Input");
                    theBuilder.setMessage("Entry invalid. Try again.");

                    theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });

                    AlertDialog builder = theBuilder.create();

                    userName.setText("");
                    password.setText("");
                    name.setText("");

                    builder.show();
                    return;
                }
            }
            if(dataBase.eExist(userNameS))
            {
                AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);

                theBuilder.setTitle("Invalid Input");
                theBuilder.setMessage("Entry invalid. Try again.");

                theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0, int arg1){}
                });

                AlertDialog builder = theBuilder.create();

                password.setText("");
                name.setText("");
                userName.setText("");

                builder.show();
                return;
            }
            else{

                employee.setPassword(password.getText().toString());
                employee.setUserName(userName.getText().toString());
                employee.setName(name.getText().toString());

                dataBase.insertEmployee(employee);

                Toast.makeText(SignupEmployee.this, "Complete", Toast.LENGTH_LONG).show();

                finish();
            }
        }
    }
}
