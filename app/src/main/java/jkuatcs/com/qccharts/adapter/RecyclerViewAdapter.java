package jkuatcs.com.qccharts.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import jkuatcs.com.qccharts.MainActivity;
import jkuatcs.com.qccharts.R;
import jkuatcs.com.qccharts.models.QData;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<QData> data;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        // define views

        TextView sno, sampledata, xbar, r;

        public MyViewHolder(View itemView) {
            super(itemView);

            // get views
            sno = itemView.findViewById(R.id.tvsno);
            sampledata = itemView.findViewById(R.id.tvsampledata);
            xbar = itemView.findViewById(R.id.tvx);
            r = itemView.findViewById(R.id.tvr);

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

        holder.sno.setText(String.valueOf(position + 1));

        //Put sample data value based on what we are computing
        if (MainActivity.isMeanComputing()){
            holder.sampledata.setText("N/A");
        } else{

            holder.sampledata.setText(String.valueOf(data.get(position).size_values));
        }
        holder.xbar.setText(String.valueOf(data.get(position).x_bar));
        holder.r.setText(String.valueOf(data.get(position).r));

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(){
        this.notifyDataSetChanged();
    }

    public void clearData(){

        this.data.clear();
        this.notifyDataSetChanged();
    }



    public ArrayList<QData> getData() {
        return data;
    }
}