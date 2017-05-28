package net.nepepe.smartfarm;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class SensorDataDetailActivity  extends AppCompatActivity {

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.sensor_detail_title));
        setSupportActionBar(toolbar);

       // operationMode = getIntent().getStringExtra("operation_mode");

        //set back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView currentReadingTextView = (TextView) findViewById(R.id.current_reading_tv);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        showChart();
    }

    private void showChart(){
        mChart = (LineChart) findViewById(R.id.chart);

        mChart.setData(generateDataLine(1));
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);
       // mChart.setDrawLineShadow(false);
        mChart.setDrawGridBackground(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(false);

        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getAxisRight().setDrawGridLines(false);

        mChart.getAxisRight().setDrawAxisLine(false);
        mChart.getAxisLeft().setDrawAxisLine(false);
        mChart.getAxisLeft().setDrawLabels(true);
        mChart.getAxisLeft().setTextColor(Color.parseColor("#ffffff"));
        mChart.getAxisRight().setDrawLabels(false);


        xAxis.setDrawGridLines(false);
        mChart.getAxisLeft().setDrawGridLines(false);
        // add a nice and smooth animation
        mChart.animateY(2500);

        mChart.getLegend().setEnabled(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setTextColor(Color.parseColor("#ffffff"));
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private BarData generateDataBar(int cnt) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        List<Integer> colors = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            entries.add(new BarEntry(i, (int) (Math.random() * 70000) + 30));
            colors.add(Color.parseColor("#ffffff"));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
        //d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        //d.setHighLightAlpha(255);

        d.setColors(colors);

        BarData cd = new BarData(d);
        cd.setBarWidth(0.9f);
        return cd;
    }



    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private LineData generateDataLine(int cnt) {

        List<String> tempList = new ArrayList<String>();

        for (int i = 0; i < 14; i++) {
            tempList.add(String.format("D%s", i));
        }
        String[] labels = new String[ tempList.size() ];
        tempList.toArray( labels );

        mChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

        ArrayList<Entry> e1 = new ArrayList<Entry>();

        for (int i = 0; i < 14; i++) {
            e1.add(new Entry(i, (int) (Math.random() * 65) + 40));
        }

        LineDataSet d1 = new LineDataSet(e1, "New DataSet " + cnt + ", (1)");
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(255, 255, 255));
        d1.setDrawValues(false);
        d1.setColor(Color.parseColor("#ffffff"));
        d1.setCircleColor(Color.parseColor("#ffffff"));

        ArrayList<Entry> e2 = new ArrayList<Entry>();

        for (int i = 0; i < 14; i++) {
            e2.add(new Entry(i, e1.get(i).getY() - 30));
        }


        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
        //sets.add(d2);

        LineData cd = new LineData(sets);
        return cd;
    }

}
