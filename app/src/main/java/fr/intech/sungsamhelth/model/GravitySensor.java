package fr.intech.sungsamhelth.model;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

public class GravitySensor extends Activity {
    private SensorManager sensorManager;
    private static Sensor sensor;

    public GravitySensor() {
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }

    public static Sensor getSensor() {
        return sensor;
    }
}
