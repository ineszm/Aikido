package com.example.aikido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter_liste_cli extends ArrayAdapter<Client> {
    Context ctx;
    public Adapter_liste_cli( Context context,  List<Client> objects) {
        super(context,R.layout.model_liste_client, objects);
        this.ctx=context;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(R.layout.model_liste_client , parent, false);
        TextView nom_cli=convertView.findViewById(R.id.nom_cli);
        TextView prenom_cli=convertView.findViewById(R.id.prenom_cli);
        TextView mail_cli=convertView.findViewById(R.id.email_cli);
        TextView tel_cli=convertView.findViewById(R.id.tele_cli);
        TextView date_cli=convertView.findViewById(R.id.date_cli);
        TextView frais_cli=convertView.findViewById(R.id.frais_cli);
        Client client=getItem(position);
        nom_cli.setText(client.getNom());
        prenom_cli.setText(client.getPrenom());
        mail_cli.setText(client.getEmail());
        tel_cli.setText(client.getTel());
        date_cli.setText(client.getDate_ajout());
        frais_cli.setText(client.getFrais());
        return convertView;

    }
}
