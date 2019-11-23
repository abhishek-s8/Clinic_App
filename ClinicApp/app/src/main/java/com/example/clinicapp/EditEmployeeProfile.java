package com.example.clinicapp;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditEmployeeProfile extends AppCompatActivity {

    private String Name;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_employee_profiles);
        Intent intent = getIntent();

        userName=intent.getStringExtra("userName");
        Name=intent.getStringExtra("Name");
    }

    public void doneEditOnClick(View view){
        myDBHelper dataBase = new myDBHelper(this);

        EditText nameOfClinic=(EditText) findViewById(R.id.nameOfClinicEText);
        EditText insuranceType=(EditText) findViewById(R.id.insuranceEText);
        EditText address=(EditText) findViewById(R.id.addressEText);
        EditText phoneNum=(EditText) findViewById(R.id.phoneNumEText);
        EditText paymentMethod=(EditText) findViewById(R.id.paymentEText);
        EditText name=(EditText) findViewById(R.id.nameEText);

        String nameOfClinicS = nameOfClinic.getText().toString();
        String insuranceS = insuranceType.getText().toString();
        String addressS = address.getText().toString();
        String phoneNumS = phoneNum.getText().toString();
        String paymentS = paymentMethod.getText().toString();
        String nameS = name.getText().toString();

        if(nameS.length()>25||addressS.length()>25||phoneNumS.length()!= 10||nameOfClinicS.length()>25||insuranceS.length()>25||paymentS.length()>25){

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Invalid Input");
            builder.setMessage("Information entered is too long!");

            builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0,int arg1){}
            });

            AlertDialog theBuilder = builder.create();

            nameOfClinic.setText("");
            paymentMethod.setText("");
            phoneNum.setText("");
            insuranceType.setText("");
            name.setText("");
            address.setText("");

            theBuilder.show();

            Intent theIntent=new Intent(this, EmployeeLogin.class);
            theIntent.putExtra("userName", userName);
            startActivity(theIntent);

            finish();
            return;
        }

        if(!nameS.equals("")) {
            for (int i=0;i<=nameS.length()-1;i++) {

                if (!Character.isDigit(nameS.charAt(i))&&!Character.isLetter(nameS.charAt(i)))
                {

                    AlertDialog.Builder builder=new AlertDialog.Builder(this);

                    builder.setTitle("Invalid Input");
                    builder.setMessage("Information entered is invalid!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });

                    AlertDialog theBuilder=builder.create();

                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    name.setText("");
                    address.setText("");

                    theBuilder.show();

                    Intent theIntent=new Intent(this,EmployeeLogin.class);

                    theIntent.putExtra("userName",userName);
                    startActivity(theIntent);
                    finish();
                    return;
                }
            }

            dataBase.update("Employee","userName",userName,"name",nameS);

            Name=nameS;
        }
        if(!addressS.equals("")){
            for (int i=0;i<=addressS.length()-1;i++) {
                if (!Character.isDigit(addressS.charAt(i))&&!Character.isLetter(addressS.charAt(i))) {

                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Information entered is invalid!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });

                    AlertDialog theBuilder = builder.create();

                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    name.setText("");
                    address.setText("");

                    theBuilder.show();

                    Intent theIntent = new Intent(this, EmployeeLogin.class);
                    theIntent.putExtra("userName", userName);
                    startActivity(theIntent);

                    finish();
                    return;
                }
            }

            dataBase.update("Employee","userName",userName,"address",addressS);
        }
        if(!phoneNumS.equals("")) {
            for (int i=0;i<=phoneNumS.length()-1;i++) {
                if (!Character.isDigit(phoneNumS.charAt(i))) {

                    AlertDialog.Builder builder=new AlertDialog.Builder(this);

                    builder.setTitle("Invalid Input");
                    builder.setMessage("Information entered is invalid!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });

                    AlertDialog theBuilder=builder.create();

                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    name.setText("");
                    address.setText("");

                    theBuilder.show();

                    Intent theIntent = new Intent(this, EmployeeLogin.class);
                    theIntent.putExtra("userName", userName);
                    startActivity(theIntent);

                    finish();
                    return;
                }
            }
            dataBase.updatePhoneNum(userName,Long.valueOf(phoneNumS.trim()).longValue());
        }
        if(!nameOfClinicS.equals("")) {
            for (int i=0;i<=nameOfClinicS.length()-1;i++) {
                if (!Character.isDigit(nameOfClinicS.charAt(i))&&!Character.isLetter(nameOfClinicS.charAt(i))) {

                    AlertDialog.Builder builder=new AlertDialog.Builder(this);

                    builder.setTitle("Invalid Input");
                    builder.setMessage("Information entered is invalid!");

                    builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });

                    AlertDialog theBuilder=builder.create();

                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    name.setText("");
                    address.setText("");

                    theBuilder.show();
                    Intent theIntent=new Intent(this,EmployeeLogin.class);

                    theIntent.putExtra("userName", userName);
                    startActivity(theIntent);

                    finish();
                    return;
                }
            }
            dataBase.update("Employee", "userName", userName, "nameOfClinic", nameOfClinicS);
        }
        if(!insuranceS.equals("")) {
            for (int i=0; i<=insuranceS.length()-1;i++) {
                if (!Character.isDigit(insuranceS.charAt(i))&&!Character.isLetter(insuranceS.charAt(i))) {

                    AlertDialog.Builder builder=new AlertDialog.Builder(this);

                    builder.setTitle("Invalid Input");
                    builder.setMessage("Information entered is invalid!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });

                    AlertDialog theBuilder=builder.create();

                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    name.setText("");
                    address.setText("");

                    theBuilder.show();

                    Intent theIntent=new Intent(this, EmployeeLogin.class);
                    theIntent.putExtra("userName", userName);
                    startActivity(theIntent);

                    finish();
                    return;
                }
            }
            dataBase.update("Employee", "userName", userName, "insuranceTypes", insuranceS);
        }
        if(!paymentS.equals("")) {
            for (int i=0;i<=paymentS.length()-1;i++) {
                if (!Character.isDigit(paymentS.charAt(i))&&!Character.isLetter(paymentS.charAt(i))) {

                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Information entered is invalid!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });

                    AlertDialog theBuilder=builder.create();

                    nameOfClinic.setText("");
                    paymentMethod.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    name.setText("");
                    address.setText("");

                    theBuilder.show();

                    Intent theIntent=new Intent(this,EmployeeLogin.class);
                    theIntent.putExtra("userName",userName);
                    startActivity(theIntent);

                    finish();
                    return;
                }
            }
            dataBase.update("Employee","userName",userName,"paymentMethod",paymentS);
        }

        Toast.makeText(EditEmployeeProfile.this, "Complete",Toast.LENGTH_LONG).show();

        Intent theIntent = new Intent(this,EmployeeLogin.class);

        theIntent.putExtra("userName", userName);
        theIntent.putExtra("Name", Name);

        startActivity(theIntent);

        dataBase.close();
        finish();
    }
}
