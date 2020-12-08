package com.example.calendar.ui.create_card;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.calendar.MainActivity;
import com.example.calendar.R;
import com.example.calendar.Card;
import com.example.calendar.ui.home.HomeFragment;


import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class CreateCardFragment extends Fragment {

    private CreateCardViewModel createCardViewModel;

    private TextView title;
    private CheckBox checkBoxPlan,checkBoxHabit,checkBoxGoal;
    private DatePicker startDate, endDate;
    private TimePicker startTime, endTime;
    private EditText description;
    private SimpleDateFormat dateFormat;
    private View myView;

   private LinearLayout cardTable;
   private ArrayList<Card> currentCards;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        createCardViewModel =
                ViewModelProviders.of(this).get(CreateCardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_createcard, container, false);

        myView = root;
        title = root.findViewById(R.id.createcard_title_input);
        checkBoxPlan = root.findViewById(R.id.createcard_type_btn_plan);
        checkBoxHabit = root.findViewById(R.id.createcard_type_btn_habit);
        checkBoxGoal = root.findViewById(R.id.createcard_type_btn_plan_goal);
        startDate = root.findViewById(R.id.createcard_startDate);
        startTime = root.findViewById(R.id.createcard_startTime);
        endDate = root.findViewById(R.id.createcard_endDate);
        endTime = root.findViewById(R.id.createcard_endTime);
        description = root.findViewById(R.id.createcard_description_input);


        currentCards = ((MainActivity)getActivity()).getCurrentCards();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            LinearLayout linearLayout = root.findViewById(R.id.createcard_container);
            int colorCode = ((MainActivity)getActivity()).getColorTheme();
            linearLayout.setBackgroundColor(colorCode);

        }catch (Exception ex){}

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.close_createcard_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CreateCardFragment.this).navigate(R.id.action_navigation_createCard_to_navigation_home);
            }
        });

        view.findViewById(R.id.createcard_btn_create).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Card tempCard = createCard();
                ((MainActivity) getActivity()).addCurrentCards(tempCard);

                NavHostFragment.findNavController(CreateCardFragment.this).navigate(R.id.action_navigation_createCard_to_navigation_home);
            }
        });
    }


    public Card createCard(){
        Card card = new Card();
        card.set(title,checkBoxPlan,checkBoxHabit,checkBoxGoal,startDate,endDate,startTime,endTime,description);
        System.out.println(card.toString());
        return card;
    }

    public void modifyHelper(Card card){
        title = card.getTitle();
        checkBoxPlan = card.getPlan();
        checkBoxHabit = card.getHabit();
        checkBoxGoal = card.getGoal();
        startDate = card.getStartDate();
        startTime = card.getStartTime();
        endDate = card.getEndDate();
        endTime = card.getEndTime();
        description = card.getDescription();
    }


}