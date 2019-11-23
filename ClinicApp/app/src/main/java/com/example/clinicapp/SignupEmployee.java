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
        EditText address = (EditText) findViewById(R.id.addressText);
        EditText phoneNum = (EditText) findViewById(R.id.phoneNumText);
        EditText nameOfClinic = (EditText) findViewById(R.id.nameOfClinicText);
        EditText insuranceType = (EditText) findViewById(R.id.insuranceText);
        EditText paymentMethod = (EditText) findViewById(R.id.paymentText);

        String phoneNumS = phoneNum.getText().toString();
        String nameOfClinicS = nameOfClinic.getText().toString();
        String nameS = name.getText().toString();
        String userNameS = userName.getText().toString();
        String addressS = address.getText().toString();
        String insuranceS = insuranceType.getText().toString();
        String paymentS = paymentMethod.getText().toString();
        String passwordS = password.getText().toString();

        final myDBHelper dataBase = new myDBHelper(this);
        final Employee employee = new Employee();

        if(userNameS.equals("") || passwordS.equals("") || nameS.equals("") || addressS.equals("") || phoneNumS.equals("") || nameOfClinicS.equals("") || insuranceS.equals("") || paymentS.equals("")){

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
            address.setText("");
            phoneNum.setText("");
            insuranceType.setText("");
            nameOfClinic.setText("");
            paymentMethod.setText("");

            builder.show();
            return;
        }
        else if(userNameS.length()>25||passwordS.length()>25||nameS.length()>25||addressS.length()>25||phoneNumS.length()!=10||nameOfClinicS.length()>25||insuranceS.length()>25||paymentS.length()>25){

            AlertDialog.Builder theBuilder = new AlertDialog.Builder(this);
            theBuilder.setTitle("Invalid Input");

            theBuilder.setMessage("Too long. The information entered is invalid.");

            theBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });

            AlertDialog builder = theBuilder.create();

            password.setText("");
            userName.setText("");
            name.setText("");
            address.setText("");
            phoneNum.setText("");
            insuranceType.setText("");
            nameOfClinic.setText("");
            paymentMethod.setText("");

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
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");

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
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");

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
                    address.setText("");
                    phoneNum.setText("");
                    insuranceType.setText("");
                    nameOfClinic.setText("");
                    paymentMethod.setText("");

                    builder.show();
                    return;
                }
            }
            for(int i = 0; i <= addressS.length()-1; i++){
                if(!Character.isDigit(addressS.charAt(i)) && !Character.isLetter(addressS.charAt(i))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Entry invalid. Try again. Error with Address.");
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
                    builder.setMessage("Entry invalid. Try again. Error with Phone Number.");
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
                    builder.setMessage("Entry invalid. Try again. Error with Name of Clinic.");
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
                    builder.setMessage("Entry invalid. Try again. Error with Insurance.");
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
                    builder.setMessage("Entry invalid. Try again. Error with Payment.");
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
                address.setText("");
                phoneNum.setText("");
                insuranceType.setText("");
                nameOfClinic.setText("");
                paymentMethod.setText("");

                builder.show();
                return;
            }
            else{

                employee.setPassword(password.getText().toString());
                employee.setUserName(userName.getText().toString());
                employee.setName(name.getText().toString());
                employee.setAddress(addressS);
                employee.setInsuranceTypes(insuranceS);
                employee.setPhoneNumber(Long.valueOf(phoneNumS.trim()).longValue());
                employee.setClinicName(nameOfClinicS);
                employee.setPaymentMethod(paymentS);

                dataBase.insertEmployee(employee);

                Toast.makeText(SignupEmployee.this, "Complete", Toast.LENGTH_LONG).show();

                finish();
            }
        }
    }
}
