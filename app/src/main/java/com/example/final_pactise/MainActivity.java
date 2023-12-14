package com.example.final_pactise;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText editDateIn;
    EditText editDateOut;
    EditText editCheckInTime;
    EditText editTextName;
    EditText editTextEmail;

    RadioButton radioButton100;
    RadioButton radioButton150;
    RadioButton radioButton200;

    private Calendar calendar;
    private SimpleDateFormat timeFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editDateIn = findViewById(R.id.editDateIn);
        editDateOut = findViewById(R.id.editDateOut);
        editCheckInTime = findViewById(R.id.editCheckInTime);
        radioButton100 = findViewById(R.id.radioButton100);
        radioButton150 = findViewById(R.id.radioButton150);
        radioButton200 = findViewById(R.id.radioButton200);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);



        calendar = Calendar.getInstance();
        timeFormat = new SimpleDateFormat("hh:mm a", Locale.US);

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        editDateIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        editDateIn.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        editDateOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        editDateOut.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        editCheckInTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                String time = timeFormat.format(calendar.getTime());
                                editCheckInTime.setText(time);
                            }
                        },
                        hour,
                        minute,
                        false
                );
                timePickerDialog.show();
            }
        });


    }

    // Method to handle "Check Out" button click
    // Method to handle "Check Out" button click
    public void onCheckOutButtonClick(View view) {
        // Get the selected data
        String selectedDateIn = editDateIn.getText().toString();
        String selectedDateOut = editDateOut.getText().toString();
        String selectedCheckInTime = editCheckInTime.getText().toString();
        String selectedRoomPrice = getSelectedRoomPrice(); // Implement this method
        int nightCount = calculateNightDifference(selectedDateIn, selectedDateOut);
        int dayCount = calculateDayDifference(selectedDateIn, selectedDateOut);
        int totalRoomPrice = calculateTotalRoomPrice(selectedRoomPrice, nightCount);

        // Get user details
        String userName = editTextName.getText().toString();
        String userEmail = editTextEmail.getText().toString();


        Log.d("MainActivity", "Date In: " + selectedDateIn);
        Log.d("MainActivity", "Date Out: " + selectedDateOut);
        Log.d("MainActivity", "Check In Time: " + selectedCheckInTime);
        Log.d("MainActivity", "Room Price: " + selectedRoomPrice);
        Log.d("MainActivity", "User Name: " + userName);
        Log.d("MainActivity", "User Email: " + userEmail);
        Log.d("MainActivity", "Night: " + nightCount);
        Log.d("MainActivity", "Day: " + dayCount);


        // Create an Intent to start the SecondActivity
        Intent intent = new Intent(this, MainActivity2.class);

        // Put the selected data and user details into the Intent
        intent.putExtra("DATE_IN", selectedDateIn);
        intent.putExtra("DATE_OUT", selectedDateOut);
        intent.putExtra("CHECK_IN_TIME", selectedCheckInTime);
        intent.putExtra("ROOM_PRICE", selectedRoomPrice);
        intent.putExtra("USER_NAME", userName);
        intent.putExtra("USER_EMAIL", userEmail);
        intent.putExtra("NIGHT_COUNT", nightCount);
        intent.putExtra("DAY_COUNT", dayCount);
        intent.putExtra("TOTAL_ROOM_PRICE", totalRoomPrice);

        // Start the SecondActivity
        startActivity(intent);

    }
    // Method to determine the selected room price
    private String getSelectedRoomPrice() {
        if (radioButton100.isChecked()) {
            return "$100";
        } else if (radioButton150.isChecked()) {
            return "$150";
        } else if (radioButton200.isChecked()) {
            return "$200";
        } else {
            return ""; // Handle the default case or add validation
        }
    }
    private int calculateNightDifference(String dateIn, String dateOut) {
        // Implement your logic to calculate the night difference
        // For simplicity, let's assume one night for each day
        return calculateDayDifference(dateIn, dateOut);
    }

    private int calculateDayDifference(String dateIn, String dateOut) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            Date checkInDate = sdf.parse(dateIn);
            Date checkOutDate = sdf.parse(dateOut);

            long diffInMillis = Math.abs(checkOutDate.getTime() - checkInDate.getTime());
            long diffDays = diffInMillis / (24 * 60 * 60 * 1000);

            return (int) diffDays;
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Handle the exception or return a default value
        }
    }

    private int calculateTotalRoomPrice(String selectedRoomPrice, int nightCount) {
        try {
            // Extract the numeric part of the selected room price
            int roomPricePerNight = Integer.parseInt(selectedRoomPrice.replaceAll("[^\\d.]", ""));

            // Calculate the total room price
            return roomPricePerNight * nightCount;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0; // Handle the exception or return a default value
        }
    }

}
