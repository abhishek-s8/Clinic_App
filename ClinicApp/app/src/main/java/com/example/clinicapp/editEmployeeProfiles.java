package com.example.clinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinicapp.DataBase.DataBase;

public class editEmployeeProfiles extends AppCompatActivity {
    private String userName;
    private String NameIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_employee_profiles);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        NameIn = intent.getStringExtra("Name");
    }

    public void doneEditOnClick(View view){
        DataBase dataBase = new DataBase(this);

        EditText name = (EditText) findViewById(R.id.nameEText);
        EditText address = (EditText) findViewById(R.id.addressEText);
        EditText phoneNum = (EditText) findViewById(R.id.phoneNumEText);
        EditText nameOfClinic = (EditText) findViewById(R.id.nameOfClinicEText);
        EditText insuranceType = (EditText) findViewById(R.id.insuranceEText);
        EditText paymentMethod = (EditText) findViewById(R.id.paymentEText);

        String nameS = name.getText().toString();
        String addressS = address.getText().toString();
        String phoneNumS = phoneNum.getText().toString();
        String nameOfClinicS = nameOfClinic.getText().toString();
        String insuranceS = insuranceType.getText().toString();
        String paymentS = paymentMethod.getText().toString();

        if(nameS.length() > 50 || addressS.length() > 50 || phoneNumS.length() != 10 || nameOfClinicS.length() > 50 || insuranceS.length() > 50 || paymentS.length() > 50){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Input");
            builder.setMessage("The information you entered are too long");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });
            AlertDialog b = builder.create();
            name.setText("");
            address.setText("");
            phoneNum.setText("");
            insuranceType.setText("");
            nameOfClinic.setText("");
            paymentMethod.setText("");
            b.show();
            Intent intent = new Intent(this, EmployeeLogin.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
            finish();
            return;
        }

        if(!nameS.equals("")) {
            for (int i = 0; i <= nameS.length() - 1; i++) {
                if (!Character.isDigit(nameS.charAt(i)) && !Character.isLetter(nameS.charAt(i))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("The information you entered are invalid");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    AlertDialog b = builder.create();
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    Intent intent = new Intent(this, EmployeeLogin.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
            dataBase.update("Employee", "userName", userName, "name", nameS);
            NameIn = nameS;
        }
        if(!addressS.equals("")) {
            for (int i = 0; i <= addressS.length() - 1; i++) {
                if (!Character.isDigit(addressS.charAt(i)) && !Character.isLetter(addressS.charAt(i)) && !Character.isSpace(addressS.charAt(i))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("The information you entered are invalid");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    AlertDialog b = builder.create();
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    Intent intent = new Intent(this, EmployeeLogin.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
            dataBase.update("Employee", "userName", userName, "address", addressS);
        }
        if(!phoneNumS.equals("")) {
            for (int i = 0; i <= phoneNumS.length() - 1; i++) {
                if (!Character.isDigit(phoneNumS.charAt(i))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("The information you entered are invalid");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    AlertDialog b = builder.create();
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    Intent intent = new Intent(this, EmployeeLogin.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
            dataBase.updatePhoneNum(userName, Long.valueOf(phoneNumS.trim()).longValue());
        }
        if(!nameOfClinicS.equals("")) {
            for (int i = 0; i <= nameOfClinicS.length() - 1; i++) {
                if (!Character.isDigit(nameOfClinicS.charAt(i)) && !Character.isLetter(nameOfClinicS.charAt(i)) && !Character.isSpace(nameOfClinicS.charAt(i))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("The information you entered are invalid");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    AlertDialog b = builder.create();
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    Intent intent = new Intent(this, EmployeeLogin.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
            dataBase.update("Employee", "userName", userName, "nameOfClinic", nameOfClinicS);
        }
        if(!insuranceS.equals("")) {
            for (int i = 0; i <= insuranceS.length() - 1; i++) {
                if (!Character.isDigit(insuranceS.charAt(i)) && !Character.isLetter(insuranceS.charAt(i)) && !Character.isSpace(insuranceS.charAt(i))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("The information you entered are invalid");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    AlertDialog b = builder.create();
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    Intent intent = new Intent(this, EmployeeLogin.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
            dataBase.update("Employee", "userName", userName, "insuranceTypes", insuranceS);
        }
        if(!paymentS.equals("")) {
            for (int i = 0; i <= paymentS.length() - 1; i++) {
                if (!Character.isDigit(paymentS.charAt(i)) && !Character.isLetter(paymentS.charAt(i)) && !Character.isSpace(paymentS.charAt(i))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("The information you entered are invalid");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    AlertDialog b = builder.create();
                    name.setText("");
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    b.show();
                    Intent intent = new Intent(this, EmployeeLogin.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
            dataBase.update("Employee", "userName", userName, "paymentMethod", paymentS);
        }
        Toast.makeText(editEmployeeProfiles.this, "Complete", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, EmployeeLogin.class);
        intent.putExtra("userName", userName);
        intent.putExtra("Name", NameIn);
        startActivity(intent);
        dataBase.close();
        finish();
    }
}
