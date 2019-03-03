package jkuatcs.com.qccharts.utils;

import java.util.ArrayList;
import java.util.List;

import jkuatcs.com.qccharts.models.QcConstants;

public class QcTable {

    private List<QcConstants> qcConstants;

    public QcTable(){

        init();

    }

    private void init() {
        qcConstants = new ArrayList<>();
        qcConstants.add(new QcConstants(1.880,2.659,1.128,0,3.267,0,3.267));
        qcConstants.add(new QcConstants(1.023,1.954,1.693,0,2.574,0,2.568));
        qcConstants.add(new QcConstants(0.729,1.628,2.059,0,2.282,0,2.266));
        qcConstants.add(new QcConstants(0.577,1.427,2.326,0,2.114,0,2.089));
        qcConstants.add(new QcConstants(0.483,1.287,2.534,0,2.004,0.030,1.970));
        qcConstants.add(new QcConstants(0.419,1.182,2.704,0.076,1.924,0.118,1.882));
        qcConstants.add(new QcConstants(0.373,1.099,2.847,0.136,1.864,0.185,1.815));
        qcConstants.add(new QcConstants(0.337,1.032,2.970,0.184,1.816,0.239,1.761));
        qcConstants.add(new QcConstants(0.308,0.975,3.078,0.223,1.777,0.284,1.716));
        qcConstants.add(new QcConstants(0.285,0.927,3.173,0.256,1.744,0.321,1.679));
        qcConstants.add(new QcConstants(0.266,0.886,3.258,0.283,1.717,0.354,1.646));
        qcConstants.add(new QcConstants(0.249,0.850,3.336,0.307,1.693,0.382,1.618));
        qcConstants.add(new QcConstants(0.235,0.817,3.407,0.328,1.672,0.406,1.594));
        qcConstants.add(new QcConstants(0.223,0.789,3.472,0.347,1.653,0.428,1.572));
        qcConstants.add(new QcConstants(0.212,0.763,3.532,0.363,1.637,0.448,1.552));
        qcConstants.add(new QcConstants(0.203,0.739,3.588,0.378,1.622,0.466,1.534));
        qcConstants.add(new QcConstants(0.194,0.718,3.640,0.391,1.608,0.482,1.518));
        qcConstants.add(new QcConstants(0.187,0.698,3.689,0.403,1.597,0.497,1.503));
        qcConstants.add(new QcConstants(0.180,0.680,3.735,0.415,1.585,0.510,1.490));
        qcConstants.add(new QcConstants(0.173,0.663,3.778,0.425,1.575,0.523,1.477));
        qcConstants.add(new QcConstants(0.167,0.647,3.819,0.434,1.566,0.534,1.466));
        qcConstants.add(new QcConstants(0.162,0.633,3.858,0.443,1.557,0.545,1.455));
        qcConstants.add(new QcConstants(0.157,0.619,3.895,0.451,1.548,0.555,1.445));
        qcConstants.add(new QcConstants(0.153,0.606,3.931,0.459,1.541,0.565,1.435));
    }


    public QcConstants getSampleConstants(int sampleSize){
        return qcConstants.get(sampleSize-2);
    }

}
