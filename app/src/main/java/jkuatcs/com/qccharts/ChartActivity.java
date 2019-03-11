package jkuatcs.com.qccharts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jkuatcs.com.qccharts.models.QData;
import jkuatcs.com.qccharts.utils.CalculationMatrix;
import jkuatcs.com.qccharts.utils.QcTable;

public class ChartActivity extends AppCompatActivity {

    private LineChart chart;
    private LineChart rchart;
    TextView xucl,xlcl,rucl,rlcl,x_analysis,r_analysis;

    DecimalFormat df = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        chart = findViewById(R.id.chart1);
        rchart = findViewById(R.id.rcharts);
        xucl = findViewById(R.id.xucl);
        xlcl = findViewById(R.id.xlcl);
        rucl = findViewById(R.id.rucl);
        rlcl = findViewById(R.id.rlcl);
        x_analysis = findViewById(R.id.x_analysis);
        r_analysis = findViewById(R.id.r_analysis);

        // background color
        chart.setBackgroundColor(Color.WHITE);
        rchart.setBackgroundColor(Color.WHITE);
        // disable description text
        chart.getDescription().setEnabled(false);
        rchart.getDescription().setEnabled(false);
        // enable touch gestures
        chart.setTouchEnabled(true);
        rchart.setTouchEnabled(true);

        List<QData> qData = new ArrayList<>();

        //get my string from intent
        String jsonQDataList = getIntent().getStringExtra("qDataList");

        Gson gson = new Gson();
        Type type = new TypeToken<List<QData>>(){}.getType();

        qData = gson.fromJson(jsonQDataList,type);

        if (getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

//        qData.add(new QData(43,5));
//        qData.add(new QData(49,6));
//        qData.add(new QData(37,5));
//        qData.add(new QData(44,7));
//        qData.add(new QData(45,7));
//        qData.add(new QData(37,4));
//        qData.add(new QData(51,8));
//        qData.add(new QData(46,6));
//        qData.add(new QData(43,4));
//        qData.add(new QData(47,6));

//        qData.add(new QData("10,10.2,11.3,12.4"));
//        qData.add(new QData("10.3,10.9,10.7,11.7"));
//        qData.add(new QData("11.5,10.7,11.4,12.4"));
//        qData.add(new QData("11,11.3,10.7,11.4"));
//        qData.add(new QData("11.3,11.6,11.0,12.1"));
//        qData.add(new QData("10.7,11.4,10.7,11"));
//        qData.add(new QData("11.3,11.4,11.1,10.3"));
//        qData.add(new QData("12.3,12.1,12.7,10.7"));
//        qData.add(new QData("11,13.1,13.1,12.4"));
//        qData.add(new QData("11.3,12.1,10.7,11.5"));
//        qData.add(new QData("12.5,11.9,11.8,11.3"));
//        qData.add(new QData("11.9,12.1,11.6,11.4"));
//        qData.add(new QData("12.1,11.1,12.1,11.7"));
//        qData.add(new QData("11.9,12.1,13.1,12.0"));
//        qData.add(new QData("10.6,11.9,11.7,12.1"));

        setXData(qData,Integer.valueOf(getIntent().getStringExtra("sampleSize")));
        setRData(qData,Integer.valueOf(getIntent().getStringExtra("sampleSize")));

    }

    private void setXData(List<QData> qData,int sample_size) {

        Log.e("Error","Passed sample size = " + String.valueOf(sample_size));

        CalculationMatrix calculationMatrix= new CalculationMatrix(qData);
        QcTable qcTable= new QcTable();


        ArrayList<Entry> ucl_entries = new ArrayList<>();
        ArrayList<Entry> lcl_entries = new ArrayList<>();
        ArrayList<Entry> values = new ArrayList<>();

        xucl.setText("X-UCL:"+df.format(calculationMatrix.getXUCL(qcTable.getSampleConstants(sample_size))));
        xlcl.setText("X-LCL:"+df.format(calculationMatrix.getXLCL(qcTable.getSampleConstants(sample_size))));
        x_analysis.setText(calculationMatrix.xisOutOfBounds());

        for (int i = 0; i < qData.size(); i++) {
            values.add(new Entry(i, (float)qData.get(i).x_bar));
            ucl_entries.add(new Entry(i,(float)calculationMatrix.getXUCL(qcTable.getSampleConstants(sample_size))));
            lcl_entries.add(new Entry(i,(float)calculationMatrix.getXLCL(qcTable.getSampleConstants(sample_size))));
        }

        LineDataSet x_lineSet;
        LineDataSet uclSet;
        LineDataSet lclSet;
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();


        x_lineSet = new LineDataSet(values, "X-Bar chart values");
        x_lineSet.setDrawIcons(false);
        x_lineSet.setColor(Color.parseColor("#FF5722"));
        x_lineSet.setCircleColor(Color.BLACK);
        x_lineSet.setLineWidth(1f);
        x_lineSet.setCircleRadius(3f);
        dataSets.add(x_lineSet);

        uclSet = new LineDataSet(ucl_entries, "UCL upper limit");
        uclSet.setDrawIcons(false);
        uclSet.enableDashedLine(10f, 5f, 0f);
        uclSet.setColor(Color.GREEN);
        uclSet.setCircleColor(Color.RED);
        uclSet.setLineWidth(1f);
        uclSet.setCircleRadius(3f);
        dataSets.add(uclSet);

        lclSet = new LineDataSet(lcl_entries, "LCL-lower limit");
        lclSet.setDrawIcons(false);
        lclSet.enableDashedLine(10f, 5f, 0f);
        lclSet.setColor(Color.DKGRAY);
        lclSet.setCircleColor(Color.BLUE);
        lclSet.setLineWidth(1f);
        lclSet.setCircleRadius(3f);
        dataSets.add(lclSet);


        LineData data = new LineData(dataSets);
        chart.setData(data);
    }

    private void setRData(List<QData> qData,int sample_size) {


        CalculationMatrix calculationMatrix= new CalculationMatrix(qData);
        QcTable qcTable= new QcTable();


        ArrayList<Entry> ucl_entries = new ArrayList<>();
        ArrayList<Entry> lcl_entries = new ArrayList<>();
        ArrayList<Entry> values = new ArrayList<>();

        rucl.setText("R-UCL:"+df.format(calculationMatrix.getRUCL(qcTable.getSampleConstants(sample_size))));
        rlcl.setText("R-LCL:"+df.format(calculationMatrix.getRLCL(qcTable.getSampleConstants(sample_size))));
        r_analysis.setText(calculationMatrix.risOutOfBounds());


        for (int i = 0; i < qData.size(); i++) {
            values.add(new Entry(i, (float)qData.get(i).r));
            ucl_entries.add(new Entry(i,(float)calculationMatrix.getRUCL(qcTable.getSampleConstants(sample_size))));
            lcl_entries.add(new Entry(i,(float)calculationMatrix.getRLCL(qcTable.getSampleConstants(sample_size))));
        }

        LineDataSet x_lineSet;
        LineDataSet uclSet;
        LineDataSet lclSet;
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();


        x_lineSet = new LineDataSet(values, "X-Bar chart values");
        x_lineSet.setDrawIcons(false);
        x_lineSet.setColor(Color.parseColor("#FF5722"));
        x_lineSet.setCircleColor(Color.BLACK);
        x_lineSet.setLineWidth(1f);
        x_lineSet.setCircleRadius(3f);
        dataSets.add(x_lineSet);

        uclSet = new LineDataSet(ucl_entries, "UCL upper limit");
        uclSet.setDrawIcons(false);
        uclSet.enableDashedLine(10f, 5f, 0f);
        uclSet.setColor(Color.GREEN);
        uclSet.setCircleColor(Color.RED);
        uclSet.setLineWidth(1f);
        uclSet.setCircleRadius(3f);
        dataSets.add(uclSet);

        lclSet = new LineDataSet(lcl_entries, "LCL-lower limit");
        lclSet.setDrawIcons(false);
        lclSet.enableDashedLine(10f, 5f, 0f);
        lclSet.setColor(Color.DKGRAY);
        lclSet.setCircleColor(Color.BLUE);
        lclSet.setLineWidth(1f);
        lclSet.setCircleRadius(3f);
        dataSets.add(lclSet);


        LineData data = new LineData(dataSets);
        rchart.setData(data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:

                super.onBackPressed();
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }
}
