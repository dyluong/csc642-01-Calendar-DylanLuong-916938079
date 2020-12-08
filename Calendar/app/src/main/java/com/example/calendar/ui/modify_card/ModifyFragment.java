package com.example.calendar.ui.modify_card;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.calendar.Card;
import com.example.calendar.MainActivity;
import com.example.calendar.R;
import com.example.calendar.ui.home.HomeFragment;

import java.util.ArrayList;

public class ModifyFragment extends Fragment {

    private TextView title;
    private CheckBox checkBoxPlan,checkBoxHabit,checkBoxGoal;
    private DatePicker startDate, endDate;
    private TimePicker startTime, endTime;
    private EditText description;

    private ModifyViewModel modifyViewModel;
    private ArrayList<Card> currentCards;
    private Card currentCard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        modifyViewModel =
                ViewModelProviders.of(this).get(ModifyViewModel.class);

        View root = inflater.inflate(R.layout.fragment_modify, container, false);

        title = root.findViewById(R.id.modifycard_title_input);
        checkBoxPlan = root.findViewById(R.id.modifycard_type_btn_plan);
        checkBoxHabit = root.findViewById(R.id.modifycard_type_btn_habit);
        checkBoxGoal = root.findViewById(R.id.modifycard_type_btn_plan_goal);
        startDate = root.findViewById(R.id.modifycard_startDate);
        startTime = root.findViewById(R.id.modifycard_startTime);
        endDate = root.findViewById(R.id.modifycard_endDate);
        endTime = root.findViewById(R.id.modifycard_endTime);
        description = root.findViewById(R.id.modifycard_description_input);

        currentCard = ((MainActivity)getActivity()).getCurrentCard();
        modifyHelper(currentCard);
        currentCards = ((MainActivity)getActivity()).getCurrentCards();

        try {
            LinearLayout linearLayout = root.findViewById(R.id.modifycard_container);
            int colorCode = ((MainActivity)getActivity()).getColorTheme();
            linearLayout.setBackgroundColor(colorCode);

        }catch (Exception ex){}

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        view.findViewById(R.id.close_modifycard_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ModifyFragment.this).navigate(R.id.action_modifyFragment_to_navigation_home3);

            }
        });

        view.findViewById(R.id.modifycard_btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateCard();
                ((MainActivity)getActivity()).setCurrentCard(currentCard);
                NavHostFragment.findNavController(ModifyFragment.this).navigate(R.id.action_modifyFragment_to_navigation_home3);

            }
        });
    }

    public void print(){
        System.out.println("hello world");
    }

    public void modifyHelper(Card card){
        title.setText(card.getTitle().getText());
        checkBoxPlan.setChecked(card.getPlan().isChecked());
        checkBoxHabit.setChecked(card.getHabit().isChecked());
        checkBoxGoal.setChecked(card.getGoal().isChecked());
        startDate = card.getStartDate();
        startTime = card.getStartTime();
        endDate = card.getEndDate();
        endTime = card.getEndTime();
        description.setText(card.getDescription().getEditableText());
    }

    public void updateCard(){

        currentCard.set(title,checkBoxPlan,checkBoxHabit,checkBoxGoal,startDate,endDate,startTime,endTime,description);

    }
}