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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class History extends AppCompatActivity {
    Bundle bundle;
    ListView history;
    private HistoryItem[] data =
            new HistoryItem[]{
                    new HistoryItem("Presión sanguínea", "Estado:crítico", "10/12/2016","15:32"),
                    new HistoryItem("Presión sanguínea", "Estado:crítico", "13/01/2015","11:54"),
                    new HistoryItem("Ácido úrico", "Estado:valores bajos", "15/11/2016","12:56"),

                    new HistoryItem("Niveles de azúcar", "Estado:crítico", "07/09/2016","19:26"),
                    new HistoryItem("Niveles de azúcar", "Estado:crítico", "10/12/2016","15:32"),
                    new HistoryItem("Presión sanguínea", "Estado:crítico", "13/01/2015","11:54"),
                    new HistoryItem("Presión sanguínea","Estado:valores bajos", "15/11/2016","12:56"),
                    new HistoryItem("Niveles de azúcar", "Estado:crítico", "07/09/2016","19:26"),
                    //...
                    new HistoryItem("Presión sanguínea", "Estado:crítico", "10/08/2016","13:42"),
                    new HistoryItem("Ácido úrico", "Estado: valores bajos", "24/03/2016","18:30")};


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
                //          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                  .setAction("Action", null).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(History.this);
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


//...
        TextView name = (TextView) findViewById(R.id.nameProfileAndPhoto);
        TextView description = (TextView) findViewById(R.id.descriptionProfileAndPhoto);
        ImageView img = (ImageView) findViewById(R.id.ImgFotoPerfilHistory);

        bundle = this.getIntent().getExtras();
        switch (bundle.getInt("user")){
            //el cero es el que está por defecto
            case 1:name.setText(getResources().getString(R.string.mariaName));
                description.setText(getResources().getString(R.string.mariaDesc));
                img.setImageResource(R.drawable.imgmaria);break;
            case 2:name.setText(getResources().getString(R.string.alfredoName));
                description.setText(getResources().getString(R.string.alfredoDesc));
                img.setImageResource(R.drawable.imgalfredo);break;
        }

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

        history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Intent intent =
                        new Intent(History.this, GraphicsIndicator.class);

                Bundle b = new Bundle();

                b.putInt("user",bundle.getInt("user"));
                b.putString("indicator",((HistoryItem)a.getItemAtPosition(position)).getName());
                switch (((HistoryItem)a.getItemAtPosition(position)).getItem()){
                    case "Estado:valores bajos":b.putInt("state",1);break;
                    case "Estado:crítico":b.putInt("state",2);break;
                }
                b.putInt("previous",0);
                b.putInt("show",bundle.getInt("show"));
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);
                //Alternativa 1:

            }
        });
    }
    //con esto se vuelve a atras
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent =
                        new Intent(History.this, SelectOp.class);
                Bundle b = new Bundle();
                b.putInt("user",bundle.getInt("user"));
                b.putInt("show",bundle.getInt("show"));


                b.putInt("show2", bundle.getInt("show2"));

                intent.putExtras(b);
                //Iniciamos la nueva actividad
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
