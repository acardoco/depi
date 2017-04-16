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

public class Indicators extends AppCompatActivity {
    Bundle bundle;
    ListView indicators;
    private IndicatorItem[] data =
            new IndicatorItem[]{
                    new IndicatorItem("Presión sanguínea", 0),
                    new IndicatorItem("Niveles de azúcar", 1),
                    new IndicatorItem("Ácido úrico", 0),

                    new IndicatorItem("Niveles de azúcar", 2),
                    new IndicatorItem("Ácido úrico", 1)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicators);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                  .setAction("Action", null).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Indicators.this);
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







        adapterListIndicators adapter =
                new adapterListIndicators(this, data);

        indicators = (ListView)findViewById(R.id.LstIndicators);

        indicators.setAdapter(adapter);


        indicators.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Intent intent =
                        new Intent(Indicators.this, GraphicsIndicator.class);

               Bundle b = new Bundle();

                b.putInt("user",bundle.getInt("user"));
                b.putString("indicator",((IndicatorItem)a.getItemAtPosition(position)).getIndicator());
                b.putInt("state",((IndicatorItem)a.getItemAtPosition(position)).getState());
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);
                //Alternativa 1:

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Esto pone el título en la barra de arriba cada vez
        TextView txtTitle = (TextView) findViewById(R.id.txtAbTitulo);
        txtTitle.setText(getResources().getString(R.string.title_activity_indicators));


    }
    //con esto se vuelve a atrás
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent =
                        new Intent(Indicators.this, MenuActivity.class);

                //Iniciamos la nueva actividad
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
