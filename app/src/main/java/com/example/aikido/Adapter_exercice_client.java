package com.example.aikido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter_exercice_client extends ArrayAdapter<Activite> {
    Context ctx;
    public Adapter_exercice_client( Context context,  List<Activite> objects) {
        super(context, R.layout.model_exercice_client, objects);
        this.ctx=context;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(R.layout.model_exercice_client , parent, false);

        TextView nom_act=convertView.findViewById(R.id.nom_exer);
        TextView num_salle=convertView.findViewById(R.id.num_salle);
        TextView temps=convertView.findViewById(R.id.temps);
        Activite activite=getItem(position);
        nom_act.setText(activite.getNom_a());
        num_salle.setText(activite.getNum_salle());
        temps.setText(activite.getDate_a());
        return convertView;
    }
}
