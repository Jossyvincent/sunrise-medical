package com.example.healthapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.example.healthapp.adapters.LabTestAdapter;
import com.example.healthapp.models.LabTest;

import java.util.ArrayList;

public class Lab_Test extends AppCompatActivity {

    private ListView listView;
    private Button btnAddToCart, btnBack;
    private ArrayList<LabTest> labTests;
    private LabTestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        listView = findViewById(R.id.listViewlabTests);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBack = findViewById(R.id.labTestBack);

        // ✅ Hardcoded lab test data
        labTests = new ArrayList<>();
        labTests.add(new LabTest("Blood Sugar", 300));
        labTests.add(new LabTest("Malaria Test", 250));
        labTests.add(new LabTest("Liver Function Test", 700));
        labTests.add(new LabTest("Kidney Test", 550));
        labTests.add(new LabTest("COVID-19 PCR", 1000));

        // ✅ Set up adapter and bind to ListView
        adapter = new LabTestAdapter(this, labTests);
        listView.setAdapter(adapter);

        // ✅ Handle 'Add to CartActivity' button
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<LabTest> selectedTests = new ArrayList<>();
                for (LabTest test : labTests) {
                    if (test.isSelected()) {
                        selectedTests.add(test);
                    }
                }

                if (selectedTests.isEmpty()) {
                    Toast.makeText(Lab_Test.this, "No tests selected!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Lab_Test.this, selectedTests.size() + " test(s) added to cart.", Toast.LENGTH_SHORT).show();
                    // 🔄 You can pass selectedTests to another activity later if needed
                }
            }
        });

        // ✅ Handle 'Back' button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close this activity
            }
        });
    }
}
