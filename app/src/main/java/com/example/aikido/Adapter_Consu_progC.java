package com.example.aikido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter_Consu_progC extends ArrayAdapter<Activite> {
    Context ctx;
    public Adapter_Consu_progC( Context context,  List<Activite> objects) {
        super(context, R.layout.model_consulter_progcli, objects);
        this.ctx=context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(R.layout.model_consulter_progcli , parent, false);
        TextView nom_act=convertView.findViewById(R.id.nom_act);
        TextView num_salle=convertView.findViewById(R.id.num_sall);
        TextView temps=convertView.findViewById(R.id.temps_act);
        TextView nom_prof=convertView.findViewById(R.id.nom_prof);
        TextView prenom_coach=convertView.findViewById(R.id.prenom_coach);
       Activite activite=getItem(position);
       nom_act.setText(activite.getNom_a());
       num_salle.setText(activite.getNum_salle());
       temps.setText(activite.getDate_a());
       nom_prof.setText(activite.getNom());
       prenom_coach.setText(activite.getPrenom());
        return convertView;
    }
}
