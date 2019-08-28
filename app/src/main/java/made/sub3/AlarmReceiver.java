package made.sub3;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class AlarmReceiver extends BroadcastReceiver {

    public static final String TYPE_NEW_MOVIE = "OneTimeAlarm";
    public static final String TYPE_REPEATING = "RepeatingAlarm";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_TYPE = "type";
    public static final String NEW_MOVIE = "ada film baru!";

    // Siapkan 2 id untuk 2 macam alarm, onetime dna repeating
    public static final int ID_NEW_MOVIE = 100;
    public static final int ID_REPEATING = 101;

    public AlarmReceiver() { }

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        boolean reminder_7_checked = sharedPreferences.getBoolean(context.getString(R.string.reminder_7),false);
        boolean reminder_new_movie = sharedPreferences.getBoolean(context.getString(R.string.reminder_new_movie),false);
        String type = intent.getStringExtra(EXTRA_TYPE);
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        String title = type.equalsIgnoreCase(TYPE_NEW_MOVIE) ? TYPE_NEW_MOVIE : TYPE_REPEATING;
        int notifId = type.equalsIgnoreCase(TYPE_NEW_MOVIE) ? ID_NEW_MOVIE : ID_REPEATING;
        if(notifId == ID_REPEATING && message.equals(NEW_MOVIE) && reminder_new_movie) {
            fetchTitles(context);
            return;
        } else if (reminder_7_checked) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_live_tv_black_24dp);
            showAlarmNotification(context,"Halo semuanyaa","Sudah cek film hari ini? ayo bukaa",notifId, bitmap);
        }


    }

    void fetchTitles(final Context context) {
        AsyncHttpClient client = new AsyncHttpClient();
        Date currentDate = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(currentDate);
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=705673c77930fde4116b81e373e3a9e7&primary_release_date.gte="+today+"&primary_release_date.lte="+today;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.backdrop_alita);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i< list.length();i++) {
                        JSONObject item = list.getJSONObject(i);
                        final ItemDetail itemDetail = new ItemDetail(item, "movie");
                        Log.e("AR","masukkk pak eko! index: "+i+" title: "+itemDetail.getDesc_or_title());
                        stringBuilder.append((i+1) + ". " +itemDetail.getDesc_or_title() + "\r\n");
                        String url = "https://image.tmdb.org/t/p/original" + itemDetail.getPoster_url_for_detail_activity();


                    }
                    showAlarmNotification(context,"Film baru hari ini!",stringBuilder.toString(),ID_REPEATING, bitmap);
                } catch (JSONException e) {
                    //
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }


    private void showToast(Context context, String title, String message) {
        Toast.makeText(context, title + " : " + message, Toast.LENGTH_LONG).show();
    }

    public boolean isDateInvalid(String date, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
            df.setLenient(false);
            df.parse(date);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }

    private String DATE_FORMAT = "yyyy-MM-dd";
    private String TIME_FORMAT = "HH:mm";

    public void setDailyAlarm(Context context, String type, String time, String message) {
        if (isDateInvalid(time, TIME_FORMAT)) return;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_TYPE, type);
        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }



    }

    public void cancelAlarm(Context context, String message, String type, int ID) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_TYPE, type);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID, intent, 0);
        alarmManager.cancel(pendingIntent);
    }

    public void setNewMovieAlarm(Context context, String type, String time, String message) {
        if (isDateInvalid(time, TIME_FORMAT)) return;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_TYPE, type);
        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_NEW_MOVIE, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        Toast.makeText(context, "Repeating alarm set up", Toast.LENGTH_SHORT).show();
    }

    private void showAlarmNotification(Context context, String title, String message, int notifId, Bitmap bitmap) {
        showToast(context, title, message);
        String CHANNEL_ID = "Channel_1";
        String CHANNEL_NAME = "AlarmManager channel";
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_local_movies_black_24dp)
                .setLargeIcon(bitmap)
                .setContentTitle(title)
                .setSubText("ayoo coba liat wkwkwk")
//                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
            builder.setChannelId(CHANNEL_ID);
            if (notificationManagerCompat != null) {
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }
        Notification notification = builder.build();
        if (notificationManagerCompat != null) {
            notificationManagerCompat.notify(notifId, notification);
        }
    }


}
