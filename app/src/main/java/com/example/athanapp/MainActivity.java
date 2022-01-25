package com.example.athanapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.batoulapps.adhan.CalculationMethod;
import com.batoulapps.adhan.CalculationParameters;
import com.batoulapps.adhan.Coordinates;
import com.batoulapps.adhan.Madhab;
import com.batoulapps.adhan.PrayerTimes;
import com.batoulapps.adhan.data.DateComponents;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Coordinates coordinates = new Coordinates(40.7259, -73.5143);
        DateComponents date = DateComponents.from(new Date());

        CalculationParameters params =
                CalculationMethod.NORTH_AMERICA.getParameters();
        params.madhab = Madhab.SHAFI;
        params.adjustments.fajr = 2;

        PrayerTimes pT = new PrayerTimes(coordinates, date, params);

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        formatter.format(pT.fajr);

        Log.d(TAG, "onCreate: " + pT.currentPrayer());
        //https://github.com/batoulapps/adhan-java

    }
}