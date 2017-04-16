package com.depi.checkdoc.checkdoc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//dshjgbfasjlbgng
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
      //          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
      //                  .setAction("Action", null).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(MenuActivity.this);
                builder1.setMessage(getResources().getString(R.string.doctorWarn));
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        getResources().getString(R.string.accept),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Esto pone el título en la barra de arriba cada vez
        TextView txtTitle = (TextView) findViewById(R.id.txtAbTitulo);
        txtTitle.setText(getResources().getString(R.string.title_activity_menu));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


     @SuppressWarnings("StatementWithEmptyBody")
     @Override
     public boolean onNavigationItemSelected(MenuItem item) {

         int id = item.getItemId();

         if (id == R.id.historial) {
             Intent intent =
                     new Intent(MenuActivity.this, History.class);

             startActivity(intent);

         } else if (id == R.id.settings) {
             Intent intent =
                     new Intent(MenuActivity.this, Settings.class);

             startActivity(intent);
         } else if (id == R.id.contactDoctor) {
             Intent intent =
                     new Intent(MenuActivity.this, contactDoctor.class);

             startActivity(intent);
         } else if (id == R.id.contactMant) {
             Intent intent =
                     new Intent(MenuActivity.this, ContactMant.class);

             startActivity(intent);

         } else if (id == R.id.calendar) {
             Intent intent =
                     new Intent(MenuActivity.this, CalendarActivity.class);

             startActivity(intent);

         } else if (id == R.id.privacyPol) {
             Intent intent =
                     new Intent(MenuActivity.this, PrivacyPolicyActivity.class);

             startActivity(intent);

         }
         else if (id == R.id.logout) {
             Intent intent =
                     new Intent(MenuActivity.this, LoginActivity.class);

             startActivity(intent);

         }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void imageClick(View view) {
        //Implement image click function
        Intent intent =
                new Intent(MenuActivity.this, Indicators.class);

        Bundle b = new Bundle();
        switch (view.getId()) {
            case R.id.ImgFotoMe: b.putInt("user",0);break;
            case R.id.ImgFotoMaria: b.putInt("user",1);break;
            case R.id.ImgFotoAlfredo: b.putInt("user",2);break;
        }

        intent.putExtras(b);

        //Iniciamos la nueva actividad
        startActivity(intent);
    }
}
