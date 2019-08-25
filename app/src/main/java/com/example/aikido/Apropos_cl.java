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


public class Apropos_cl extends Fragment {
    ListView listView;
    public Apropos_cl() {
        // Required empty public constructor
    }


//el consommation mta3 web service mta3 el apropos ysir hne


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_apropos_cl, container, false);

        listView = view.findViewById(R.id.list);

        A_propoos();

return view;
    }

    private void A_propoos() {
        String url="http://192.168.1.8/Aikido/Apropos.php";
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
                           Salle[] salle=gson.fromJson(result.toString(),Salle[].class);
                            ArrayList<Salle> s1=new ArrayList<>();
                            for(Salle row:salle)
                            {
                                s1.add(row);

                            }
                            Adapter_apropos adapter_apropos=new Adapter_apropos(getActivity(),s1);
                            listView.setAdapter(adapter_apropos);
                        }
                    }
                });
    }


}
