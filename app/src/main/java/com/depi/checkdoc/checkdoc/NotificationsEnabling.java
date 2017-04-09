package com.depi.checkdoc.checkdoc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationsEnabling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_enabling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Switch mySwitch=(Switch)findViewById(R.id.BtnSwitch1);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(NotificationsEnabling.this, "Las notificaciones para este usuario se han habilitado",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(NotificationsEnabling.this,"Las notificaciones para este usuario se han deshabilitado",
                            Toast.LENGTH_LONG).show();
                }


            }
        });

        Switch mySwitch2=(Switch)findViewById(R.id.BtnSwitch2);
        mySwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(NotificationsEnabling.this, "Las notificaciones para este usuario se han habilitado",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(NotificationsEnabling.this,"Las notificaciones para este usuario se han deshabilitado",
                            Toast.LENGTH_LONG).show();
                }


            }
        });

        Switch mySwitch3=(Switch)findViewById(R.id.BtnSwitch3);
        mySwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(NotificationsEnabling.this, getResources().getString(R.string.availableNotif),
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(NotificationsEnabling.this,getResources().getString(R.string.notavailableNotif),
                            Toast.LENGTH_LONG).show();
                }


            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Esto pone el título en la barra de arriba cada vez
        TextView txtTitle = (TextView) findViewById(R.id.txtAbTitulo);
        txtTitle.setText(getResources().getString(R.string.title_activity_notifications_enabling));


    }
    //con esto se vuelve a atrás
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent =
                        new Intent(NotificationsEnabling.this, Settings.class);

                //Iniciamos la nueva actividad
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
