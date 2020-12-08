package com.example.calendar.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.calendar.MainActivity;
import com.example.calendar.R;


public class HelpFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_help, container, false);
        try {
            LinearLayout linearLayout = root.findViewById(R.id.help_container);
            int colorCode = ((MainActivity) getActivity()).getColorTheme();
            linearLayout.setBackgroundColor(colorCode);

        } catch (Exception ex) {
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.close_help_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HelpFragment.this).navigate(R.id.action_helpFragment_to_navigation_settings);
            }
        });
        view.findViewById(R.id.submit_help_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HelpFragment.this).navigate(R.id.action_helpFragment_to_navigation_settings);

            }
        });
    }
}