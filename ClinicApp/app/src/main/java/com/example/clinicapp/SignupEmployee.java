package com.example.clinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinicapp.Clinics.Clinic;
import com.example.clinicapp.Clinics.Employee;
import com.example.clinicapp.DataBase.DataBase;

public class SignupEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_employee);
    }

    public void newClinic(View view){
        final DataBase dataBase = new DataBase(this);
        LayoutInflater li = LayoutInflater.from(this);
        View newClinic = li.inflate(R.layout.add_clinic, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Sign up new clinic");
        alertDialogBuilder.setView(newClinic);
        final EditText nameOfClinic = (EditText) newClinic.findViewById(R.id.editText700);
        final EditText address = (EditText) newClinic.findViewById(R.id.editText800);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                boolean pass = true;
                if(nameOfClinic.getText().toString().equals("") || address.getText().toString().equals("")){
                    Toast.makeText(SignupEmployee.this, "You have NOT input anything", Toast.LENGTH_LONG).show();
                    pass = false;
                }
                else{
                    for(int i = 0; i < nameOfClinic.getText().toString().length(); i++){
                        if(!Character.isDigit(nameOfClinic.getText().toString().charAt(i)) && !Character.isLetter(nameOfClinic.getText().toString().charAt(i)) && !Character.isSpace(nameOfClinic.getText().toString().charAt(i))){
                            Toast.makeText(SignupEmployee.this, "Error with Name of Clinic (Only Letters)", Toast.LENGTH_LONG).show();
                            pass = false;
                        }
                    }
                    for(int i = 0; i < address.getText().toString().length(); i++){
                        if(!Character.isDigit(address.getText().toString().charAt(i)) && !Character.isLetter(address.getText().toString().charAt(i)) && !Character.isDigit(address.getText().toString().charAt(i)) && !Character.isSpace(address.getText().toString().charAt(i))){
                            Toast.makeText(SignupEmployee.this, "Error with Address (Only Letters and Numbers)", Toast.LENGTH_LONG).show();
                            pass = false;
                        }
                    }
                }
                if(pass) {
                    if (dataBase.clinicExist(nameOfClinic.getText().toString())) {
                        Toast.makeText(SignupEmployee.this, "This clinic already exists!", Toast.LENGTH_LONG).show();
                    } else {
                        Clinic clinic = new Clinic();
                        clinic.setName(nameOfClinic.getText().toString());
                        clinic.setAddress(address.getText().toString());
                        dataBase.insertClinic(clinic);
                        Toast.makeText(SignupEmployee.this, "Complete", Toast.LENGTH_LONG).show();
                    }
                }
                dataBase.close();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void doneOnClick(View view) {
        final DataBase dataBase = new DataBase(this);
        final Employee employee = new Employee();

        EditText userName = (EditText) findViewById(R.id.UserName);
        EditText password = (EditText) findViewById(R.id.Password);
        EditText name = (EditText) findViewById(R.id.Name);
        EditText address = (EditText) findViewById(R.id.addressText);
        EditText phoneNum = (EditText) findViewById(R.id.phoneNumText);
        EditText nameOfClinic = (EditText) findViewById(R.id.nameOfClinicText);
        EditText insuranceType = (EditText) findViewById(R.id.insuranceText);
        EditText paymentMethod = (EditText) findViewById(R.id.paymentText);

        String userNameS = userName.getText().toString();
        String passwordS = password.getText().toString();
        String nameS = name.getText().toString();
        String addressS = address.getText().toString();
        String phoneNumS = phoneNum.getText().toString();
        String nameOfClinicS = nameOfClinic.getText().toString();
        String insuranceS = insuranceType.getText().toString();
        String paymentS = paymentMethod.getText().toString();

        if(userNameS.equals("") || passwordS.equals("") || nameS.equals("") || addressS.equals("") || phoneNumS.equals("") || nameOfClinicS.equals("") || insuranceS.equals("") || paymentS.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Input");
            builder.setMessage("No information was entered. Please try again.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });
            AlertDialog b = builder.create();
            userName.setText("");
            password.setText("");
            name.setText("");
            address.setText("");
            phoneNum.setText("");
            insuranceType.setText("");
            nameOfClinic.setText("");
            paymentMethod.setText("");
            b.show();
            return;
        }
        else if(userNameS.length() > 50 || passwordS.length() > 50 || nameS.length() > 50 || addressS.length() > 50 || phoneNumS.length() != 10 || nameOfClinicS.length() > 50 || insuranceS.length() > 50 || paymentS.length() > 50){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Input");
            builder.setMessage("Can only be 50 characters maximum. Please try again.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });
            AlertDialog b = builder.create();
            userName.setText("");
            password.setText("");
            name.setText("");
            address.setText("");
            phoneNum.setText("");
            insuranceType.setText("");
            nameOfClinic.setText("");
            paymentMethod.setText("");
            b.show();
            return;
        }
        else if(!dataBase.clinicExist(nameOfClinicS)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Input");
            builder.setMessage("The clinic doesn't exist. Please try again.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });
            AlertDialog b = builder.create();
            userName.setText("");
            password.setText("");
            name.setText("");
            address.setText("");
            phoneNum.setText("");
            insuranceType.setText("");
            nameOfClinic.setText("");
            paymentMethod.setText("");
            b.show();
            return;
        }
        else {
            for(int i = 0; i <= userNameS.length()-1; i++){
                if(!Character.isDigit(userNameS.charAt(i)) && !Character.isLetter(userNameS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Error with UserName (only Digits and Letters, NO SPACES). Please try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });
                    AlertDialog b = builder.create();
                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    return;
                }
            }
            for(int i = 0; i <= passwordS.length()-1; i++){
                if(!Character.isDigit(passwordS.charAt(i)) && !Character.isLetter(passwordS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Error with Password (only Digits and Letters, NO SPACES). Please try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });
                    AlertDialog b = builder.create();
                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    return;
                }
            }
            for(int i = 0; i <= nameS.length()-1; i++){
                if(!Character.isDigit(nameS.charAt(i)) && !Character.isLetter(nameS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Error with Name (only Digits and Letters, NO SPACES). Please try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });
                    AlertDialog b = builder.create();
                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    return;
                }
            }
            for(int i = 0; i <= addressS.length()-1; i++){
                if(!Character.isDigit(addressS.charAt(i)) && !Character.isLetter(addressS.charAt(i)) && !Character.isSpace(addressS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Error with Address (only Digits and Letters). Please try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });
                    AlertDialog b = builder.create();
                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    return;
                }
            }
            for(int i = 0; i <= phoneNumS.length()-1; i++){
                if(!Character.isDigit(phoneNumS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Error with Phone Number (only 10 Digits, NO SPACES). Please try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });
                    AlertDialog b = builder.create();
                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    return;
                }
            }
            for(int i = 0; i <= nameOfClinicS.length()-1; i++){
                if(!Character.isDigit(nameOfClinicS.charAt(i)) && !Character.isLetter(nameOfClinicS.charAt(i)) && !Character.isSpace(nameOfClinicS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Error with Clinic Name (only Digits and Letters, make sure Clinic exists). Please try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });
                    AlertDialog b = builder.create();
                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    return;
                }
            }
            for(int i = 0; i <= insuranceS.length()-1; i++){
                if(!Character.isDigit(insuranceS.charAt(i)) && !Character.isLetter(insuranceS.charAt(i)) && !Character.isSpace(insuranceS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Error with Insurance (only Digits and Letters). Please try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });
                    AlertDialog b = builder.create();
                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    return;
                }
            }
            for(int i = 0; i <= paymentS.length()-1; i++){
                if(!Character.isDigit(paymentS.charAt(i)) && !Character.isLetter(paymentS.charAt(i)) && !Character.isSpace(paymentS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Error with Payment (only Digits and Letters). Please try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1){}
                    });
                    AlertDialog b = builder.create();
                    userName.setText("");
                    password.setText("");
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    return;
                }
            }
            if(dataBase.eExist(userNameS)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Invalid Input");
                builder.setMessage("This user name already exists. Please try again or log in.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0, int arg1){}
                });
                AlertDialog b = builder.create();
                userName.setText("");
                password.setText("");
                name.setText("");
                address.setText("");
                phoneNum.setText("");
                insuranceType.setText("");
                nameOfClinic.setText("");
                paymentMethod.setText("");
                b.show();
                return;
            }
            else{
                employee.setUserName(userName.getText().toString());
                employee.setPassword(password.getText().toString());
                employee.setName(name.getText().toString());
                employee.setAddress(addressS);
                employee.setInsuranceTypes(insuranceS);
                employee.setPhoneNum(Long.valueOf(phoneNumS.trim()).longValue());
                employee.setNameOfClinic(nameOfClinicS);
                employee.setPaymentMethod(paymentS);
                dataBase.insertEmployee(employee);
                Toast.makeText(SignupEmployee.this, "Complete", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
