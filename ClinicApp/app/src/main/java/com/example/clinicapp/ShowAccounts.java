package com.example.clinicapp;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class ShowAccounts extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_accounts);

        myDBHelper dataBase=new myDBHelper(this);

        String[] accountClient = dataBase.showC();
        String[] accountsEmployee = dataBase.showE();

        TextView textView = (TextView)findViewById(R.id.textView9);

        if (accountClient==null && accountsEmployee==null)
        {
            textView.setText("There is NO account");
        }
        else if(accountClient==null)
        {
            String e="Employees:\n";
            for(int i = 0;i < accountsEmployee.length;i++)
            {

                e+=accountsEmployee[i];

                if(i!=accountsEmployee.length-1)
                {
                    e+="\n";
                }
            }
            textView.setText(e);
        }
        else if(accountsEmployee == null)
        {
            String c = "Patients:\n";

            for(int i = 0; i < accountClient.length; i++)
            {
                c += accountClient[i];
                if(i != accountClient.length-1)
                {
                    c += "\n";
                }
            }
            textView.setText(c);
        }
        else{
            String c = "Patients:\n";

            for(int i = 0; i < accountClient.length; i++)
            {
                c += accountClient[i];

                if(i != accountClient.length-1)
                {
                    c += "\n";
                }
            }
            String e = "\n\nEmployees:\n";
            for(int i = 0; i < accountsEmployee.length; i++)
            {
                e += accountsEmployee[i];

                if(i != accountsEmployee.length-1)
                {
                    e += "\n";
                }
            }

            textView.setText(c+e);
        }
    }
    public void finishClick(View view){
        finish();
    }
}
