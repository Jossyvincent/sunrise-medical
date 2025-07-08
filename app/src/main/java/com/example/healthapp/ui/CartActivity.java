package com.example.healthapp.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthapp.R;
import com.example.healthapp.models.Cart;
import com.example.healthapp.models.Medicine;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    Button ConfirmOrderBtn;
    ListView CartListView;
    TextView TotalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        ConfirmOrderBtn = findViewById(R.id.confirmOrderBtn);
        CartListView = findViewById(R.id.cartListView);
        TotalTextView = findViewById(R.id.TotalTextview);

        ArrayList<Medicine> cartItems = Cart.cartItems;
        ArrayAdapter<Medicine> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cartItems);

        CartListView.setAdapter(adapter);

        double total = 0;
        for (Medicine m : cartItems) {
            total += m.getPrice();
        }
        TotalTextView.setText("Total: ksh " + total );

        ConfirmOrderBtn.setOnClickListener(v ->{
            Toast.makeText(this, "Order placed", Toast.LENGTH_SHORT).show();
            Cart.cartItems.clear();
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}