package com.example.testa.pojo;

import android.annotation.SuppressLint;

import com.example.testa.R;
import com.example.testa.pojo.modules.DayDetails;
import com.example.testa.pojo.modules.WeatherRespon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utilities {
    private static final String TAG = "Utilities";

    /**
     * this function gets the icon from resources based on OpenWeatherApi response
     */
    public static int setIcon(String icon) {
        if ("01n".equals(icon))
            return R.drawable.clear_night;
        else if ("01d".equals(icon))
            return R.drawable.mostly_sunny;
        else if ("02n".equals(icon))
            return R.drawable.party_cloudy_night;
        else if ("02d".equals(icon))
            return R.drawable.party_cloudy;
        else if ("03n".equals(icon))
            return R.drawable.mostly_cloudy_night;
        else if ("03d".equals(icon) || "04d".equals(icon) || "04n".equals(icon))
            return R.drawable.mostly_cloudy;
        else if ("09d".equals(icon) || "09n".equals(icon) || "10d".equals(icon))
            return R.drawable.rain;
        else if ("10n".equals(icon))
            return R.drawable.rain_nght;
        else if ("11d\"".equals(icon))
            return R.drawable.scattered_thunderstorm;
        else if ("11n".equals(icon))
            return R.drawable.scattered_thunderstorm_night;
        else if ("13d".equals(icon))
            return R.drawable.snow;
        else if ("13n".equals(icon))
            return R.drawable.snow_night;
        else if ("50d".equals(icon))
            return R.drawable.fog;
        else if ("50n".equals(icon))
            return R.drawable.fog_night;
        else
            return 0;

    }

    /**
     * this function covert the temp degree from kelvin to celsius
     */
    public static String fromKtoC(Float degree) {
        degree = (degree - 273.15f);

        return String.valueOf(degree.byteValue()) + "°";
    }

    /**
     * this function removes any quotation if exits
     */
   /* public static String removeQuotation(String text) {
        StringBuilder str = new StringBuilder(text);
        int i = 0;
        while (str.length() > i) {
            if (str.charAt(i) == '\"') {
                str.deleteCharAt(i);
            }
            i++;
        }
       *//* str.deleteCharAt(0);
        str.deleteCharAt(str.length() - 1);*//*
        return str.toString();
    }*/

    /***/
    public static List<JSONObject> fillCurrentDayArray(JSONArray list, List<JSONObject> dayState) {
        ///"dt_txt": "2020-10-09 18:00:00"
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder str;
        String date = dateFormat.format(new Date());

        for (int i = 0; i < list.length(); i++) {
            try {
                str = new StringBuilder(list.getJSONObject(i).get("dt_txt").toString());
                if (str.subSequence(0, 10).equals(date)) {
                    //JsonObject main2 = list.get(i).getAsJsonObject();
                    JSONObject main2 = list.getJSONObject(i);
                    dayState.add(main2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return dayState;
    }

    public static ArrayList<DayDetails> fillCurrentDayArray(WeatherRespon weatherRespon, ArrayList<DayDetails> adaptersArray) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        String thisDay;

        for (int i = 0; i < weatherRespon.getDayDetails().size(); i++) {
            thisDay = dateFormat.format(weatherRespon.getDayDetails().get(i).getDate() * 1000);
            if (thisDay.equals(currentDate) || thisDay == currentDate) {
                adaptersArray.add(weatherRespon.getDayDetails().get(i));
            }
        }
        return adaptersArray;
    }

    /***/
    public static List<JSONObject> fillNextDaysArray(JSONArray list, List<JSONObject> dayState)
            throws ParseException, JSONException {

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String currentDate = dateFormat.format(new Date());
        currentDate = currentDate + " 12:00:00";

        final Date date = dateFormat.parse(currentDate);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        for (int i = 0, j = 0; i < list.length() && j < 5; i++) {

            String str = list.getJSONObject(i).get("dt_txt").toString();
            String time = dateFormat.format(calendar.getTime()) + " 12:00:00";

            if (str.equals(time)) {
                j++;
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                JSONObject main2 = list.getJSONObject(i);
                dayState.add(main2);
            }
        }
        return dayState;
    }

    public static ArrayList<DayDetails> fillNextDaysArray(WeatherRespon weatherRespon, ArrayList<DayDetails> dayDetails) throws ParseException {

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String currentDate = dateFormat.format(new Date());
        currentDate = currentDate + " 12:00:00";

        final Date date;

        date = dateFormat.parse(currentDate);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        for (int i = 0, j = 0; i < weatherRespon.getDayDetails().size() && j < 5; i++) {

            String str = weatherRespon.getDayDetails().get(i).getDateTxt();
            String time = dateFormat.format(calendar.getTime()) + " 12:00:00";

            if (str.equals(time)) {
                j++;
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                DayDetails main2 = weatherRespon.getDayDetails().get(i);
                dayDetails.add(main2);
            }
        }
        return dayDetails;
    }

    /**
     * this function removes the date and leaves the time only in this format HH:MM
     */
    public static String removeDate(String time) {
        StringBuilder str = new StringBuilder(time);
        str.delete(0, 11);
        str.delete(4, 7);
        return str.toString();
    }

    /**
     * this function gets the week name
     */
    public static String getWeek(String date) throws ParseException {
        String[] namesOfDays = {"Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"};
        StringBuilder str = new StringBuilder(date);
        String croppedDate = str.substring(0, 11);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date d = dateFormat.parse(croppedDate);
        return namesOfDays[d.getDay()];
    }

    /**
     * this function gets the day and month formatted in this format ex. 3 Oct
     */
    public static String getDayAndMonth(String date) throws ParseException {
        String[] namesOfMonths = {"Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
        StringBuilder str = new StringBuilder(date);
        String croppedDate = str.substring(0, 11);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date d = dateFormat.parse(croppedDate);
        return d.getDate() + " " + namesOfMonths[d.getMonth()];
    }

    public static Retrofit startConnection(Retrofit retrofit, String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}