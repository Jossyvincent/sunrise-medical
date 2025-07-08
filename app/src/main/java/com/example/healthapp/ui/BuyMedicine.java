package com.example.healthapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import com.example.healthapp.R;
import com.example.healthapp.models.Cart;
import com.example.healthapp.models.Medicine;

public class BuyMedicine extends AppCompatActivity {

    ArrayList<Medicine> medicineList = new ArrayList<>();
    Button viewCart;
    ListView listview;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buymedicine);

        listview = findViewById(R.id.buymedicine_Listview);
        viewCart = findViewById(R.id.buymedicine_btn);
        textview = findViewById(R.id.buymedicine_Textview);

        //sample medicine
        medicineList.add(new Medicine("Paracetamol",50));
        medicineList.add(new Medicine("Brufen",50));
        medicineList.add(new Medicine("P-ALAXIN",300));
        medicineList.add(new Medicine("Tramadol",100));
        medicineList.add(new Medicine("Amoxicilin",20));
        medicineList.add(new Medicine("Septrin",20));

        ArrayAdapter<Medicine> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,medicineList);
        listview.setAdapter(adapter);

        //Add to cart
        listview.setOnItemClickListener((adapterView, view, i, l) -> {
            Medicine selected = medicineList.get(i);
            Cart.cartItems.add(selected);
            Toast.makeText(this,selected.getName() + " added to cart ",Toast.LENGTH_SHORT).show();
        });

        viewCart.setOnClickListener(v ->
        {
            Intent intent = new Intent(BuyMedicine.this, CartActivity.class);
            startActivity(intent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}