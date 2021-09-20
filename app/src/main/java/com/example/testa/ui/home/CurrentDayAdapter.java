package com.example.testa.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.testa.R;
import com.example.testa.pojo.Utilities;
import com.example.testa.pojo.modules.DayDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrentDayAdapter extends RecyclerView.Adapter<CurrentDayAdapter.DayViewHolder> {

    private final Context mContext;
    private List<DayDetails> dayDetails;

    public CurrentDayAdapter(Context mContext, ArrayList<DayDetails> dayDetails) {
        this.mContext = mContext;
        this.dayDetails = dayDetails;
    }

    public void setDayDetails(List<DayDetails> dayDetails) {
        this.dayDetails = dayDetails;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.current_day_layout, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        DayDetails dayDetails = this.dayDetails.get(position);

        holder.txtTemp.setText(dayDetails.getDetails().getTempTxt());
        holder.mainCard.setCardBackgroundColor(mContext.getResources().getColor(R.color.colorA2));

        holder.txtTime.setText(dayDetails.getTime());
        String icon = dayDetails.getWeather().get(0).getIcon();
        holder.imgWeatherState.setImageResource(Utilities.setIcon(icon));

        if (position == 0) {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            holder.txtTime.setText(format.format(new Date()));
            holder.txtTemp.setText("NOW");
            holder.mainCard.setCardBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
        }


    }

    @Override
    public int getItemCount() {
        return dayDetails.size();
    }

    static class DayViewHolder extends RecyclerView.ViewHolder {
        CardView mainCard;
        TextView txtTime, txtTemp;
        ImageView imgWeatherState;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            mainCard = itemView.findViewById(R.id.mainCard);
            txtTemp = itemView.findViewById(R.id.txtTemp);
            txtTime = itemView.findViewById(R.id.txtTime);
            imgWeatherState = itemView.findViewById(R.id.imgWeatherState);
        }
    }
}
