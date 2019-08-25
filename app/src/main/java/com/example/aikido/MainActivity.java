package com.example.aikido;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.koushikdutta.ion.builder.Builders;

public class MainActivity extends AppCompatActivity {
Button button;
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.entrer);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sharedPreferences=getBaseContext().getSharedPreferences("pref",MODE_PRIVATE);

            String type1=sharedPreferences.getString("type",null);


           if(type1.equalsIgnoreCase("client"))

            {

                Intent intent=new Intent(MainActivity.this,Menu_client.class);
               startActivity(intent);

           }
           else if(type1.equalsIgnoreCase("responsable"))
            {
                Intent intent1=new Intent(MainActivity.this,Menu_respo.class);
                startActivity(intent1);

            }
          else if (type1.equalsIgnoreCase("admin"))
            {
                Intent intent2=new Intent(MainActivity.this,Menu_Admin.class);
                startActivity(intent2);

            }
          else  if (type1.equalsIgnoreCase("coach"))
            {
                Intent intent3=new Intent(MainActivity.this,Menu_Coach.class);
                startActivity(intent3);

            }
          else
           {
               Toast.makeText(MainActivity.this, "type don't exist", Toast.LENGTH_SHORT).show();
           }



    }
});
    }


}
