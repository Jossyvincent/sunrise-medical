package com.example.healthapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.healthapp.R;
import com.example.healthapp.models.Doctor;

import java.util.ArrayList;

public class DoctorAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Doctor> doctorList;

    public DoctorAdapter(Context context, ArrayList<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @Override
    public int getCount() {
        return doctorList.size();
    }

    @Override
    public Object getItem(int position) {
        return doctorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.doctor_list_item, parent, false);
        }

        Doctor doctor = doctorList.get(position);

        TextView nameTextView = convertView.findViewById(R.id.doctor_name);
        TextView specialtyTextView = convertView.findViewById(R.id.doctor_specialty);
        TextView contactTextView = convertView.findViewById(R.id.doctor_contact);

        nameTextView.setText(doctor.getName());
        specialtyTextView.setText(doctor.getSpecialty());
        contactTextView.setText(doctor.getPhone()); // or doctor.getEmail()

        return convertView;
    }
}
