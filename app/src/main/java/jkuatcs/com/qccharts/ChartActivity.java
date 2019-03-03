package jkuatcs.com.qccharts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jkuatcs.com.qccharts.models.QData;
import jkuatcs.com.qccharts.utils.CalculationMatrix;
import jkuatcs.com.qccharts.utils.QcTable;

public class ChartActivity extends AppCompatActivity {

    private LineChart chart;
    private LineChart rchart;
    TextView xucl,xlcl,rucl,rlcl;

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
        qData.add(new QData(43,5));
        qData.add(new QData(49,6));
        qData.add(new QData(37,5));
        qData.add(new QData(44,7));
        qData.add(new QData(45,7));
        qData.add(new QData(37,4));
        qData.add(new QData(51,8));
        qData.add(new QData(46,6));
        qData.add(new QData(43,4));
        qData.add(new QData(47,6));

        setXData(qData,5);
        setRData(qData,5);

    }

    private void setXData(List<QData> qData,int sample_size) {


        CalculationMatrix calculationMatrix= new CalculationMatrix(qData);
        QcTable qcTable= new QcTable();


        ArrayList<Entry> ucl_entries = new ArrayList<>();
        ArrayList<Entry> lcl_entries = new ArrayList<>();
        ArrayList<Entry> values = new ArrayList<>();

        xucl.setText("X-UCL:"+df.format(calculationMatrix.getXUCL(qcTable.getSampleConstants(sample_size))));
        xlcl.setText("X-LCL:"+df.format(calculationMatrix.getXLCL(qcTable.getSampleConstants(sample_size))));


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
        x_lineSet.setColor(Color.YELLOW);
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
        x_lineSet.setColor(Color.YELLOW);
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

}
