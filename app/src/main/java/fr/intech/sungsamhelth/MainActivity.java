package fr.intech.sungsamhelth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import fr.intech.sungsamhelth.model.GravitySensor;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView NombrePas;
    private TextView numberGlass;
    private ImageButton moreGlass;
    private ImageButton lessGlass;
    private ImageButton infoGlass;
    private ImageButton infoSleep;
    private ImageButton okSleep;
    private EditText sleepTime;
    private TextView sleep;
    int glass = 0;
    int steps;
    String dureeSommeil;
    int dayOFTheMonth = Calendar.DAY_OF_MONTH;
    private TextView textViewStepCounter;
    private SensorManager sensorManager;
    private Sensor nStepCounter;
    private boolean isCounterSensorPresent;
    int stepCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        textViewStepCounter = findViewById(R.id.textViewStepCounter);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            nStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        } else {
            textViewStepCounter.setText("Step Counter isn't present on your phone");
            isCounterSensorPresent = false;
        }

        numberGlass = findViewById(R.id.NBGlass);
        moreGlass = findViewById(R.id.glassButtonMore);
        lessGlass = findViewById(R.id.glassButtonLess);
        infoGlass = findViewById(R.id.glassButtonInfo);
        sleepTime = findViewById(R.id.sleepingTime);
        sleep = findViewById(R.id.sleepingText);
        okSleep = findViewById(R.id.sleepingButtonSend);
        infoSleep = findViewById(R.id.sleepingButtonInfo);

        SharedPreferences sharedPref = getSharedPreferences("date", 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDate = sdf.format(new Date());
        if (sharedPref.getString("LAST_LAUNCH_DATE", "nodate").contains(currentDate)) {
            glass = getPreferences(MODE_PRIVATE).getInt("glassNumber", 0);
            numberGlass.setText(String.valueOf(glass));
        } else {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("LAST_LAUNCH_DATE", currentDate);
            editor.commit();

        }


        dureeSommeil = sleepTime.getText().toString();
        okSleep.setOnClickListener(v -> {
            sleep.setText("aujourdhui vous avez dormi " + dureeSommeil);
        });

        moreGlass.setOnClickListener(v -> {
            glass++;
            numberGlass.setText(String.valueOf(glass));
            getPreferences(MODE_PRIVATE).edit().putInt("glassNumber", glass).commit();
        });

        lessGlass.setOnClickListener(v -> {
            if (glass > 0) {
                glass--;
                numberGlass.setText(String.valueOf(glass));
                getPreferences(MODE_PRIVATE).edit().putInt("glassNumber", glass).commit();

            }
        });

        infoGlass.setOnClickListener(v -> {
            CharSequence text = "Il est recommendé de boire 8 verres d'eau par jour.";
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(this, text, duration).show();
        });

        infoSleep.setOnClickListener(v -> {
            CharSequence text = "Il est recommendé de dormir de  7 à 8 heures par jour.";
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(this, text, duration).show();

        });
        Sensor sensor = GravitySensor.getSensor();
        Sensor sensor1 = GravitySensor.getSensor1();

    }

    public void onSensorChanged(final SensorEvent event) {
        if (event.sensor == nStepCounter) {
            stepCount = (int) event.values[0];
            textViewStepCounter.setText(String.valueOf(stepCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.registerListener(this, nStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.unregisterListener(this, nStepCounter);
        }
    }
}