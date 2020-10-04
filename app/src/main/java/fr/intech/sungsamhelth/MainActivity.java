package fr.intech.sungsamhelth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import fr.intech.sungsamhelth.model.GravitySensor;

public class MainActivity extends AppCompatActivity {
    private TextView NombrePas;
    private TextView numberGlass;
    private Button moreGlass;
    private Button lessGlass;
    private ImageButton infoGlass;
    int glass = 0;
    int steps;
    Context context = getApplicationContext();
    CharSequence text = "Hello toast!";
    int duration = Toast.LENGTH_SHORT;

    Toast toast = Toast.makeText(context, text, duration);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NombrePas = findViewById(R.id.NBPas);
        numberGlass = findViewById(R.id.NBGlass);
        moreGlass = findViewById(R.id.glassButtonMore);
        lessGlass = findViewById(R.id.glassButtonLess);
        infoGlass = findViewById(R.id.glassButtonInfo);

        moreGlass.setOnClickListener(v -> {
            glass ++;
            numberGlass.setText(String.valueOf(glass));
        });

        lessGlass.setOnClickListener(v -> {
            glass --;
            numberGlass.setText(String.valueOf(glass));
        });

        infoGlass.setOnClickListener(v -> {
            toast.show();
        });
        Sensor sensor = GravitySensor.getSensor();
        Sensor sensor1 = GravitySensor.getSensor1();

    }
    public void onSensorChanged(final SensorEvent event) {
        steps = (int) event.values[0];
        NombrePas.setText(String.valueOf(steps));
        Log.i("step",""+steps);
    }

}