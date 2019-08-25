package com.example.aikido;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Delete_Coach extends Fragment {

    ListView listView;

    String id_co;
    String mail;
    public Delete_Coach() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_delete__coach, container, false);
        listView=view.findViewById(R.id.list7);
GetAll();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Coach coach=(Coach)parent.getItemAtPosition(position);
                mail=coach.getEmail();
                id_co=GetId(mail);
                Show_popup();

            }
        });
        return view;
    }

    private String GetId(String mail) {
        String url="http://192.168.1.8/Aikido/GetId.php?email="+mail;
        Ion.with(getActivity())
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if(e!=null)
                        {
                            Toast.makeText(getActivity(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Gson gson=new Gson();
                            Coach [] coaches=gson.fromJson(result.toString(),Coach[].class);
                            ArrayList<Coach> c1=new ArrayList<>();
                            for(Coach row:coaches)
                            {
                                c1.add(row);

                            }
                            for(int i=0;i<c1.size();i++)
                            {
                                id_co=c1.get(i).getId_u();
                            }
                        }
                    }
                });
        return id_co;
    }

    private void Show_popup() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity())
                .setTitle("Delete Contact")
                .setMessage("ARE YOU SURE ?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteCoach(id_co);
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    private void DeleteCoach(String id) {
        String url="http://192.168.1.8/Aikido/Delete_Coach.php?id_u="+id;
        Ion.with(getActivity())
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e!=null)
                        {
                            Toast.makeText(getActivity(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            boolean res=result.get("reponse").getAsBoolean();
                            if(res)
                            {
                                Toast.makeText(getActivity(), "success............!", Toast.LENGTH_SHORT).show();
                                GetAll();
                            }
                            else
                            {
                                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void GetAll() {
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
