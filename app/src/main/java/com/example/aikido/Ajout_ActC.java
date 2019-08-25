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


public class Ajout_ActC extends Fragment  implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
EditText edit1,edit2,edit3;
Button button;

    Calendar calendar;

    String amPm;
    int day,month,year,minute,hour;
    int dayFinal,monthFinal,yearFinal,minuteFinal,hourFinal;
    public Ajout_ActC() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ajout__act_c, container, false);
        edit1=view.findViewById(R.id.name_act);
        edit2=view.findViewById(R.id.time);
        edit3=view.findViewById(R.id.salle);
        button=view.findViewById(R.id.ajouter_act);
        edit2.setFocusable(false);
        edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), Ajout_ActC.this,year,month,day);
                datePickerDialog.show();




            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom=edit1.getText().toString();
                String date=edit2.getText().toString();
                String room=edit3.getText().toString();
                if(nom.length()!=0&&date.length()!=0&&room.length()!=0)
                {
                Activite activite=new Activite(nom,date,room);
                Add_Exercice(activite);}
                else
                {
                    Toast.makeText(getActivity(), "Remplir les champs", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    private void Add_Exercice(Activite activite) {
        String url="http://192.168.1.8/Aikido/AddExercice.php?nom_a="+activite.getNom_a().replace(" ","%20")+"&date_a="+activite.getDate_a().replace(" ","%20")+"&num_salle="+activite.getNum_salle();
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
                        {
                            boolean isInserted= result.get("reponse").getAsBoolean();
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
        TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(), Ajout_ActC.this,hour,minute, DateFormat.is24HourFormat(getActivity()));



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
