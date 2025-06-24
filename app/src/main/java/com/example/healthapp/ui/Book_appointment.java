package com.example.healthapp.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthapp.R;
import com.example.healthapp.database.Database;

import java.util.Calendar;
import java.util.Locale;

public class Book_appointment extends AppCompatActivity {

    TextView tv;
    EditText full_name,address, fee,phone_number,editTextDate,editTextTime ;
    Button register;

    SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
    Database db = new Database(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_appointment);
        // instantiating the widgets
        tv = findViewById(R.id.book_appt);
        full_name = findViewById(R.id.editTextfullname);
        address = findViewById(R.id.editTextaddress);
        fee = findViewById(R.id.editTextconsultant_fee);
        phone_number = findViewById(R.id.editTextphonenumber);
        register = findViewById(R.id.buttonRegister);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        //disabling editing
        for (EditText field : new EditText[]{full_name, address, fee, phone_number}) {
            field.setKeyListener(null);
        }

        //fetching the data from the previous activity
        Intent intent = getIntent();

        String name     = intent.getStringExtra("doctor_name");
        String specialty = intent.getStringExtra("specialty"); // optional
        String hospital = intent.getStringExtra("hospital");
        double feeValue = intent.getDoubleExtra("fee", 0.0);
        String phone    = intent.getStringExtra("phone");

        //setting the data to the widgets
        tv.setText(specialty);
        full_name.setText(name);
        address.setText(hospital);
        fee.setText(String.format(Locale.getDefault(), "%.2f", feeValue));
        phone_number.setText(phone);

        //adding the details to the database
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Collect data from the form
                String patientUsername = prefs.getString("username", "Guest");
                String doctorName   = full_name.getText().toString();
                String hospital     = address.getText().toString();
                double feeValue = Double.parseDouble(fee.getText().toString().replaceAll("[^\\d.]", ""));
                String phone        = phone_number.getText().toString();
                String date         = editTextDate.getText().toString();
                String time         = editTextTime.getText().toString();

                boolean success = db.insertAppointment(doctorName,hospital,feeValue,phone,date,time,patientUsername);
                if (success)  {
                    Toast.makeText(getApplicationContext(), "Appointment booked successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // or navigate elsewhere
                }  else {
                    Toast.makeText(getApplicationContext(), "Booking failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }


        });

        // Date picker logic
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get current date
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Create DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Note: monthOfYear is 0-based
                                String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                editTextDate.setText(selectedDate);
                            }
                        },
                        year, month, day
                );

                datePickerDialog.show();
            }
        });

        //time picker logic
        editTextTime.setOnClickListener(v -> {
            // Get current time as default
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    Book_appointment.this,
                    (view, hourOfDay, minute1) -> {
                        // Format time
                        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute1);
                        editTextTime.setText(formattedTime);
                    },
                    hour, minute, true // true = 24 hour format
            );

            timePickerDialog.show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}