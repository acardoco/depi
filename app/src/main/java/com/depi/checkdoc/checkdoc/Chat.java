package com.depi.checkdoc.checkdoc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Chat extends AppCompatActivity {

    Bundle bundle;
    ImageButton ib;
    TextView outputText;
    EditText inputText;
    String texto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = this.getIntent().getExtras();
        //Esto pone el título en la barra de arriba cada vez
        TextView txtTitle = (TextView) findViewById(R.id.txtAbTitulo);
        txtTitle.setText(getResources().getString(R.string.title_activity_chat));

        ib = (ImageButton)findViewById(R.id.imageButton);
        inputText = (EditText) findViewById(R.id.textoInput);
        outputText = (TextView)findViewById(R.id.textoOutput);
        outputText.setKeyListener(null);//para no hacerlo editable

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto = texto + Html.fromHtml("<br />") + inputText.getText();
                outputText.setText(texto);
                inputText.getText().clear();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                  .setAction("Action", null).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Chat.this);
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


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent =
                        new Intent(Chat.this, contactDoctor.class);

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
}
