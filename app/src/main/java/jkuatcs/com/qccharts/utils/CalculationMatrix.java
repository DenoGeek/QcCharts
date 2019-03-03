package jkuatcs.com.qccharts.utils;


import java.util.ArrayList;
import java.util.List;

import jkuatcs.com.qccharts.models.QData;
import jkuatcs.com.qccharts.models.QcConstants;

// Class containing calculations for X and R chart generation formulaes
public class CalculationMatrix {


    private double xmean;
    private double rbar;


    public CalculationMatrix(List<QData> qData) {

        int sumx = 0, sumr = 0;
        for(QData q:qData){
            sumx += q.x_bar;
            sumr += q.r;
        }
        xmean = sumx/qData.size();
        rbar = sumr/qData.size();
    }


    // CL for x bar chart
    private double getXbar(){
        return this.xmean;
    }


    private double getRbar(){


        return this.rbar;

    }


    //LCL and UCL methods for x  chart
    private double getXLCL(QcConstants qcConstants){
        double result = 0;

        result = getXbar() - (qcConstants.A_2 * getRbar());

        return result;

    }


    private double getXUCL(QcConstants qcConstants){
        double result = 0;

        result = xmean + (qcConstants.A_2 * rbar);

        return result;
    }



    //LCL UCL for R  chart
    private double getRLCL(QcConstants qcConstants ){
        double result = 0;

        result =  (qcConstants.D_3 * getRbar());

        return result;

    }


    private double getRUCL(QcConstants qcConstants){
        double result = 0;

        result = (qcConstants.D_4 * getRbar());

        return result;
    }



}
