package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.clinicapp.Clinics.Clients;
import com.example.clinicapp.Clinics.Employee;
import com.example.clinicapp.DataBase.DataBase;

import java.util.List;

public class ShowAccounts extends AppCompatActivity {
    private ListView listViewEmployee;
    private ListView listViewClient;
    private EmployeeList employeeList;
    private ClientList clientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DataBase dataBase = new DataBase(this);
        List<Employee> employeeList =dataBase.showAllEmployees();
        List<Clients> clientList =dataBase.showAllClients();

        listViewEmployee = (ListView)findViewById(R.id.AccountListEmployee);
        listViewClient = (ListView)findViewById(R.id.AccountListClient);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_accounts);
        List<Clients> accountsC = dataBase.showAllClients();
        List<Employee> accountsE = dataBase.showAllEmployees();
        TextView textView = (TextView)findViewById(R.id.textView9);
        if (accountsC == null && accountsE == null) {
            textView.setText("There is no account yet.");
        }
        else if(accountsC == null){
            String e = "Employees:\n";
            for(int i = 0; i < accountsE.size(); i++){
                e += accountsE.get(i).getUserName();
                if(i != accountsE.size()-1){
                    e += "\n";
                }
            }
            textView.setText(e);
        }
        else if(accountsE == null){
            String c = "Patients:\n";
            for(int i = 0; i < accountsC.size(); i++){
                c += accountsC.get(i).getUserName();
                if(i != accountsC.size()-1){
                    c += "\n";
                }
            }
            textView.setText(c);
        }
        else{
            String c = "Patients:\n";
            for(int i = 0; i < accountsC.size(); i++){
                c += accountsC.get(i).getUserName();
                if(i != accountsC.size()-1){
                    c += "\n";
                }
            }
            String e = "\n\nEmployees:\n";
            for(int i = 0; i < accountsE.size(); i++){
                e += accountsE.get(i).getUserName();
                if(i != accountsE.size()-1){
                    e += "\n";
                }
            }
            textView.setText(c + e);
        }
    }
/*
    @Override
    protected void onStart() {
        super.onStart();

        DataBase dataBase = new DataBase(this);
        dataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products.clear();

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Product product = postSnapshot.getValue(Product.class);
                    products.add(product);
                }

                ProductList productAdapter = new ProductList(MainActivity.this, products);
                listViewProducts.setAdapter(productAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
*/
    public void fiOnClick(View view){
        finish();
    }
}
