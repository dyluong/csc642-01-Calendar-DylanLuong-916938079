package com.example.calendar.ui.settings;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Space;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.calendar.MainActivity;
import com.example.calendar.R;
import com.example.calendar.ui.home.HomeFragment;

public class CustomizationFragment  extends Fragment {

    private CustomizationViewModel customizationViewModel;
    private EditText colorCode;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        customizationViewModel =
                ViewModelProviders.of(this).get(CustomizationViewModel.class);

        View root = inflater.inflate(R.layout.fragment_customization, container, false);
        colorCode = root.findViewById(R.id.settings_customization_theme_input);

        try {
            LinearLayout linearLayout = root.findViewById(R.id.customization_container);
            int colorCode = ((MainActivity)getActivity()).getColorTheme();
            linearLayout.setBackgroundColor(colorCode);

        }catch (Exception ex){}

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.close_customization_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CustomizationFragment.this).navigate(R.id.action_customizationFragment_to_navigation_settings);
            }
        });

        view.findViewById(R.id.settings_customization_btn_theme_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int code = 0;

                try {

                      code = Color.parseColor(colorCode.getText().toString());
                      ((MainActivity)getActivity()).setColorTheme(code);
                    NavHostFragment.findNavController(CustomizationFragment.this).navigate(R.id.action_customizationFragment_to_navigation_settings);


                }catch (Exception ex){
                    System.out.println(ex);

                } finally {

                }

            }
        });

        view.findViewById(R.id.settings_customization_btn_theme_default).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).setColorTheme(getResources().getColor(R.color.default_theme));
                NavHostFragment.findNavController(CustomizationFragment.this).navigate(R.id.action_customizationFragment_to_navigation_settings);

            }
        });
    }


}
