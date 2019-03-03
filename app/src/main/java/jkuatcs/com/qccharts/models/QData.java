package jkuatcs.com.qccharts.models;

import android.content.Intent;

import java.util.ArrayList;

// one row in the table
public class QData {

    public double x_bar;
    public double r;
    public String size_values;

    public ArrayList<Integer> getSizesAsInts(){
        ArrayList<Integer> all = new ArrayList<>();
        for(String s: size_values.split(",")){
            all.add(Integer.valueOf(s));
        }
        return all;
    }


}
