package com.example.aikido;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Menu_respo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
SharedPreferences sharedPreferences;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_respo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        sharedPreferences=getBaseContext().getSharedPreferences("pref",MODE_PRIVATE);
        View header=navigationView.getHeaderView(0);
        textView=header.findViewById(R.id.nom_respo);
        String mail=sharedPreferences.getString("email",null);
        textView.setText(mail);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_respo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.login_out)
            {
                sharedPreferences.edit().putBoolean("loged",false).apply();//sakaret el session mete3ou
                Intent intent=new Intent(Menu_respo.this,Login.class);
                startActivity(intent);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment2=null;
        if (id == R.id.nav_liste_client) {
            // Handle the camera action
            fragment2=new Liste_client_co();
        } else if (id == R.id.nav_add_client) {
            fragment2=new Ajouter_Client();

        } else if (id == R.id.nav_delete_client) {
            fragment2=new Delete_Client();
        } else if (id == R.id.nav_liste_coach) {
      fragment2=new Liste_coach();
        } else if (id == R.id.nav_add_coach) {
            fragment2=new Ajouter_Coach();

        } else if (id == R.id.nav_delete_coach) {
          fragment2=new Delete_Coach();
        }
        else if (id == R.id.nav_profil_resp) {
              fragment2=new Profil_cli();
        }
        if(fragment2!=null)
        {

            FragmentManager fr=getSupportFragmentManager();
            FragmentTransaction ft=fr.beginTransaction();
            ft.replace(R.id.framelayout,fragment2).commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
