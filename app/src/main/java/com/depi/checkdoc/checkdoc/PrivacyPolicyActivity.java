package com.depi.checkdoc.checkdoc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                  .setAction("Action", null).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(PrivacyPolicyActivity.this);
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


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Esto pone el título en la barra de arriba cada vez
        TextView txtTitle = (TextView) findViewById(R.id.txtAbTitulo);
        txtTitle.setText(getResources().getString(R.string.title_activity_privacy));


        //la de ajustes de notificaciones ya está hecha, se llama NotificationsEnabling, solo tienes que llamarla desde aquí

    }
    //con esto se vuelve a atrás
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent =
                        new Intent(PrivacyPolicyActivity.this, MenuActivity.class);

                //Iniciamos la nueva actividad
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
