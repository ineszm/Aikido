package com.example.aikido;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


public class Profil_cli extends Fragment {

    TextView text1,text2,text3,text4;
    SharedPreferences sharedPreferences;
    public Profil_cli() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profil_cli, container, false);
        text1=view.findViewById(R.id.mail_pro);
        text2=view.findViewById(R.id.nom_pro);
        text3=view.findViewById(R.id.prenom_pro);
        text4=view.findViewById(R.id.pass_pro);
        sharedPreferences=getActivity().getBaseContext().getSharedPreferences("pref",MODE_PRIVATE);
        String mail=sharedPreferences.getString("email",null);
        String nom=sharedPreferences.getString("nom",null);
        String prenom=sharedPreferences.getString("prenom",null);
        String password=sharedPreferences.getString("password",null);
        text1.setText(mail);
        text2.setText(nom);
        text3.setText(prenom);
        text4.setText(password);


        return view;
    }



}
