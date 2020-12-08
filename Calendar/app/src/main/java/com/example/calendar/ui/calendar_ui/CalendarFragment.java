package com.example.calendar.ui.calendar_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.calendar.MainActivity;
import com.example.calendar.R;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel.class);

        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

        try {
            LinearLayout linearLayout = root.findViewById(R.id.calendar_container);
            int colorCode = ((MainActivity)getActivity()).getColorTheme();
            linearLayout.setBackgroundColor(colorCode);


        }catch (Exception ex){ }

        return root;


    }

}