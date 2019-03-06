package jkuatcs.com.qccharts.models;

import android.content.Intent;

import java.util.ArrayList;

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
        double total = 0;
        double largest = getSizesAsDoubles().get(0);
        double smallest = getSizesAsDoubles().get(0);

        for (Double d:getSizesAsDoubles()){
            if(d>largest){ largest =d; }
            if(d<smallest){ smallest =d; }
            total+=d;
        }

        //Finally extracted x_bar and r values
        this.x_bar = total/getSizesAsDoubles().size();
        this.r = largest-smallest;
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
