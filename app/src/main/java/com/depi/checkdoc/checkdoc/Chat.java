package com.depi.checkdoc.checkdoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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
