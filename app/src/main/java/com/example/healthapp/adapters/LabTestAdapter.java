package com.example.healthapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.healthapp.R;
import com.example.healthapp.models.LabTest;

import java.util.List;

public class LabTestAdapter extends ArrayAdapter<LabTest> {
    private Context mContext;
    private List<LabTest> labTestList;

    public LabTestAdapter(Context context, List<LabTest> tests) {
        super(context, 0, tests);
        this.mContext = context;
        this.labTestList = tests;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LabTest test = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lab_test_item, parent, false);
        }

        TextView testName = convertView.findViewById(R.id.lab_test_name);
        TextView testPrice = convertView.findViewById(R.id.lab_test_price);
        CheckBox checkBox = convertView.findViewById(R.id.lab_test_checkbox);

        testName.setText(test.getName());
        testPrice.setText("Ksh " + test.getPrice());
        checkBox.setChecked(test.isSelected());

        // Handle checkbox toggle
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> test.setSelected(isChecked));

        return convertView;
    }
}
