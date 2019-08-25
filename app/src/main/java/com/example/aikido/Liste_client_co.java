package com.example.aikido;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Liste_client_co extends Fragment {
ListView listView;

    public Liste_client_co() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_liste_client_co, container, false);
        listView=view.findViewById(R.id.list4);

        GetClient();


return view;
    }

    private void GetClient() {
        String url="http://192.168.1.8/Aikido/Liste_client_respo.php/";
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
                            Client [] client=gson.fromJson(result.toString(),Client[].class);
                            ArrayList<Client> c1=new ArrayList<>();
                            for(Client row:client)
                            {
                                  c1.add(row);

                            }
                            Adapter_liste_cli adapter_liste_cli=new Adapter_liste_cli(getActivity(),c1);
                            listView.setAdapter(adapter_liste_cli);
                        }
                    }
                });
    }


}
