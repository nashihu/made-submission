package made.sub3.mainactivity;

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

import made.sub3.R;

public class NotifSettingFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    public NotifSettingFragment() {

    }

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
        } else {
            editor.putBoolean(getString(R.string.reminder_new_movie), isChecked);
        }
        editor.apply();
    }

}

