package jkuatcs.com.qccharts.utils;


import java.util.ArrayList;
import java.util.List;

import jkuatcs.com.qccharts.models.QData;
import jkuatcs.com.qccharts.models.QcConstants;

// Class containing calculations for X and R chart generation formulaes
public class CalculationMatrix {


    private double xbar;
    private double rbar;


    public CalculationMatrix(QData qData) {
        int sumx = 0, sumr = 0;

        sumx += qData.r;
        sumr += qData.x_bar;



    }


    // CL for x bar chart
    private double getXbar(){
        return this.xbar;
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

        result = xbar + (qcConstants.A_2 * rbar);

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
