package com.depi.checkdoc.checkdoc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static com.depi.checkdoc.checkdoc.R.layout.alert_view;

public class NotificationsEnabling extends AppCompatActivity {
    Bundle bundle;
    int show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_enabling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = this.getIntent().getExtras();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                  .setAction("Action", null).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(NotificationsEnabling.this);
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

        View checkBoxView = View.inflate(this, R.layout.alert_view, null);
        CheckBox checkBox = (CheckBox) checkBoxView.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                    show = 1;
                else
                    show = 0;


            }
        });


        if(bundle.getInt("show2") == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Recuerde");
            builder.setMessage("Mueva el botón que hay encima de cada imagen para habilitar o deshabilitar las notificiaciones para cada usuario.")
                    .setView(checkBoxView)
                    .setCancelable(false)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }).show();

        }
        else
            show = 1;

    }
    //con esto se vuelve a atrás
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent =
                        new Intent(NotificationsEnabling.this, Settings.class);

                Bundle b = new Bundle();
                if(bundle != null) {
                    b.putInt("show", bundle.getInt("show"));

                }

                b.putInt("show2",show);
                intent.putExtras(b);
                //Iniciamos la nueva actividad
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
