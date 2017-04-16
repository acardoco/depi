package com.depi.checkdoc.checkdoc;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphicsIndicator extends AppCompatActivity {
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics_indicator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                  .setAction("Action", null).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(GraphicsIndicator.this);
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



        TextView title = (TextView) findViewById(R.id.Titlegraphic);
        bundle = this.getIntent().getExtras();
        title.setText(bundle.getString("indicator"));

        TextView name = (TextView) findViewById(R.id.nameProfileAndPhoto);
        TextView description = (TextView) findViewById(R.id.descriptionProfileAndPhoto);
        ImageView img = (ImageView) findViewById(R.id.ImgFotoPerfilHistory);

        switch (bundle.getInt("user")){
            //el cero es el que está por defecto
            case 1:name.setText(getResources().getString(R.string.mariaName));
                description.setText(getResources().getString(R.string.mariaDesc));
                img.setImageResource(R.drawable.imgmaria);break;
            case 2:name.setText(getResources().getString(R.string.alfredoName));
                description.setText(getResources().getString(R.string.alfredoDesc));
                img.setImageResource(R.drawable.imgalfredo);break;
        }

        TextView stat = (TextView) findViewById(R.id.graphicState);
        ImageView imgSt = (ImageView) findViewById(R.id.graphictStateIm);
        switch (bundle.getInt("state")){
            //el cero es el que está por defecto
            case 0:stat.setText(getResources().getString(R.string.legend_normal_state));
                  imgSt.setImageResource(R.drawable.valornormal);break;
            case 1:stat.setText(getResources().getString(R.string.legend_low_state));
                imgSt.setImageResource(R.drawable.valormedio);break;
            case 2:stat.setText(getResources().getString(R.string.legend_critical_state));
                imgSt.setImageResource(R.drawable.valorcritico);break;
        }

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 115),
                new DataPoint(2, 120),
                new DataPoint(3, 118),
                new DataPoint(4, 118),
                new DataPoint(5, 110),
                new DataPoint(6, 101),
                new DataPoint(7, 140),
        });

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 120),
                new DataPoint(2, 130),
                new DataPoint(3, 125),
                new DataPoint(4, 135),
                new DataPoint(5, 130),
                new DataPoint(6, 131),
                new DataPoint(7, 120),
        });
        series2.setColor(Color.GREEN);
        graph.addSeries(series);
        graph.addSeries(series2);
        series.setTitle(getResources().getString(R.string.graphic_legend_1));
        series2.setTitle(getResources().getString(R.string.graphic_legend_2));
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setBackgroundColor(Color.WHITE);
        graph.getLegendRenderer().setFixedPosition(0,0);
        graph.getGridLabelRenderer().setNumVerticalLabels(20);
        graph.getGridLabelRenderer().setNumHorizontalLabels(8);
        graph.getGridLabelRenderer().setVerticalAxisTitle(getResources().getString(R.string.axis_y_title));
        graph.getGridLabelRenderer().setHorizontalAxisTitle(getResources().getString(R.string.axis_x_title));
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(100);
        graph.getViewport().setMaxY(145);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(8);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Esto pone el título en la barra de arriba cada vez
        TextView txtTitle = (TextView) findViewById(R.id.txtAbTitulo);
        txtTitle.setText(getResources().getString(R.string.title_activity_graphics_indicator));

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent =
                        new Intent(GraphicsIndicator.this, Indicators.class);
                Bundle b = new Bundle();
                b.putInt("user",bundle.getInt("user"));
                intent.putExtras(b);
                //Iniciamos la nueva actividad
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
