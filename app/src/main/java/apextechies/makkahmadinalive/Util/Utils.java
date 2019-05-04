package apextechies.makkahmadinalive.Util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static String GetDateTimeDifference(Date startDate, Date endDate){

        //milliseconds
        long different =  startDate.getTime() - endDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long monthInMilli = daysInMilli * 30;

        long elapsedMonths = different / monthInMilli;
        different = different % monthInMilli;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;
        long totalSecond = (elapsedSeconds + (60*elapsedMinutes) + (3600*elapsedHours) + (86400*elapsedDays) + (2592000*elapsedMonths));

        Log.i("elapsedMonths",""+elapsedMonths);
        Log.i("elapsedDays",""+elapsedDays);
        Log.i("elapsedHours",""+elapsedHours);
        Log.i("elapsedMinutes",""+elapsedMinutes);
        Log.i("elapsedSeconds",""+elapsedSeconds);
        if (totalSecond<60){
            return "now";
        }
        if ((totalSecond>=60) && (!(totalSecond>=120))){
            return elapsedMinutes+" min ago";
        }
        if ((totalSecond>=120) && (!(totalSecond>=3600))){
            return elapsedMinutes+" mins ago";
        }
        if ((totalSecond>=3600) && (!(totalSecond>=7200))){
            return elapsedHours+" hour ago";
        }
        if ((totalSecond>=7200) && (!(totalSecond>=86400))){
            return elapsedHours+" hours ago";
        }
        if ((totalSecond>=86400) && (totalSecond<172800)){
            return elapsedDays+" day ago";
        }
        if ((totalSecond>=172800) && (!(totalSecond>=2592000))){
            return elapsedDays+" days ago";
        }
        if ((totalSecond>=2592000) && (!(totalSecond>=5184000))){
            return elapsedMonths+" month ago";
        }
        if ((totalSecond>=5184000) && (!(totalSecond<31104000))){
            return elapsedMonths+" months ago";
        }
        else {
            return ""+startDate;
        }


    }

    public static String GetTodaysDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }

    public static Date getDateFromString(String dateStr) {
        Date date = null;
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = DATE_FORMAT.parse(dateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
