package com.depi.checkdoc.checkdoc;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class contactDoctor extends AppCompatActivity {
    Bundle bundle;
    FloatingActionButton chat;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_doctor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = this.getIntent().getExtras();
        String a = "aaa";
        if(bundle != null)
            a = "bbb";
        Log.d("eeeeeeeeeeee", a);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                  .setAction("Action", null).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(contactDoctor.this);
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

        chat = (FloatingActionButton) findViewById(R.id.chat);
        chat.setEnabled(false);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(contactDoctor.this, Chat.class);
                Bundle b = new Bundle();
                if(bundle != null) {
                    b.putInt("show", bundle.getInt("show"));
                    b.putInt("show2", bundle.getInt("show2"));
                }
                intent.putExtras(b);
                startActivity(intent);
            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Esto pone el título en la barra de arriba cada vez
        TextView txtTitle = (TextView) findViewById(R.id.txtAbTitulo);
        txtTitle.setText(getResources().getString(R.string.title_activity_contact_doctor));

    }
    //con esto se vuelve a atrás
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent =
                        new Intent(contactDoctor.this, MenuActivity.class);
                Bundle b = new Bundle();

                if(bundle != null) {
                    b.putInt("show", bundle.getInt("show"));
                    b.putInt("show2", bundle.getInt("show2"));
                }

                intent.putExtras(b);
                //Iniciamos la nueva actividad
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        for(StatusBarNotification not: mNotificationManager.getActiveNotifications()){
            if(not.getId() == 112) chat.setEnabled(true);
            else chat.setEnabled(false);
        }

        if(!chat.isEnabled()) chat.getBackground().setAlpha(64);
        else chat.getBackground().setAlpha(255);

    }

}
