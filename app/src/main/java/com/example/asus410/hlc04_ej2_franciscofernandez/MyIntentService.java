package com.example.asus410.hlc04_ej2_franciscofernandez;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

public class MyIntentService extends IntentService {

    MediaPlayer reproductor;

    public MyIntentService() {
        super("MyIntentService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //obttengo el recurso de audio
        reproductor = MediaPlayer.create(this, R.raw.nana);
        //inicio el recurso
        reproductor.start();
        tiempomusica(reproductor);
        //envia un broadcas a la actividad para informar de que ha terminado la reproduccion con
        //el tiempo de reproduccion.
        Intent broadcastIntent = new Intent();
        int tiempo = reproductor.getDuration();
        broadcastIntent.putExtra("TIEMPO", tiempo);
        broadcastIntent.setAction("ACTION");
        getBaseContext().sendBroadcast(broadcastIntent);

    }

    public void tiempomusica(MediaPlayer media) {
        //obtengo la duracion del sonido
        Long tiempo = new Long(media.getDuration());

        try {
            //duermo el hilo durante la duracion, le sumo medio segundo
            //asi para por completo la reproduccion.
            Thread.sleep(tiempo+500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
