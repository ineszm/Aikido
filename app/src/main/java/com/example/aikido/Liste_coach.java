package com.example.aikido;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
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


public class Liste_coach extends Fragment {
    ListView listView;

    public Liste_coach() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_liste_coach, container, false);
        listView=view.findViewById(R.id.list6);

        GetCoach();


        return view;
    }

    private void GetCoach() {
        String url="http://192.168.1.8/Aikido/Liste_coach_respo.php/";
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
                            Coach [] coach=gson.fromJson(result.toString(),Coach[].class);
                            ArrayList<Coach> c2=new ArrayList<>();
                            for(Coach row:coach)
                            {
                                c2.add(row);

                            }
                            Adapter_Liste_coach adapter_liste_coach=new Adapter_Liste_coach(getActivity(),c2);
                            listView.setAdapter(adapter_liste_coach);
                        }
                    }
                });
    }


}
