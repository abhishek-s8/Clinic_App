package com.example.clinicapp;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignupClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_client);
    }

    public void doneClick(View view)
    {
        EditText name = (EditText) findViewById(R.id.Name);
        EditText age = (EditText) findViewById(R.id.Age);
        EditText userName = (EditText) findViewById(R.id.UserName);
        EditText password = (EditText) findViewById(R.id.Password);

        final myDBHelper dataBase = new myDBHelper(this);
        final Clients client = new Clients();

        String userNameS = userName.getText().toString();
        String passwordS = password.getText().toString();
        String nameS = name.getText().toString();
        String ageS = age.getText().toString();

        if(userNameS.equals("") && passwordS.equals("") && nameS.equals(""))
        {
            AlertDialog.Builder theBuilder=new AlertDialog.Builder(this);
            theBuilder.setTitle("Invalid Input");

            theBuilder.setMessage("No information entered.");

            theBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0,int arg1){}
            });

            AlertDialog builder=theBuilder.create();

            name.setText("");
            age.setText("");
            userName.setText("");
            password.setText("");

            builder.show();
            return;
        }
        else if(userNameS.length()>25 && passwordS.length()>25 && nameS.length()>25)
        {
            AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);

            theBuilder.setTitle("Invalid Input");
            theBuilder.setMessage("Entry is too long. Try again.");

            theBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });

            AlertDialog build = theBuilder.create();

            name.setText("");
            age.setText("");
            userName.setText("");
            password.setText("");

            build.show();
            return;
        }
        else {
            for(int i = 0; i <= userNameS.length()-1; i++)
            {
                if(!Character.isDigit(userNameS.charAt(i))&&!Character.isLetter(userNameS.charAt(i)))
                {

                    AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);

                    theBuilder.setTitle("Invalid Input");
                    theBuilder.setMessage("Entry is invalid. Try again.");

                    theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });

                    AlertDialog builder = theBuilder.create();

                    password.setText("");
                    name.setText("");
                    userName.setText("");
                    age.setText("");
                    builder.show();

                    return;
                }
            }
            for(int i = 0; i <= passwordS.length()-1; i++)
            {
                if(!Character.isDigit(passwordS.charAt(i))&&!Character.isLetter(passwordS.charAt(i)))
                {
                    AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);

                    theBuilder.setTitle("Invalid Input");
                    theBuilder.setMessage("Entry is invalid. Try again.");

                    theBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });

                    AlertDialog builder = theBuilder.create();

                    name.setText("");
                    age.setText("");
                    userName.setText("");
                    password.setText("");
                    builder.show();

                    return;
                }
            }
            for(int i = 0; i <= nameS.length()-1; i++)
            {
                if(!Character.isDigit(nameS.charAt(i)) && !Character.isLetter(nameS.charAt(i)))
                {
                    AlertDialog.Builder theBuilder=new AlertDialog.Builder(this);

                    theBuilder.setTitle("Invalid Input");
                    theBuilder.setMessage("Entry is invalid. Try again.");

                    theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });

                    AlertDialog builder = theBuilder.create();

                    name.setText("");
                    age.setText("");
                    userName.setText("");
                    password.setText("");
                    builder.show();

                    return;
                }
            }
            for (int i = 0; i <= ageS.length()-1; i++)
            {
                if(!Character.isDigit(ageS.charAt(i)))
                {
                    AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);

                    theBuilder.setTitle("Invalid Input");
                    theBuilder.setMessage("The information you entered are invalid, please try again.");

                    theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });

                    AlertDialog builder = theBuilder.create();

                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    age.setText("");
                    builder.show();

                    return;
                }
            }
            if(dataBase.cExist(userNameS))
            {
                AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);

                theBuilder.setTitle("Invalid Input");
                theBuilder.setMessage("User name exists. Try again.");

                theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0, int arg1){}
                });

                AlertDialog build = theBuilder.create();

                password.setText("");
                name.setText("");
                userName.setText("");
                age.setText("");

                build.show();
                return;
            }
            else{
                client.setPassword(password.getText().toString());
                client.setUserName(userName.getText().toString());
                client.setName(name.getText().toString());

                dataBase.insertClient(client);
                Toast.makeText(SignupClient.this,"Complete",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
