package com.example.athanapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.batoulapps.adhan.CalculationMethod;
import com.batoulapps.adhan.CalculationParameters;
import com.batoulapps.adhan.Coordinates;
import com.batoulapps.adhan.Madhab;
import com.batoulapps.adhan.Prayer;
import com.batoulapps.adhan.PrayerTimes;
import com.batoulapps.adhan.data.DateComponents;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {


    TextView curPrayer;
    TextView fajr;
    TextView dhuhr;
    TextView asr;
    TextView maghrib;
    TextView isha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Coordinates coordinates = new Coordinates(40.7259, -73.5143);
        DateComponents date = DateComponents.from(new Date());

        CalculationParameters params =
                CalculationMethod.NORTH_AMERICA.getParameters();
        params.madhab = Madhab.SHAFI;

        PrayerTimes pT = new PrayerTimes(coordinates, date, params);

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        formatter.format(pT.fajr);

        curPrayer = findViewById(R.id.curPrayer);
        Prayer ans = pT.currentPrayer();
        curPrayer.setText("Current Prayer: " + ans.toString());

        fajr = findViewById(R.id.fajr);
        fajr.setText("Fajr time: "+formatter.format(pT.fajr));

        dhuhr = findViewById(R.id.dhuhr);
        dhuhr.setText("Dhuhr time: "+formatter.format(pT.dhuhr));

        asr = findViewById(R.id.asr);
        asr.setText("Asr time: "+formatter.format(pT.asr));

        maghrib = findViewById(R.id.maghrib);
        maghrib.setText("Maghrib time: "+formatter.format(pT.maghrib));

        isha = findViewById(R.id.isha);
        isha.setText("Isha time: "+formatter.format(pT.isha));

        //https://github.com/batoulapps/adhan-java

    }
}