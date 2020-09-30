package fr.intech.sungsamhelth;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import fr.intech.sungsamhelth.model.GravitySensor;

public class MainActivity extends AppCompatActivity {
    private TextView NombrePas;
    int steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NombrePas = findViewById(R.id.NBPas);
        Sensor sensor = GravitySensor.getSensor();
        Sensor sensor1 = GravitySensor.getSensor1();

    }
    public void onSensorChanged(final SensorEvent event) {
        steps = (int) event.values[0];
        NombrePas.setText(String.valueOf(steps));
        Log.i("step",""+steps);
    }

}