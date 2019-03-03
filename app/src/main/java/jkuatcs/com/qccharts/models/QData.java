package jkuatcs.com.qccharts.models;

import android.content.Intent;

import java.util.ArrayList;

public class QData {

    public double x_bar;
    public double r;
    public String size_values;

    public QData(double x_bar,double r){
        this.x_bar = x_bar;
        this.r = r;
    }

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

        this.x_bar = total/getSizesAsDoubles().size();
        this.r = largest-smallest;
    }

    public ArrayList<Double> getSizesAsDoubles(){
        ArrayList<Double> all = new ArrayList<>();
        for(String s: size_values.split(",")){
            all.add(Double.valueOf(s));
        }
        return all;
    }



}
