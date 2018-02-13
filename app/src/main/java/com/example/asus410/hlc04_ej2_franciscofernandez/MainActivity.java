package com.example.asus410.hlc04_ej2_franciscofernandez;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void arrancarServicio(View view) {
        startService(new Intent(getBaseContext(), MyIntentService.class));
    }

    //Se ejecuta una vez que la Activity ha terminado de cargarse en el dispositivo
    //y el usuario empieza a interactuar con la aplicación.
    @Override
    public void onResume(){
        super.onResume();
        //---IntentFilter para filtrar el intent de archivo descargado ---
        intentFilter = new IntentFilter();
        intentFilter.addAction("ACTION");

        //---registrar el receptor ---
        registerReceiver(intentReceiver, intentFilter);

    }


    //se ejecuta cuando la Activity pierde el foco, pero sigue siendo visible
    @Override
    public void onPause(){
        super.onPause();
        //--- anular el registro del recpetor ---
        unregisterReceiver(intentReceiver);

    }

    /**
     * Instancia de la clase BroadcastReceiver que hemos definido
     * Mostramos la finalizacion del servicio con los segundos transcurridos
     */
    private BroadcastReceiver intentReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive (Context context, Intent intent){
            int tiempo = intent.getIntExtra("TIEMPO",0);
            tiempo = tiempo/1000;
            Toast.makeText(getBaseContext(), "Fin del servicio en "+tiempo +" segundos", Toast.LENGTH_LONG).show();
        }
    };
}
