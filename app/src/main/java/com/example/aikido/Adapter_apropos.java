package com.example.aikido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter_apropos extends ArrayAdapter<Salle> {
    Context ctx;
    public Adapter_apropos( Context context,   List<Salle> objects) {
        super(context, R.layout.model_apropos, objects);
        this.ctx=context;
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(R.layout.model_apropos , parent, false);

        TextView name=convertView.findViewById(R.id.name);
        TextView adresse=convertView.findViewById(R.id.adre);
        TextView email=convertView.findViewById(R.id.mail);
        TextView tel=convertView.findViewById(R.id.tele);
        Salle salle= getItem(position);
    name.setText(salle.getNom_s());
    adresse.setText(salle.getAdress_s());
    email.setText(salle.getEmail_s());
    tel.setText(salle.getTel_s());



        return convertView;
    }
}
