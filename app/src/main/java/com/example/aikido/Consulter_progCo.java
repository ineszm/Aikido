package com.example.aikido;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;


public class Consulter_progCo extends Fragment {

ListView listView;
String email;//ngibouh mel shered preference
    SharedPreferences sharedPreferences;
    public Consulter_progCo() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
 View view= inflater.inflate(R.layout.fragment_consulter_prog_co, container);
        listView = view.findViewById(R.id.list3);

        sharedPreferences=getActivity().getBaseContext().getSharedPreferences("pref",Context.MODE_PRIVATE);
        email=sharedPreferences.getString("email",null);
        GetProg(email);
 return view;
    }

    private void GetProg(String email) {
        String url="http://192.168.1.8/Aikido/Consulter_progcoach.php?email="+email;
        Ion.with(getActivity())
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if(e!=null)
                        {
                            Toast.makeText(getActivity(), "ERROR "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Gson gson=new Gson();
                          Activite[] activites=gson.fromJson(result.toString(),Activite[].class);
                            ArrayList<Activite> act=new ArrayList<>();
                            for(Activite row:activites)
                            {
                                act.add(row);

                            }
                            Adapter_exercice_client adapter_exercice_client=new Adapter_exercice_client(getActivity(),act);
                            listView.setAdapter(adapter_exercice_client);
                        }
                    }
                });
    }


}
