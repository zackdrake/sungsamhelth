package fr.intech.sungsamhelth;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.os.Bundle;
import android.util.Log;

import fr.intech.sungsamhelth.model.GravitySensor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Sensor sensor = GravitySensor.getSensor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("sensor",""+sensor);

    }

}