package com.example.hazardmap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<CardItem> items;

    public CardAdapter(List<CardItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_report, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardItem item = items.get(position);
        holder.reportTitle.setText(item.title);
        holder.reportType.setText("Hazard Type : " + item.type);
        holder.reportName.setText("Reporter : " + item.reporter);
        holder.reportTime.setText(item.day + "/" + item.month + "/" + item.year + ", " + item.hour + ":" + item.minute + " " + item.tod);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView reportTitle, reportType, reportName, reportTime;

        public ViewHolder(View itemView) {
            super(itemView);
            reportTitle = itemView.findViewById(R.id.reportTitle);
            reportType = itemView.findViewById(R.id.reportType);
            reportName = itemView.findViewById(R.id.reportName);
            reportTime = itemView.findViewById(R.id.reportTime);
        }
    }
}