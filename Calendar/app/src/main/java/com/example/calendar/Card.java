package com.example.calendar;

import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.calendar.R;

public class Card {

    private TextView title;
    private EditText description;
    private CheckBox plan, habit, goal;
    private DatePicker startDate, endDate;
    private TimePicker startTime, endTime;


    public void set(TextView title, CheckBox plan, CheckBox habit, CheckBox goal, DatePicker startDate, DatePicker endDate, TimePicker startTime, TimePicker endTime, EditText description){
        this.title = title;
        this.plan = plan;
        this.habit = habit;
        this.goal = goal;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public String getCardType(){
        if(plan.isChecked()) return "plan";

        else if (habit.isChecked()) return "habit";

        else if (goal.isChecked()) return "goal";

        return "plan";
    }

    public TextView getTitle() {
        return title;
    }

    public CheckBox getPlan() {
        return plan;
    }

    public CheckBox getHabit() {
        return habit;
    }

    public CheckBox getGoal() {
        return goal;
    }

    public DatePicker getStartDate() {
        return startDate;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public TimePicker getStartTime() {
        return startTime;
    }

    public TimePicker getEndTime() {
        return endTime;
    }

    public EditText getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Card{" +
                "title=" + title.getText() +
                ", description=" + description.getText()+
                ", plan=" + plan.isChecked() +
                ", habit=" + habit.isChecked() +
                ", goal=" + goal.isChecked() +
                ", startDate=" + startDate.toString() +
                ", endDate=" + endDate.toString() +
                ", startTime=" + startTime.toString() +
                ", endTime=" + endTime.toString() +
                '}';
    }


}
