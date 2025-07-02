package com.example.healthapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthapp.R;
import com.example.healthapp.adapters.DoctorAdapter;
import com.example.healthapp.models.Doctor;
import com.example.healthapp.database.Database;

import java.util.ArrayList;

public class Doctor_detail extends AppCompatActivity {

    private ListView listView;
    private DoctorAdapter adapter;
    private ArrayList<Doctor> doctorList;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.doctordetail);

        TextView tv = findViewById(R.id.textView2labTest);
        Button btn = findViewById(R.id.labTestBack);
        listView = findViewById(R.id.listViewlabTests); //

        // Set title from previous activity
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        // Initialize the database
        dbHelper = new Database(this);
        doctorList = dbHelper.getDoctorsByCategory(title); // Get all doctors from DB
        adapter = new DoctorAdapter(this, doctorList); // Create adapter
        listView.setAdapter(adapter); // Attach adapter to ListView

        // Back button functionality
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(Doctor_detail.this, Find_doctor.class);
            startActivity(intent);
            finish();
        });

        // setting the ListView onClick Listener
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Doctor selectedDoctor = doctorList.get(position);

            Intent intent = new Intent(Doctor_detail.this, Book_appointment.class);
            intent.putExtra("doctor_name", selectedDoctor.getName());
            intent.putExtra("specialty", selectedDoctor.getSpecialty());
            intent.putExtra("hospital", selectedDoctor.getHospital());
            intent.putExtra("fee", selectedDoctor.getFee());
            intent.putExtra("phone", selectedDoctor.getPhone());

            startActivity(intent);
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
