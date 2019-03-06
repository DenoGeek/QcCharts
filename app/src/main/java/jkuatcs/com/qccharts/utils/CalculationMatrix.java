package jkuatcs.com.qccharts.utils;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jkuatcs.com.qccharts.models.QData;
import jkuatcs.com.qccharts.models.QcConstants;

// Class containing calculations for X and R chart generation formulaes
public class CalculationMatrix {


    private double xmean;
    private double rbar;
    private List<QData> qData;
    private double xucl;
    private double xlcl;
    private double rlcl;
    private double rucl;


    public CalculationMatrix(List<QData> qData) {
        this.qData = qData;

        int sumx = 0, sumr = 0;
        for(QData q:qData){
            sumx += q.x_bar;
            sumr += q.r;
        }
        xmean = sumx/(double)qData.size();
        rbar = sumr/(double)qData.size();
    }


    // CL for x bar chart
    public double getXbar(){
        return this.xmean;
    }


    public double getRbar(){


        return this.rbar;

    }


    //LCL and UCL methods for x  chart
    public double getXLCL(QcConstants qcConstants){
        double result = 0;
        result = getXbar() - (qcConstants.A_2 * getRbar());
        this.xlcl = result;
        return result;

    }


    public double getXUCL(QcConstants qcConstants){
        double result = 0;

        result = xmean + (qcConstants.A_2 * rbar);

        this.xucl = result;

        return result;
    }

    public String xisOutOfBounds(){


        String message = "";
        int countabove = 0;
        int countbelow = 0;
        for(QData q:qData){

            if(xucl<q.x_bar){
                countabove += 1;
            }
            if(xlcl>q.x_bar){
                countbelow += 1;
            }
        }
        if(countabove > 0 || countbelow > 0){
            message = countabove+" points lies above the UCL and "+countbelow+" lie below the LCL hence process is not under control";
        }else{
            message = "Range chart lies within the control lines hence process is under control";
        }
        return message;
    }




    //LCL UCL for R  chart
    public double getRLCL(QcConstants qcConstants ){
        double result = 0;

        result =  (qcConstants.D_3 * getRbar());
        this.rlcl = result;
        return result;

    }


    public double getRUCL(QcConstants qcConstants){
        double result = 0;

        result = (qcConstants.D_4 * getRbar());

        this.rucl = result;
        return result;
    }

    public String risOutOfBounds(){


        String message = "";
        int countabove = 0;
        int countbelow = 0;
        for(QData q:qData){

            if(rucl<q.r){
                countabove += 1;
            }
            if(rlcl>q.r){
                countbelow += 1;
            }
        }
        if(countabove > 0 || countbelow > 0){
            message = countabove+" points lies above the UCL and "+countbelow+" lie below the LCL hence process is not under control";
        }else{
            message = "Range chart lies within the control lines hence process is under control";
        }
        return message;
    }

}
