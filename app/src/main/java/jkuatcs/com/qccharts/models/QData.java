package jkuatcs.com.qccharts.models;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.math.BigDecimal;
import java.nio.DoubleBuffer;
import java.util.ArrayList;

import jkuatcs.com.qccharts.MainActivity;

// one row in the table
public class QData {

    public double x_bar;
    public double r;
    public String size_values;

    public QData(double x_bar,double r){
        this.x_bar = x_bar;
        this.r = r;
    }

    //Doing x_bar and r analysis on data entry (sample sizes)
    //Formulate a function to check size of entered data, to ensure as big as sample size, before storing
    public QData(String coma_values){
        this.size_values = coma_values;

        //Added a .0 to provide proper double precision
        double total = 0.0;
        double largest = getSizesAsDoubles().get(0);
        double smallest = getSizesAsDoubles().get(0);

        for (Double d:getSizesAsDoubles()){
            if(d>largest){ largest =d; }
            if(d<smallest){ smallest =d; }
            total+=d;
        }

        //Finally extracted x_bar and r values
        this.x_bar = Double.valueOf(String.valueOf(BigDecimal.valueOf(total).divide(BigDecimal.valueOf(getSizesAsDoubles().size()))));

        //Use big decimal, to avoid precision loss
        this.r = Double.valueOf(String.valueOf(BigDecimal.valueOf(largest).subtract(BigDecimal.valueOf(smallest))));

        //Log to see whether this has worked
        Log.e("Info","Value of smallest = " + String.valueOf(smallest));
        Log.e("Info", "Value of largest = " + String.valueOf(largest));

    }

    //Function to change string to double values
    //Ensure split has no letter in it, in validation function

    public ArrayList<Double> getSizesAsDoubles(){
        ArrayList<Double> all = new ArrayList<>();
        for(String s: size_values.split(",")){

            if (!s.isEmpty()){

                all.add(Double.valueOf(s));
            }
        }
        return all;
    }



}
