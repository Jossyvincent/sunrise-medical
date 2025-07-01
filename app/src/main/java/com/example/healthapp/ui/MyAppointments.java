package com.example.healthapp.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthapp.R;
import com.example.healthapp.database.Database;

import java.util.ArrayList;

public class MyAppointments extends AppCompatActivity {

    ListView listView;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_appointments);

        listView = findViewById(R.id.listViewAppointments);
        db = new Database(this);

        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        String username = prefs.getString("username", null);

        if (username != null) {
            ArrayList<String> appointmentList = db.getAppointmentsByUsername(username);
            if (appointmentList.isEmpty()) {
                Toast.makeText(this, "No appointments found.", Toast.LENGTH_SHORT).show();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appointmentList);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Error: No logged-in user.", Toast.LENGTH_LONG).show();
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}