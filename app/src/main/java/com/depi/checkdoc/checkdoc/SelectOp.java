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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectOp extends AppCompatActivity {

    Bundle bundle;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_op);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                  .setAction("Action", null).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(SelectOp.this);
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Esto pone el título en la barra de arriba cada vez
        TextView txtTitle = (TextView) findViewById(R.id.txtAbTitulo);
        txtTitle.setText(getResources().getString(R.string.title_activity_select_options));


        // Get ListView object from xml
       listView = (ListView) findViewById(R.id.optionsList);

        // Defined Array values to show in ListView
        String[] values = new String[] {
                "Indicadores",
                "Calendario",
                "Historial"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;
                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);
                Intent intent;
                Bundle b = new Bundle();

                b.putInt("user",bundle.getInt("user"));
                b.putInt("show",bundle.getInt("show"));
                b.putInt("show2", bundle.getInt("show2"));

                switch (position){
                    case 0:
                        intent= new Intent(SelectOp.this, Indicators.class);

                        intent.putExtras(b);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(SelectOp.this, CalendarActivity.class);
                        intent.putExtras(b);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(SelectOp.this, History.class);
                        intent.putExtras(b);
                        startActivity(intent);
                        break;


                }




            }

        });


    }
    //con esto se vuelve a atrás
   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent =
                        new Intent(SelectOp.this, MenuActivity.class);

                Bundle b = new Bundle();
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
