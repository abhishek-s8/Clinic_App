package com.example.clinicapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.clinicapp.Clinics.Clients;

import java.util.List;

public class ClientList extends ArrayAdapter<Clients> {
    private Activity context;
    private List<Clients> accounts;

    public ClientList(Activity context, List<Clients> accounts) {
        super(context, R.layout.account_list_layout, accounts);
        this.context = context;
        this.accounts = accounts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.account_list_layout, null, true);

        TextView userName = (TextView) listViewItem.findViewById(R.id.textView26);
        TextView name = (TextView) listViewItem.findViewById(R.id.textView27);
        TextView role = (TextView) listViewItem.findViewById(R.id.textView28);

        role.setText("Patient");

        Clients clients = accounts.get(position);
        userName.setText(clients.getUserName());
        name.setText(clients.getName());
        return listViewItem;
    }
}
