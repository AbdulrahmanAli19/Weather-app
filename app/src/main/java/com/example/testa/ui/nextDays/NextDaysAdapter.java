package com.example.testa.ui.nextDays;

import static com.example.testa.pojo.Utilities.getDayAndMonth;
import static com.example.testa.pojo.Utilities.getWeek;
import static com.example.testa.pojo.Utilities.setIcon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.testa.R;
import com.example.testa.pojo.modules.DayDetails;

import java.text.ParseException;
import java.util.ArrayList;

public class NextDaysAdapter extends RecyclerView.Adapter<NextDaysAdapter.DaysViewHolder> {
    private static final String TAG = "NextDaysAdapter";
    private final Context mContext;
    private final ArrayList<DayDetails> dayDetails;

    public NextDaysAdapter(Context mContext, ArrayList<DayDetails> dayDetails) {
        this.mContext = mContext;
        this.dayDetails = dayDetails;
    }

    @NonNull
    @Override
    public DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.next_days_layout, parent, false);
        return new DaysViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DaysViewHolder holder, int position) {
        DayDetails current = dayDetails.get(position);

        holder.txtMaxTemp.setText(current.getDetails().getTempTxt());
        holder.txtMinTemp.setText(current.getDetails().getFeelsLikeTxt());

        int icon = setIcon(current.getWeather().get(0).getIcon());
        holder.imgWeatherState.setImageResource(icon);

        try {
            holder.txtWeek.setText(getWeek(current.getDateTxt()) + ", ");
            holder.txtDay.setText(getDayAndMonth(current.getDateTxt()));

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return dayDetails.size();
    }

    static class DaysViewHolder extends RecyclerView.ViewHolder {
        ImageView imgWeatherState;
        TextView txtWeek, txtDay, txtMaxTemp, txtMinTemp;

        public DaysViewHolder(@NonNull View itemView) {
            super(itemView);
            imgWeatherState = itemView.findViewById(R.id.imgWeatherState);
            txtDay = itemView.findViewById(R.id.txtDay);
            txtWeek = itemView.findViewById(R.id.txtWeek);
            txtMaxTemp = itemView.findViewById(R.id.txtTemp);
            txtMinTemp = itemView.findViewById(R.id.txtFeelsLike);
        }
    }
}
