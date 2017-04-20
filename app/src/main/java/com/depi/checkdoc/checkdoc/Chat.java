package com.depi.checkdoc.checkdoc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Chat extends AppCompatActivity {

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

    }

}
