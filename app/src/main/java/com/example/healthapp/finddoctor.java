package com.example.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class finddoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_finddoctor);
       //card views event listeners
       //family physician
        CardView familyphysician = findViewById(R.id.family_physician);
        familyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(finddoctor.this,doctordetail.class);
                it.putExtra("title", "family physician");
                startActivity(it);
            }
        });
        // dentist
        CardView dentist = findViewById(R.id.dentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(finddoctor.this,doctordetail.class);
                it.putExtra("title", "Dentist");
                startActivity(it);
            }
        });
        //cardiologist
        CardView cardiologist = findViewById(R.id.cardiologist);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(finddoctor.this,doctordetail.class);
                it.putExtra("title", "Cardiologist");
                startActivity(it);
            }
        });

        // dietician
        CardView dietician = findViewById(R.id.dietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(finddoctor.this,doctordetail.class);
                it.putExtra("title", "Dietician");
                startActivity(it);
            }
        });

        // surgeon
        CardView surgeon = findViewById(R.id.surgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(finddoctor.this,doctordetail.class);
                it.putExtra("title", "Surgeon");
                startActivity(it);
            }
        });

        //the back event listener
        CardView back = findViewById(R.id.back_card);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(finddoctor.this, home.class));

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}