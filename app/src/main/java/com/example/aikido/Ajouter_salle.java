package com.example.aikido;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ajouter_salle extends Fragment {

    EditText edit1, edit2, edit3, edit4, edit5, edit6;
    Button button;
    String s_long;
    String s_lat;
    public Ajouter_salle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ajouter_salle, container, false);
        edit1=view.findViewById(R.id.nom_salle);
        edit2=view.findViewById(R.id.adresse_salle);
        edit3=view.findViewById(R.id.email_salle);
        edit4=view.findViewById(R.id.num_act);
        edit5=view.findViewById(R.id.tel_salle);
        edit6=view.findViewById(R.id.num_coach);
        button=view.findViewById(R.id.ajouter_salle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom=edit1.getText().toString();
                String adress=edit2.getText().toString();
                String email=edit3.getText().toString();
                String num_ac=edit4.getText().toString();
                String tel=edit5.getText().toString();
                String num_co=edit6.getText().toString();

                if(nom.length()!=0&&adress.length()!=0&&email.length()!=0&&num_ac.length()!=0&&tel.length()!=0&&num_co.length()!=0)
                {
                List<Address> addressList=null;
                if(!TextUtils.isEmpty(adress))
                {
                    Geocoder geocoder=new Geocoder(getActivity());
                    try {
                        if (addressList.size() != 0) {
                            addressList = geocoder.getFromLocationName(adress, 1);
                            Address address = addressList.get(0);
                             s_long = String.valueOf(address.getLongitude());
                          s_lat = String.valueOf(address.getLatitude());

                        }
                        else
                        {
                            edit2.setError("Verifier Votre Emplacement");
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
                Salle salle=new Salle(nom,adress,email,num_ac,tel,num_co,s_long,s_lat);
                AddSalle(salle);}
                else
                {
                    Toast.makeText(getActivity(), "Remplir les champs", Toast.LENGTH_SHORT).show();
                }


            }
        });
        return view;
    }

    private void AddSalle(Salle salle) {
        String url="http://192.168.1.8/Aikido/AddSalle.php?nom_s="+salle.getNom_s().replace(" ","%20")+"&adres_s="+salle.getAdress_s().replace(" ","%20")+"&num_coach="+salle.getNum_coach()+"&num_act="+salle.getNum_act()+"&tel_s="+salle.getTel_s()+"&email_s="+salle.getEmail_s()+"&longitude="+salle.getLongitude()+"&latitude="+salle.getLatitude();
        Ion.with(getActivity())
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e!=null)
                        {
                            Toast.makeText(getActivity(), "ERROR "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {   boolean isInserted= result.get("reponse").getAsBoolean();
                            if(isInserted)
                            {
                                Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                                edit1.setText("");
                                edit2.setText("");
                                edit3.setText("");
                                edit4.setText("");
                                edit5.setText("");
                                edit6.setText("");

                            }
                            else
                            {
                                Toast.makeText(getActivity(), "erreur ", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }
                });
    }

}
