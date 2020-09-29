package fr.intech.sungsamhelth.model;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

public class GravitySensor {
    private SensorManager sensorManager;
    private Sensor sensor;

    public GravitySensor() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }
}
