package com.lazeebear.parkhere;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.lazeebear.parkhere.DAOs.ReturnedObjects.SpotDAO;
import com.lazeebear.parkhere.ServerConnector.ServerConnector;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

public class Search extends AppCompatActivity {
    private DatePickerDialog datePicker_lower, datePicker_upper;
    private TimePickerDialog timePicker_lower, timePicker_upper;
    private Button date_button_lower, date_button_upper, time_button_lower, time_button_upper;
    private Calendar c;
    private int year, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getCurrentTime();
        createDatePickers();
        createTimePickers();

        Button searchButton = (Button) findViewById(R.id.search_submit_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                search();
            }
        });
    }

    private void getCurrentTime(){
        // Use the current date as the default date in the picker
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Use the current date as the default date in the picker
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
    }

    private void createDatePickers(){
        date_button_lower = (Button) findViewById(R.id.date_button_lower_search);
        date_button_upper = (Button) findViewById(R.id.date_button_upper_search);
        //===Search by date: DatePicker===//

        //create the listener for the DatePickerDialog
        //first the lower
        DatePickerDialog.OnDateSetListener onDateSetHandlerLower = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // Update the TextView with the date chosen by the user
                date_button_lower.setText(formatDate(year, month, day));
            }
        };
        datePicker_lower = new DatePickerDialog(this, onDateSetHandlerLower, year, month, day);
        date_button_lower.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {showDatePickerLower();}
        });

        //now upper
        DatePickerDialog.OnDateSetListener onDateSetHandlerUpper = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // Update the TextView with the date chosen by the user
                date_button_upper.setText(formatDate(year,month,day));
            }
        };
        datePicker_upper = new DatePickerDialog(this, onDateSetHandlerUpper, year, month, day);
        date_button_upper.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {showDatePickerUpper();}
        });
    }

    private String formatDate(int year, int month, int day){
        return year + "-" + month + "-" + day;
    }

    //Date Picker
    private void showDatePickerLower(){
        datePicker_lower.show();
    }
    private void showDatePickerUpper(){ datePicker_upper.show();}


    //
    //time picker
    private void createTimePickers() {
        //===Search by date: DatePicker===//
        time_button_lower = (Button) findViewById(R.id.time_button_lower_search);
        time_button_upper = (Button) findViewById(R.id.time_button_upper_search);
        //create the listener for the DatePickerDialog
        TimePickerDialog.OnTimeSetListener onTimeSetHandlerLower = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                // Update the TextView with the date chosen by the user
                time_button_lower.setText(formatTime(hour, minute));
            }
        };
        timePicker_lower = new TimePickerDialog(this, onTimeSetHandlerLower, hour, minute,
                DateFormat.is24HourFormat(this));

        time_button_lower.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {showTimePickerLower(view);}
        });

        TimePickerDialog.OnTimeSetListener onTimeSetHandlerUpper = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                // Update the TextView with the date chosen by the user
                time_button_upper.setText(formatTime(hour, minute));
            }
        };
        timePicker_upper = new TimePickerDialog(this, onTimeSetHandlerUpper, hour, minute,
                DateFormat.is24HourFormat(this));

        time_button_upper.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {showTimePickerUpper(view);}
        });
    }

    private String formatTime(int hour, int minute){
        return hour + ":" + minute;
    }

    //Time Picker
    private void showTimePickerLower(View view){
        timePicker_lower.show();
    }
    private void showTimePickerUpper(View view){
        timePicker_upper.show();
    }

    private void search(){
        TextView address = (TextView) findViewById(R.id.address);
        String addressString = address.getText().toString();
        //String lowerDateTime = getDateTime(lowerInt);
        //String upperDateTime = getDateTime(upperInt);
        /*
        List<SpotDAO> spots = null;
        try {
            spots = ServerConnector.searchSpot(addressString);
        } catch (Exception e) {

        }
        Intent intent = new Intent(this, SpotListActivity.class);
        for (int i=0; i< spots.size(); i++) {
            intent.putExtra("address"+i, spots.get(i).getAddress() + ":" + spots.get(i).getId());
        }*/
        Intent intent = new Intent(this, SpotListActivity.class);
        intent.putExtra("address0",addressString+":"+"0");
        startActivity(intent);
    }

    //lower = 1
    //upper = 2
    private String formatDateTime(String date, String time){
        return date + " " + time + ":00";
    }

}
