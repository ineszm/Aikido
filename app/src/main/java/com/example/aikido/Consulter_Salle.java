package com.example.aikido;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class Consulter_Salle extends Fragment {

    ListView listView;
    public Consulter_Salle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_consulter__salle, container);
        listView = view.findViewById(R.id.list8);

        GetSalle();
        return view;
    }

    private void GetSalle() {
        String url="http://192.168.1.8/Aikido/Consulter_salle.php";
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
                          Salle [] salle=gson.fromJson(result.toString(),Salle[].class);
                            ArrayList<Salle> s1=new ArrayList<>();
                            for(Salle row:salle)
                            {
                                s1.add(row);

                            }
                            Adapter_consulter_salle adapter_consulter_salle=new Adapter_consulter_salle(getActivity(),s1);
                            listView.setAdapter(adapter_consulter_salle);
                        }
                    }
                });
    }

}
