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

public class Register extends AppCompatActivity {
    Button regBtn;
    EditText UserName, Password, CPassword, Email;
    TextView tv;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        Database dbHelper = new Database(this);

        UserName = findViewById(R.id.editTextusername);
        Password = findViewById(R.id.editTextRegPassword);
        Email = findViewById(R.id.editTextemail);
        CPassword = findViewById(R.id.editTextConfirmPassword);
        tv = findViewById(R.id.textViewReg);
        regBtn = findViewById(R.id.buttonRegister);
        checkBox = findViewById(R.id.showPassword);

        //register button event listener
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = UserName.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String confirmpassword = CPassword.getText().toString().trim();

                if (password.equals(confirmpassword)) {
                    boolean hasUpper = password.matches(".*[A-Z].*");
                    boolean hasLower = password.matches(".*[a-z].*");
                    boolean hasDigit = password.matches(".*\\d.*");
                    boolean hasSpecial = password.matches(".*[@#$%^&+=!].*");
                    boolean hasLength = password.length() >= 6;

                    if (hasUpper && hasLower && hasDigit && hasSpecial && hasLength)
                    {
                        boolean success = dbHelper.registerUser(username, email, password, Register.this);
                        if (success) {
                            Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                            finish();
                        }
                        // no need for else here because error toast is handled in the db method
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Password must have at least 6+ chars, uppercase, lowercase, number & special character",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
       // show password logic
        checkBox.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {
                //show password
                Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                CPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                //hide password
                Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                CPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        }));
        //text view event Listener
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}