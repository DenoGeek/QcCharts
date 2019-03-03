package jkuatcs.com.qccharts.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jkuatcs.com.qccharts.R;
import jkuatcs.com.qccharts.models.QData;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<QData> data;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        // define views

        public MyViewHolder(View itemView) {
            super(itemView);

            // get views
        }
    }

    public RecyclerViewAdapter(Context mContext,ArrayList<QData> data) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return data.size();
    }




    public ArrayList<QData> getData() {
        return data;
    }
}