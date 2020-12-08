package com.example.calendar.ui.home;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.example.calendar.Card;
import com.example.calendar.MainActivity;
import com.example.calendar.R;
import com.example.calendar.ui.create_card.CreateCardFragment;
import com.example.calendar.ui.modify_card.ModifyFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Calendar calendar;
    private TextView textView;
    private ArrayList<Card> currentCards;

    private TableLayout tableLayout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        calendar = Calendar.getInstance();

        textView = root.findViewById(R.id.home_currentdate);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                String date = new SimpleDateFormat("MM/dd", Locale.getDefault()).format(calendar.getTime());
                textView.setText(date);
            }
        });

        currentCards = ((MainActivity)getActivity()).getCurrentCards();
        tableLayout = root.findViewById(R.id.table);
        tableLayout.removeAllViews();
        tableLayout.setOrientation(LinearLayout.VERTICAL);
        try {
            getCards();
        }catch (Exception ex){
            System.out.println(ex);
        }
        try {
            LinearLayout linearLayout = root.findViewById(R.id.home_container);
            int colorCode = ((MainActivity)getActivity()).getColorTheme();
            linearLayout.setBackgroundColor(colorCode);

        }catch (Exception ex){ }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.home_previous_button).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                calendar.add(Calendar.DATE, -1);

                String date = new SimpleDateFormat("MM/dd").format(calendar.getTime());

                if (textView != null) textView.setText(date);

                else System.out.println("ERROR");
                tableLayout.removeAllViews();

                getCards();

            }
        });

        view.findViewById(R.id.home_next_button).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                calendar.add(Calendar.DATE, 1);

                String date = new SimpleDateFormat("MM/dd").format(calendar.getTime());

                if (textView != null) textView.setText(date);

                else System.out.println("ERROR");
                tableLayout.removeAllViews();
                getCards();

            }
        });

        view.findViewById(R.id.home_create_card_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_navigation_home_to_navigation_createCard);

            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addCardToTable(final Card card){

        final TextView title = card.getTitle();
        TimePicker startTime = card.getStartTime();
        TimePicker endTime = card.getEndTime();
        final DatePicker startDate = card.getStartDate();
        DatePicker endDate = card.getEndDate();
        TextView timeView = new TextView(getActivity());
        Button button = new Button(getContext());

        final LinearLayout row = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);



        row.setLayoutParams(layoutParams);
        row.setOrientation(LinearLayout.VERTICAL);
        TextView tv = new TextView(getActivity());
        tv.setText(title.getText().toString());
        tv.setTypeface(Typeface.DEFAULT_BOLD);
        row.addView(tv);


        final String time = "Time: "+ (startDate.getMonth()+1) +"/"+startDate.getDayOfMonth()+", "+getMyTime(startTime)+" - \n\t\t\t\t\t "+ (endDate.getMonth()+1) +"/"+endDate.getDayOfMonth()+", "+getMyTime(endTime);
        timeView.setText(time);
        row.addView(timeView);

        button.setText("-");
        button.setTextSize(15f);
        button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        button.setGravity(Gravity.RIGHT);
        button.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.card_button,null));

        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.width = 90;
        layout.height =90;
        layout.leftMargin = 550;
        layout.gravity = Gravity.BOTTOM;


        button.setLayoutParams(layout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableLayout.removeView(row);
                currentCards.remove(card);
            }
        });

        row.addView(button);

        if (card.getCardType() == "plan")
            row.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.plancard,null));
        else if (card.getCardType() == "habit")
            row.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.habitcard,null));
        else if (card.getCardType() == "goal")
            row.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.goalcard,null));
        else
            row.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.plancard,null));


        row.setClickable(true);
                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_navigation_home_to_modifyFragment2);
                        ((MainActivity)getActivity()).setCurrentCard(card);

            }
        });

        tableLayout.addView(row);

        Space space = new Space(getActivity());
        LinearLayout.LayoutParams spaceLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        spaceLayout.width = 50;
        spaceLayout.height = 50;

        space.setLayoutParams(spaceLayout);
        tableLayout.addView(space);


    }


    private ArrayList<Integer> getIndex(){
        ArrayList<Integer> tempCards = new ArrayList<>();
        DatePicker startDate,endDate;
        Integer month, day, year, e_month,e_day,e_year, c_month,c_day,c_year;

        for(int i = 0; i<currentCards.size();i++){
            startDate = currentCards.get(i).getStartDate();
            endDate = currentCards.get(i).getEndDate();
            month = startDate.getMonth();
            day = startDate.getDayOfMonth();
            year = startDate.getYear();

            e_month = endDate.getMonth();
            e_day = endDate.getDayOfMonth();
            e_year = endDate.getYear();

            c_month = calendar.get(calendar.MONTH);
            c_day = calendar.get(calendar.DAY_OF_MONTH);
            c_year = calendar.get(calendar.YEAR);

            //Checks for invalid dates, and filter
            if( e_day < day || e_month < month || e_year < year){
                continue;
            }

            //Checks if plan is on the day
            if (day.equals(c_day) && month.equals(c_month)  && year.equals(c_year)) {
                tempCards.add(i);
            }
            //Checks if plan is on the end date
            else if (e_day.equals(c_day) && e_month.equals(c_month)  && e_year.equals(c_year)) {
                tempCards.add(i);
            }

            //Checks if plan is reoccuring for couple days
            else if (day <= c_day &&  c_day < e_day){
                if( month <= c_month && c_month <= e_month){
                    if( year <= c_year && c_year <= e_year){
                        tempCards.add(i);
                    }
                }
            }
        }
        return  tempCards;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getCards(){
        ArrayList<Integer> indexList = getIndex();

        for(int i = 0; i < indexList.size(); i++){
            addCardToTable(currentCards.get(indexList.get(i)));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private String getMyTime(TimePicker t){

        String AM_PM ="" ;
        String minute="";
        if(t.getHour()< 12)  AM_PM = "am";

        else AM_PM = "pm";

        if(t.getMinute() == 0) minute = "00";

        if (t.getMinute()<10){
            minute = "0"+t.getMinute();
        }
        else
            minute = String.valueOf(t.getMinute());


//        return t.getHour() % 12 + ":"+minute+" "+AM_PM;
        return t.getHour() + ":"+t.getMinute() ;
    }


}