package com.example.healthapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class login extends AppCompatActivity {
    //declaring widgets
    EditText Email,Password ;
    Button LoginBtn;
    TextView NewUser;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        Database dbHelper = new Database(this);
        //instantiating the widgets
        Email = findViewById(R.id.editTextemail);
        Password = findViewById(R.id.editTextRegPassword);
        LoginBtn = findViewById(R.id.buttonRegister);
        NewUser = findViewById(R.id.textViewNewuser);
        checkBox = findViewById(R.id.showPasswordCheckBox);


        //setting the event listener;
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                if(email.trim().isEmpty() || password.trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username and password fields cant be blank", Toast.LENGTH_SHORT).show();
                } else{
                    boolean valid = dbHelper.checkUser(email,password);
                    if (valid) {
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, home.class);
                        startActivity(intent);
                        finish();
                    }  else {
                        Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        // show password logic
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                //show password
                Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                //hide password
                Password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            // Move the cursor to the end of the text
            Password.setSelection(Password.length());
        });
        //textView event listener
        NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, Register.class));

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}