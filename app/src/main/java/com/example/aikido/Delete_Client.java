package com.example.aikido;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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


public class Delete_Client extends Fragment {
ListView listView;
  String id_c;
  String mail;

    public Delete_Client() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_delete__client, container, false);
        listView=view.findViewById(R.id.list5);





        GetAll();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Client client=(Client) parent.getItemAtPosition(position);

                mail=client.getEmail();

                id_c=GetId(mail);
                Show_PopUp();
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
                            Client [] client=gson.fromJson(result.toString(),Client[].class);
                            ArrayList<Client> c1=new ArrayList<>();
                            for(Client row:client)
                            {
                                c1.add(row);

                            }
                            for(int i=0;i<c1.size();i++)
                            {
                                id_c=c1.get(i).getId_u();
                            }
                        }
                }
                });
        return id_c;
    }


    private void Show_PopUp() {
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
                        DeleteClient(id_c);
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    private void DeleteClient(String id) {
        String url="http://192.168.1.8/Aikido/Delete_Client.php?id_u="+id;
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
