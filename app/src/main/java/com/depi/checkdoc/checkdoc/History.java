package com.depi.checkdoc.checkdoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class History extends AppCompatActivity {

    ListView history;
    private HistoryItem[] data =
            new HistoryItem[]{
                    new HistoryItem("Victor", "Presión sanguínea en estado crítico", "10/12/2016","15:32"),
                    new HistoryItem("María", "Presión sanguínea en estado crítico", "13/01/2015","11:54"),
                    new HistoryItem("Alfredo", "Ácido úrico en estado crítico", "15/11/2016","12:56"),

                    new HistoryItem("Alfredo", "Niveles de azúcar en estado crítico", "07/09/2016","19:26"),
                    new HistoryItem("Victor", "Presión sanguínea en estado crítico", "10/12/2016","15:32"),
                    new HistoryItem("María", "Presión sanguínea en estado crítico", "13/01/2015","11:54"),
                    new HistoryItem("Alfredo","Presión sanguínea en estado crítico", "15/11/2016","12:56"),
                    new HistoryItem("Alfredo", "Niveles de azúcar en estado crítico", "07/09/2016","19:26"),
                    //...
                    new HistoryItem("Victor", "Presión sanguínea en estado crítico", "10/08/2016","13:42"),
                    new HistoryItem("María", "Ácido úrico en estado crítico", "24/03/2016","18:30")};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
            }
        });



//...

        adapterListHistory adapter =
                new adapterListHistory(this, data);

        history = (ListView)findViewById(R.id.LstHistory);

        history.setAdapter(adapter);
        View header = getLayoutInflater().inflate(R.layout.content_header, null);

        history.addHeaderView(header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Esto pone el título en la barra de arriba cada vez
        TextView txtTitle = (TextView) findViewById(R.id.txtAbTitulo);
        txtTitle.setText(getResources().getString(R.string.title_activity_history));

    }
    //con esto se vuelve a atras
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent =
                        new Intent(History.this, MenuActivity.class);

                //Iniciamos la nueva actividad
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
