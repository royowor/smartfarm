package net.nepepe.smartfarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity  {

    private static final String SENSOR_TYPE = "sensor_type";
    private ImageView soilMoistureImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soilMoistureImageView = (ImageView) findViewById(R.id.soil_moisture_btn);
        soilMoistureImageView.setOnClickListener(onDasboardBtnClickListener);
    }


    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener onDasboardBtnClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.soil_moisture_btn:

                    Intent intent = new Intent(getApplicationContext(), SensorDataDetailActivity.class);
                    intent.putExtra(SENSOR_TYPE, "0" );
                    startActivity(intent);

                    break;
                default:
                    return;
            }


        }
    };

}
