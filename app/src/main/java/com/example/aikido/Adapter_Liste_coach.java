package com.example.aikido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter_Liste_coach extends ArrayAdapter<Coach> {
    Context ctx;
    public Adapter_Liste_coach( Context context,  List<Coach> objects) {
        super(context, R.layout.model_liste_coach, objects);
        this.ctx=context;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(R.layout.model_liste_coach , parent, false);
        TextView nom_coach=convertView.findViewById(R.id.nom_coa);
        TextView prenom_coach=convertView.findViewById(R.id.prenom_coa);
        TextView mail_coach=convertView.findViewById(R.id.email_coa);
        TextView tel_coach=convertView.findViewById(R.id.tele_coa);
        TextView adress_coach=convertView.findViewById(R.id.adresse_coa);
        Coach coach=getItem(position);
        nom_coach.setText(coach.getNom());
        prenom_coach.setText(coach.getPrenom());
        mail_coach.setText(coach.getEmail());
        tel_coach.setText(coach.getTel());
        adress_coach.setText(coach.getAdresse());
        return convertView;
    }
}
