package com.example.aikido;

import android.content.Context;
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
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;


public class Consulter_progC extends Fragment {
    ListView listView;

    public Consulter_progC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_consulter_prog_c, container, false);
        listView = view.findViewById(R.id.list2);

        GetProg();

        return view;
    }

    private void GetProg() {
      String url="http://192.168.1.8/Aikido/Consulter_programmeclient.php";
        Ion.with(Consulter_progC.this)
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
                            Adapter_Consu_progC adapter_consu_progC=new Adapter_Consu_progC(getActivity(),act);
                            listView.setAdapter(adapter_consu_progC);
                        }
                    }
                });
    }


}
