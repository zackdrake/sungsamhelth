package fr.intech.sungsamhelth.model;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.Log;

public class GravitySensor extends Activity {
    private SensorManager sensorManager;
    private static Sensor sensor;
    private static Sensor sensor1;


    public GravitySensor() {
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensor1 = sensorManager.getDefaultSensor((Sensor.TYPE_STEP_DETECTOR));

    }

    public static Sensor getSensor() {
        return sensor;
    }

    public static Sensor getSensor1() {
        return sensor1;
    }


}
