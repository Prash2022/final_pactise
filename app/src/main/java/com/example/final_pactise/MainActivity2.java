package com.example.final_pactise;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    // Declare TextViews or other UI elements to display the retrieved data
    //private TextView textViewDateIn;
    //private TextView textViewDateOut;
    private TextView textViewCheckInTime;
    //private TextView textViewRoomPrice;
    private TextView textViewUserName;
    private TextView textViewUserEmail;
    //private TextView textViewDayCount;
    private TextView textViewNightCount;
    private TextView textViewTotalRoomPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize TextViews or other UI elements
        //textViewDateIn = findViewById(R.id.textViewDateIn);
        //textViewDateOut = findViewById(R.id.textViewDateOut);
        textViewCheckInTime = findViewById(R.id.textViewCheckInTime);
        //textViewRoomPrice = findViewById(R.id.textViewRoomPrice);
        textViewUserName = findViewById(R.id.textViewUserName);
        textViewUserEmail = findViewById(R.id.textViewUserEmail);
        textViewNightCount = findViewById(R.id.textViewNightCount);
        //textViewDayCount = findViewById(R.id.textViewDayCount);
        textViewTotalRoomPrice = findViewById(R.id.textViewTotalRoomPrice);


        // Retrieve data from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            String dateIn = intent.getStringExtra("DATE_IN");
            String dateOut = intent.getStringExtra("DATE_OUT");
            String checkInTime = intent.getStringExtra("CHECK_IN_TIME");
            String roomPrice = intent.getStringExtra("ROOM_PRICE");
            String userName = intent.getStringExtra("USER_NAME");
            String userEmail = intent.getStringExtra("USER_EMAIL");
            int nightCount = intent.getIntExtra("NIGHT_COUNT", 0);
            int dayCount = intent.getIntExtra("DAY_COUNT", 0);
            int totalRoomPrice = intent.getIntExtra("TOTAL_ROOM_PRICE", 0);

            // Display the retrieved data in the TextViews or other UI elements
            //textViewDateIn.setText("Date In: " + dateIn);
            //textViewDateOut.setText("Date Out: " + dateOut);
            //textViewCheckInTime.setText("Check In Time: " + checkInTime);
            //textViewRoomPrice.setText("Room Price: " + roomPrice);
            textViewUserName.setText("User Name: " + userName);
            textViewUserEmail.setText("User Email: " + userEmail);
            textViewNightCount.setText("Night Count: " + nightCount);
            //textViewDayCount.setText("Day Count: " + dayCount);
            textViewTotalRoomPrice.setText("Total Room Price: $" + totalRoomPrice);
            textViewCheckInTime.setText("Check In Time: " + checkInTime);

        }
    }
}
