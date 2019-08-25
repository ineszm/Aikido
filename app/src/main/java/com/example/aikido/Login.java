package com.example.aikido;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    Button button;
    EditText edit1, edit2;
    TextView textView;
    SharedPreferences sharedPreferences;
    String ty,name1,prename;
    Boolean isregisted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.login);
        edit1 = findViewById(R.id.mail);
        edit2 = findViewById(R.id.mdp);
        textView = findViewById(R.id.sigin);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Sign_in.class);
                startActivity(intent);
            }
        });
        sharedPreferences=getBaseContext().getSharedPreferences("pref",MODE_PRIVATE);
        if(IsLoged())
        {
            Intent intent=new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit1.length() == 0 && edit2.length() == 0) {
                    Toast.makeText(Login.this, "erreur remplir les champs", Toast.LENGTH_SHORT).show();
                    edit1.setError("Remplir champ Mail");
                    edit2.setError("Remplir champ MDP");
                }
               else
                {
                   String mail=edit1.getText().toString();
                   String mdp=edit2.getText().toString();
                   User user=new User(mail,mdp);
                   log(user);

                }
            }
        });
    }



    private void log(final User user) {
        String url = "http://192.168.1.8/Aikido/Login.php?email="+ user.getEmail() + "&password="+ user.getPassword();
        Ion.with(Login.this)
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            Toast.makeText(Login.this, "ERROR " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        { Gson gson=new Gson();
                            User [] users=gson.fromJson(result.toString(),User[].class);
                            ArrayList<User> c2=new ArrayList<>();
                            for(User row:users)
                            {
                                c2.add(row);

                            }
                            for(int i=0;i<c2.size();i++)
                            {
                                ty=c2.get(i).getType();
                                name1=c2.get(i).getNom();
                                prename=c2.get(i).getPrenom();
                               isregisted=c2.get(i).equals("reponse");

                            }

                            if(ty!=null)
                            {
                                Toast.makeText(Login.this, "succeess", Toast.LENGTH_SHORT).show();


                                sharedPreferences.edit().putBoolean("loged",true).putString("email",user.getEmail()).putString("password",user.getPassword()).putString("nom",name1).putString("prenom",prename).putString("type",ty).apply();
                                Intent intent=new Intent(Login.this,MainActivity.class);
                                startActivity(intent);



                            }
                            else
                            {
                                Toast.makeText(Login.this, "Verifier email et password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    private boolean IsLoged() {

            if (sharedPreferences.contains("loged"))
            {
                boolean test=sharedPreferences.getBoolean("loged",false);
                if (test)
                {
                    return  true;
                }
                else
                {
                    return  false;
                }
            }
            else
            {
                sharedPreferences.edit().putBoolean("loged",false).apply();
                return false;
            }
        }
    }

