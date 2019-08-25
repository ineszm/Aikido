package com.example.aikido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter_consulter_salle extends ArrayAdapter<Salle> {
    Context ctx;
    public Adapter_consulter_salle( Context context, List<Salle> objects) {
        super(context, R.layout.model_consulter_salle, objects);
        this.ctx=context;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        convertView = LayoutInflater.from(ctx).inflate(R.layout.model_consulter_salle , parent, false);
        TextView nom_salle=convertView.findViewById(R.id.nom_salle);
        TextView adress_salle=convertView.findViewById(R.id.adress_salle);
        TextView tel_salle=convertView.findViewById(R.id.tel_salle);
        TextView num_act=convertView.findViewById(R.id.num_act);
        TextView num_coach=convertView.findViewById(R.id.num_coach);
        TextView mail_salle=convertView.findViewById(R.id.email_salle);
        Salle salle=getItem(position);
        nom_salle.setText(salle.getNom_s());
        adress_salle.setText(salle.getAdress_s());
        tel_salle.setText(salle.getTel_s());
        num_act.setText(salle.getNum_act());
        num_coach.setText(salle.getNum_coach());
        mail_salle.setText(salle.getEmail_s());


        return convertView;
    }
}
