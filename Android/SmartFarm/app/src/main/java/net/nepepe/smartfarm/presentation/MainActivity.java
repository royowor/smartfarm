package net.nepepe.smartfarm.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import net.nepepe.smartfarm.R;

public class MainActivity extends AppCompatActivity  {

    private static final String SENSOR_TYPE = "sensor_type";
    private ImageView soilMoistureImageView;
    private ImageView temperatureImageView;
    private ImageView lightingImageView;
    private ImageView airflowImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soilMoistureImageView = (ImageView) findViewById(R.id.soil_moisture_btn);
        soilMoistureImageView.setOnClickListener(onDasboardBtnClickListener);

        temperatureImageView = (ImageView) findViewById(R.id.temperature_dashboard_btn);
        temperatureImageView.setOnClickListener(onDasboardBtnClickListener);

        lightingImageView = (ImageView) findViewById(R.id.lighting_dashboard_btn);
        lightingImageView.setOnClickListener(onDasboardBtnClickListener);

        airflowImageView = (ImageView) findViewById(R.id.airflow_dashboard_btn);
        airflowImageView.setOnClickListener(onDasboardBtnClickListener);

    }


    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener onDasboardBtnClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.soil_moisture_btn:

                    boolean moistureIsCritical = true;
                    if(moistureIsCritical) {
                        Intent soilMoistureCriticalIntent = new Intent(getApplicationContext(), TakeActionActivity.class);
                        soilMoistureCriticalIntent.putExtra(SENSOR_TYPE, "moisture" );
                        startActivity(soilMoistureCriticalIntent);
                    }else {

                        Intent soilMoistureIntent = new Intent(getApplicationContext(), SensorDataDetailActivity.class);
                        soilMoistureIntent.putExtra(SENSOR_TYPE, "moisture" );
                        startActivity(soilMoistureIntent);
                    }



                    break;

                case R.id.temperature_dashboard_btn:

                    Intent temperatureIntent = new Intent(getApplicationContext(), SensorDataDetailActivity.class);
                    temperatureIntent.putExtra(SENSOR_TYPE, "temperature" );
                    startActivity(temperatureIntent);

                    break;

                case R.id.lighting_dashboard_btn:

                    Intent lightingIntent = new Intent(getApplicationContext(), SensorDataDetailActivity.class);
                    lightingIntent.putExtra(SENSOR_TYPE, "lighting" );
                    startActivity(lightingIntent);

                    break;

                case R.id.airflow_dashboard_btn:

                    Intent airflowIntent = new Intent(getApplicationContext(), SensorDataDetailActivity.class);
                    airflowIntent.putExtra(SENSOR_TYPE, "airflow" );
                    startActivity(airflowIntent);

                    break;

                default:
                    return;
            }


        }
    };

}
