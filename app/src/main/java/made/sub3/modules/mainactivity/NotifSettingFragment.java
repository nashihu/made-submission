package made.sub3.modules.mainactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import made.sub3.AlarmReceiver;
import made.sub3.R;

public class NotifSettingFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    public NotifSettingFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getActivity() != null) {
            sharedPreferences = getActivity().getSharedPreferences(
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        }
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Switch reminder_seven = view.findViewById(R.id.switch_seven);
        Switch reminder_eight = view.findViewById(R.id.switch_eight);
        reminder_seven.setOnCheckedChangeListener(this);
        reminder_eight.setOnCheckedChangeListener(this);

        reminder_seven.setChecked(sharedPreferences.getBoolean(getString(R.string.reminder_7), false));
        reminder_eight.setChecked(sharedPreferences.getBoolean(getString(R.string.reminder_new_movie), false));


        super.onViewCreated(view, savedInstanceState);
    }

    SharedPreferences sharedPreferences;

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (compoundButton.getId() == R.id.switch_seven) {
            editor.putBoolean(getString(R.string.reminder_7), isChecked);
            if(isChecked) {
                setReminder("07:00","ayo buka lagi aplikasinya",AlarmReceiver.TYPE_REPEATING);
            } else {
                cancelReminder(AlarmReceiver.TYPE_REPEATING,AlarmReceiver.ID_REPEATING);
            }
        } else {
            editor.putBoolean(getString(R.string.reminder_new_movie), isChecked);
            if(isChecked) {
                setReminder("08:00","ada film baru!",AlarmReceiver.TYPE_NEW_MOVIE);
            } else {
                cancelReminder(AlarmReceiver.TYPE_NEW_MOVIE,AlarmReceiver.ID_NEW_MOVIE);
            }
        }
        editor.apply();

//
//        AlarmReceiver alarmReceiver = new AlarmReceiver();
//        Date currentDate = new Date(System.currentTimeMillis());
//        DateFormat df = new SimpleDateFormat("HH:mm");
//        String hour = df.format(currentDate);
//        int minute = Integer.valueOf(hour.split(":")[1]);
//        minute += 1;
//        hour = hour.split(":")[0];
//        String time = hour+":"+minute;
//        String message = "ada film baru!";
//        Log.e("MA","before: " + hour + ":" + minute);
//        alarmReceiver.setNewMovieAlarm(getContext(),AlarmReceiver.TYPE_NEW_MOVIE,time,message);

    }

    public void setReminder(String time, String message, String type) {
        AlarmReceiver alarmReceiver = new AlarmReceiver();
        Date currentDate = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("HH:mm");
        String hour = df.format(currentDate);
        int minute = Integer.valueOf(hour.split(":")[1]);
        minute += 1;
        hour = hour.split(":")[0];
        time = hour+":"+minute;
        alarmReceiver.setDailyAlarm(getContext(),type,
                time,
                message
        );
        Toast.makeText(getContext(), "alarm set to: "+time, Toast.LENGTH_SHORT).show();

    }

    public void cancelReminder(String type, int type_int) {
        AlarmReceiver alarmReceiver = new AlarmReceiver();
        alarmReceiver.cancelAlarm(getContext(),"test",type,type_int);
    }


}

