package fr.intech.sungsamhelth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import fr.intech.sungsamhelth.model.GravitySensor;

public class MainActivity extends AppCompatActivity {
    private TextView NombrePas;
    private TextView numberGlass;
    private ImageButton moreGlass;
    private ImageButton lessGlass;
    private ImageButton infoGlass;
    int glass = 0;
    int steps;
    CharSequence text = "Il est recommendé de boire 8 verres d'eau par jour.";
    int duration = Toast.LENGTH_LONG;
    int dayOFTheMonth = Calendar.DAY_OF_MONTH;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NombrePas = findViewById(R.id.NBPas);
        numberGlass = findViewById(R.id.NBGlass);
        moreGlass = findViewById(R.id.glassButtonMore);
        lessGlass = findViewById(R.id.glassButtonLess);
        infoGlass = findViewById(R.id.glassButtonInfo);
        SharedPreferences sharedPref = getSharedPreferences("date", 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDate = sdf.format(new Date());
        if (sharedPref.getString("LAST_LAUNCH_DATE","nodate").contains(currentDate)){
            glass = getPreferences(MODE_PRIVATE).getInt("glassNumber",0);
            numberGlass.setText(String.valueOf(glass));
        }
        else
        {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("LAST_LAUNCH_DATE", currentDate);
            editor.commit();

        }

        moreGlass.setOnClickListener(v -> {
            glass++;
            numberGlass.setText(String.valueOf(glass));
            getPreferences(MODE_PRIVATE).edit().putInt("glassNumber",glass).commit();
        });

        lessGlass.setOnClickListener(v -> {
            if (glass > 0) {
                glass--;
                numberGlass.setText(String.valueOf(glass));
                getPreferences(MODE_PRIVATE).edit().putInt("glassNumber",glass).commit();

            }
        });

        infoGlass.setOnClickListener(v -> {
            Toast.makeText(this, text, duration).show();
        });
        Sensor sensor = GravitySensor.getSensor();
        Sensor sensor1 = GravitySensor.getSensor1();

    }

    public void onSensorChanged(final SensorEvent event) {
        steps = (int) event.values[0];
        NombrePas.setText(String.valueOf(steps));
        Log.i("step", "" + steps);
    }

}