package com.example.clinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinicapp.Clinics.Clients;
import com.example.clinicapp.DataBase.DataBase;

public class SignupClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_client);
    }

    public void doneOnClick(View view){
        final DataBase dataBase = new DataBase(this);
        final Clients client = new Clients();

        EditText userName = (EditText) findViewById(R.id.UserName);
        EditText password = (EditText) findViewById(R.id.Password);
        EditText name = (EditText) findViewById(R.id.Name);
        EditText age = (EditText) findViewById(R.id.Age);
        String userNameS = userName.getText().toString();
        String passwordS = password.getText().toString();
        String nameS = name.getText().toString();
        String ageS = age.getText().toString();
        if(Integer.parseInt(ageS) >= 100){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Input");
            builder.setMessage("No information was entered, please try again.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });
            AlertDialog b = builder.create();
            userName.setText("");
            password.setText("");
            name.setText("");
            age.setText("");
            b.show();
            return;
        }

        if(userNameS.equals("") && passwordS.equals("") && nameS.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Input");
            builder.setMessage("No information was entered, please try again.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });
            AlertDialog b = builder.create();
            userName.setText("");
            password.setText("");
            name.setText("");
            age.setText("");
            b.show();
            return;
        }
        else if(userNameS.length() > 50 && passwordS.length() > 50 && nameS.length() > 50){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Input");
            builder.setMessage("The information you entered are too long (only 50 characters max), please try again.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });
            AlertDialog b = builder.create();
            userName.setText("");
            password.setText("");
            name.setText("");
            age.setText("");
            b.show();
            return;
        }
        else {
            for(int i = 0; i <= userNameS.length()-1; i++){
                if(!Character.isDigit(userNameS.charAt(i)) && !Character.isLetter(userNameS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("The username you entered is invalid (Only letters and digits, NO SPACES), please try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });
                    AlertDialog b = builder.create();
                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    age.setText("");
                    b.show();
                    return;
                }
            }
            for(int i = 0; i <= passwordS.length()-1; i++){
                if(!Character.isDigit(passwordS.charAt(i)) && !Character.isLetter(passwordS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("The password you entered is invalid (Only letters and digits, NO SPACES), please try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });
                    AlertDialog b = builder.create();
                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    age.setText("");
                    b.show();
                    return;
                }
            }
            for(int i = 0; i <= nameS.length()-1; i++){
                if(!Character.isDigit(nameS.charAt(i)) && !Character.isLetter(nameS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("The name you entered is invalid (Only letters and digits, NO SPACES), please try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });
                    AlertDialog b = builder.create();
                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    age.setText("");
                    b.show();
                    return;
                }
            }
            for (int i = 0; i <= ageS.length()-1; i++){
                if(!Character.isDigit(ageS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("The age you entered is invalid (Only digits, NO SPACES), please try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });
                    AlertDialog b = builder.create();
                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    age.setText("");
                    b.show();
                    return;
                }
            }
            if(dataBase.cExist(userNameS)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Invalid Input");
                builder.setMessage("This user name already exists, please try again or log in.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0, int arg1){}
                });
                AlertDialog b = builder.create();
                userName.setText("");
                password.setText("");
                name.setText("");
                age.setText("");
                b.show();
                return;
            }
            else{
                client.setUserName(userNameS);
                client.setPassword(passwordS);
                client.setName(nameS);
                int i = Integer.parseInt(ageS);
                client.setAge(i);
                dataBase.insertClient(client);
                Toast.makeText(SignupClient.this, "Complete", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
