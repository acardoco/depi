package com.depi.checkdoc.checkdoc;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    private NotificationManager mNotificationManager;
    private TaskStackBuilder stackBuilder;
    private NotificationCompat.Builder mBuilder;
    private Intent notIntent;


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
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
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

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.checkdocsmall)
                        .setContentTitle("Alerta por nivel de azúcar")
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.sugarnot))
                        .setSound(alarmSound)
                        .setVibrate(new long[] {0, 1000, 200,1000 })
                        .setLights(Color.MAGENTA, 500, 500)
                        .setContentText("Los niveles de glucosa en sangre de " + name.getText() + " están por...");



        notIntent = new Intent(this, GraphicsIndicator.class);
        Bundle b = new Bundle();
            b.putInt("user", bundle.getInt("user"));
            b.putString("indicator", data[3].getIndicator());
            b.putInt("state", data[3].getState());
        notIntent.putExtras(b);

        stackBuilder = TaskStackBuilder.create(this);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        scheduler.scheduleAtFixedRate
                (new Runnable() {
                    public void run() {

                        stackBuilder.addParentStack(GraphicsIndicator.class);
                        stackBuilder.addNextIntent(notIntent);

                        PendingIntent resultPendingIntent =
                                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT
                                );
                        mBuilder.setContentIntent(resultPendingIntent);

                        mNotificationManager.notify(1234, mBuilder.build());

                    }
                }, 0, 10, TimeUnit.MINUTES);
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
