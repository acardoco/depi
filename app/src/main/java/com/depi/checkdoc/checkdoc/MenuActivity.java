package com.depi.checkdoc.checkdoc;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Bundle bundle;

    private Intent notIntent;
    private NotificationCompat.Builder mBuilder;
    private TaskStackBuilder stackBuilder;
    private NotificationManager mNotificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // if(this.getIntent().getExtras() != null)
        bundle = this.getIntent().getExtras();
        //Log.d("int",this.getIntent().getExtras().toString());

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

                builder1.setNegativeButton(
                        getResources().getString(R.string.cancelCall),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),
                                        "Se ha cancelado su llamada a emergencias", Toast.LENGTH_LONG)
                                        .show();
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


        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(MenuActivity.this)
                .setSmallIcon(R.drawable.chatsmall)
                .setContentTitle("Su médico")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.doctorchat))
                .setSound(alarmSound)
                .setVibrate(new long[] {0, 1000, 200,1000 })
                .setLights(Color.GREEN, 500, 500)
                .setContentText("Hola, Víctor. He visto que María no ha tomado su meducación de hoy. ¿Pasa algo?")
                .setAutoCancel(true);

        notIntent = new Intent(MenuActivity.this, Chat.class);
        /*Bundle b = new Bundle();
        b.putInt("show",bundle.getInt("show"));
        b.putInt("show2", bundle.getInt("show2"));
        notIntent.putExtras(b);*/

        stackBuilder = TaskStackBuilder.create(MenuActivity.this);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        scheduler.scheduleAtFixedRate
                (new Runnable() {
                    public void run() {
                        stackBuilder.addParentStack(Chat.class);
                        stackBuilder.addNextIntent(notIntent);

                        PendingIntent resultPendingIntent =
                                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                        mBuilder.setContentIntent(resultPendingIntent);

                        mNotificationManager.notify(112, mBuilder.build());
                    }
                }, 0, 10, TimeUnit.MINUTES);
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

         if (id == R.id.settings) {
             Intent intent =
                     new Intent(MenuActivity.this, Settings.class);

             Bundle b = new Bundle();
             if(bundle != null){
                 b.putInt("show", bundle.getInt("show"));
                 b.putInt("show2", bundle.getInt("show2"));
             }

             intent.putExtras(b);
             startActivity(intent);
         }
         else if (id == R.id.contactDoctor) {
             Intent intent =
                     new Intent(MenuActivity.this, contactDoctor.class);

             Bundle b = new Bundle();
             if(bundle != null){
                 b.putInt("show", bundle.getInt("show"));
                 b.putInt("show2", bundle.getInt("show2"));
             }
             intent.putExtras(b);
             startActivity(intent);
         } else if (id == R.id.contactMant) {
             Intent intent =
                     new Intent(MenuActivity.this, ContactMant.class);

             Bundle b = new Bundle();
             if(bundle != null){
                 b.putInt("show", bundle.getInt("show"));
                 b.putInt("show2", bundle.getInt("show2"));
             }

             intent.putExtras(b);
             startActivity(intent);
         } else if (id == R.id.privacyPol) {
             Intent intent =
                     new Intent(MenuActivity.this, PrivacyPolicyActivity.class);

             Bundle b = new Bundle();
             if(bundle != null){
                 b.putInt("show", bundle.getInt("show"));
                 b.putInt("show2", bundle.getInt("show2"));
             }

             intent.putExtras(b);
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
                new Intent(MenuActivity.this, SelectOp.class);

        Bundle b = new Bundle();
        switch (view.getId()) {
            case R.id.ImgFotoMe: b.putInt("user",0);break;
            case R.id.ImgFotoMaria: b.putInt("user",1);break;
            case R.id.ImgFotoAlfredo: b.putInt("user",2);break;
        }
        if(bundle != null){
            b.putInt("show", bundle.getInt("show"));
            b.putInt("show2", bundle.getInt("show2"));
        }
        intent.putExtras(b);

        //Iniciamos la nueva actividad
        startActivity(intent);
    }
}
