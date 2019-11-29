package com.example.clinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinicapp.Clinics.Employee;
import com.example.clinicapp.DataBase.DataBase;

public class SignupEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_employee);
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
            builder.setMessage("You haven't enter any information, please try again.");
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
            builder.setMessage("You went past the 50 character limit. Please try again!");
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
                    builder.setMessage("Username entered must only have letters and digits. Please try again.");
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
                    builder.setMessage("Password entered must only have letters and digits. Please try again.");
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
                    builder.setMessage("Name entered must only have letters. Please try again.");
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
                    builder.setMessage("Address entered must only have letters and digits. Please try again.");
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
                    builder.setMessage("Phone number entered must only have digits. Please try again.");
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
                if(!Character.isDigit(nameOfClinicS.charAt(i)) && !Character.isLetter(nameOfClinicS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Name of clinic entered must only have letters and digits. Please try again.");
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
                if(!Character.isDigit(insuranceS.charAt(i)) && !Character.isLetter(insuranceS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Insurance entered must only have letters and digits. Please try again.");
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
                if(!Character.isDigit(paymentS.charAt(i)) && !Character.isLetter(paymentS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Payment info entered must only have letters and digits. Please try again.");
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
                builder.setMessage("This user name has exist, please try again.");
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
                Toast.makeText(SignupEmployee.this, "Success!!!", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
