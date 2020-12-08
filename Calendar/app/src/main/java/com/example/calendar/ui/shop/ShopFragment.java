package com.example.calendar.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.calendar.MainActivity;
import com.example.calendar.R;
import com.example.calendar.ui.settings.SettingsViewModel;

public class ShopFragment extends Fragment {

    private ShopViewModel shopViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        shopViewModel =
                ViewModelProviders.of(this).get(ShopViewModel.class);

        View root = inflater.inflate(R.layout.fragment_shop, container, false);
        try {
            LinearLayout linearLayout = root.findViewById(R.id.shop_container);
            int colorCode = ((MainActivity)getActivity()).getColorTheme();
            linearLayout.setBackgroundColor(colorCode);

        }catch (Exception ex){}

        return root;
    }
}