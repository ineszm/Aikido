package com.example.aikido;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.Calendar;


public class Ajouter_Client extends Fragment  implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    EditText edit1, edit2, edit3;
    Button button;

    Calendar calendar;

    String amPm;
    int day,month,year,minute,hour;
    int dayFinal,monthFinal,yearFinal,minuteFinal,hourFinal;
    public Ajouter_Client() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ajouter__client, container, false);
        edit1=view.findViewById(R.id.date_ajout);
        edit2=view.findViewById(R.id.frais_client);
        edit3=view.findViewById(R.id.mode_paiement_client);
        edit1.setFocusable(false);
        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), Ajouter_Client.this,year,month,day);
                datePickerDialog.show();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String date=edit1.getText().toString();
              String frais=edit2.getText().toString();
              String mode=edit3.getText().toString();
              if(date.length()!=0&&frais.length()!=0&&mode.length()!=0)
              {
              Client client=new Client(date,frais,mode);
              AddClient(client);}
              else
              {
                  Toast.makeText(getActivity(), "Remplir les champs", Toast.LENGTH_SHORT).show();
              }
            }
        });

        return view;
    }

    private void AddClient(Client client) {
        String url="http://192.168.1.8/Aikido/AddClient.php?date_ajout="+client.getDate_ajout().replace(" ","%20")+"&frais="+client.getFrais().replace(" ","%20")+"&mode_paiement="+client.getMode_paiement().replace(" ","%20");
        Ion.with(Ajouter_Client.this)//ou bien getActivity
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

                            }
                            else
                            {
                                Toast.makeText(getActivity(), "erreur ", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }
                });







    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal=year;
        monthFinal=month;
        dayFinal=dayOfMonth;
        Calendar calendar=Calendar.getInstance();
        hour=calendar.get(Calendar.HOUR);
        minute=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(), Ajouter_Client.this,hour,minute, DateFormat.is24HourFormat(getActivity()));



        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal=hourOfDay;
        minuteFinal=minute;
        if(hourOfDay>=12)
        {amPm="PM";
            edit2.setText(String.format("%02d-%02d-%02d %02d:%02d:00",yearFinal,monthFinal,dayFinal,hourFinal,minuteFinal));}
        else
        {amPm="AM";
            edit2.setText(String.format("%02d-%02d-%02d %02d:%02d:00",yearFinal,monthFinal,dayFinal,hourFinal,minuteFinal));
        }

    }
}
