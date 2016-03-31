package cool.flexime.test_video_position;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Context;


public class MainActivity extends ActionBarActivity {

    // Variables pour les sensors de rotation
    private boolean recordingRotations;
    TextView textX, textY, textZ;
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisations pour la rotation
        recordingRotations = false;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        textX = (TextView) findViewById(R.id.textX);
        textY = (TextView) findViewById(R.id.textY);
        textZ = (TextView) findViewById(R.id.textZ);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /** Called when the user touches the button */
    public void videoRecordClick(View view) {
        TextView t =(TextView) findViewById(R.id.textView);
        t.setText("Fuck me!");
    }

    /** Called when the user touches the button */
    public void rotationRecordClick(View view) {
        TextView t = (TextView) findViewById(R.id.textView);
        Button b = (Button) findViewById(R.id.rotation_button);

        // Activation des sensors
        if(!recordingRotations){
            b.setText("Stop recording rotations");
            t.setText("Spin motherfucka!");

            sensorManager.registerListener(gyroListener, sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

            recordingRotations=true;
        }
        // Arret des sensors
        else{
            b.setText("Record rotations");
            t.setText("Wuba luba dub dub!");

            sensorManager.unregisterListener(gyroListener);

            recordingRotations=false;

        }

    }

//    public void onResume() {
//        super.onResume();
//        sensorManager.registerListener(gyroListener, sensor,
//                SensorManager.SENSOR_DELAY_NORMAL);
//    }
//
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(gyroListener);
    }

    public SensorEventListener gyroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) { }
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            textX.setText("GyroX : " + String.format("%.2f",x) + " rad/s");
            textY.setText("GyroY : " + String.format("%.2f",y) + " rad/s");
            textZ.setText("GyroZ : " + String.format("%.2f",z) + " rad/s");
        }
    };
}
