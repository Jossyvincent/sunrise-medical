package com.example.healthapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.widget.Toast;

import com.example.healthapp.models.Doctor;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    //Database constants
    private static final String DATABASE_NAME = "sunrise.db";
    private static final int DATABASE_VERSION = 1;
    //user table columns
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    //columns for doctors table
    private static final String TABLE_DOCTORS = "doctors";
    private static final String COLUMN_DOCTOR_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SPECIALTY = "specialty";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_DOCTOR_EMAIL = "email";
    private static final String COLUMN_HOSPITAL = "hospital";
    private static final String COLUMN_FEE = "fee";


    // The constructor
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create users table
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);
        // create doctors table
        String CREATE_DOCTORS_TABLE = "CREATE TABLE " + TABLE_DOCTORS + " (" +
                COLUMN_DOCTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_SPECIALTY + " TEXT, " +
                COLUMN_HOSPITAL + " TEXT, " +
                COLUMN_FEE + " REAL, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_DOCTOR_EMAIL + " TEXT)";
        db.execSQL(CREATE_DOCTORS_TABLE);

        populateDoctorTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTORS);
        onCreate(db);

    }
    public boolean registerUser(String username, String email, String password, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        try {
            long result = db.insertOrThrow(TABLE_USERS, null, values);
            db.close();
            return result != -1;
        } catch (SQLiteConstraintException e) {
            db.close();
            Toast.makeText(context, "Email already in use", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    // to add data to the doctors table
    private void populateDoctorTable(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, "Emmanuel Okoda");
        values.put(COLUMN_SPECIALTY, "FAMILY PHYSICIAN");
        values.put(COLUMN_PHONE, "555-678");
        values.put(COLUMN_FEE, 1000 );
        values.put(COLUMN_HOSPITAL, "Sunrise medical clinic");
        values.put(COLUMN_DOCTOR_EMAIL, "emmanuel@gmail.com");
        db.insert(TABLE_DOCTORS,null, values);

        values.put(COLUMN_NAME, "Innocent Leaky");
        values.put(COLUMN_SPECIALTY, "DIETITIAN");
        values.put(COLUMN_PHONE, "553-658");
        values.put(COLUMN_FEE, 1500 );
        values.put(COLUMN_HOSPITAL, "Hawai-Kisumu");
        values.put(COLUMN_DOCTOR_EMAIL, "leaky@gmail.com");
        db.insert(TABLE_DOCTORS,null, values);

        values.put(COLUMN_NAME, "Arnold Favour");
        values.put(COLUMN_SPECIALTY, "DENTIST");
        values.put(COLUMN_PHONE, "555-000");
        values.put(COLUMN_FEE, 2000 );
        values.put(COLUMN_HOSPITAL, "Bliss Medical Centre");
        values.put(COLUMN_DOCTOR_EMAIL, "arnold@gmail.com");
        db.insert(TABLE_DOCTORS,null, values);

        values.put(COLUMN_NAME, "Balozi Eugene");
        values.put(COLUMN_SPECIALTY, "SURGEON");
        values.put(COLUMN_PHONE, "555-890");
        values.put(COLUMN_FEE, 1000 );
        values.put(COLUMN_HOSPITAL, "Avenue-Nairobi");
        values.put(COLUMN_DOCTOR_EMAIL, "balozi@gmail.com");
        db.insert(TABLE_DOCTORS,null, values);

        values.put(COLUMN_NAME, "Vincent Jossy");
        values.put(COLUMN_SPECIALTY, "CARDIOLOGIST");
        values.put(COLUMN_PHONE, "555-678");
        values.put(COLUMN_FEE, 2500 );
        values.put(COLUMN_HOSPITAL, "Doctors plaza");
        values.put(COLUMN_DOCTOR_EMAIL, "jossy@gmail.com");
        db.insert(TABLE_DOCTORS,null, values);
    }

    //get ALL doctors method
    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> doctorList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DOCTORS, null);

        if (cursor.moveToFirst()) {
            do {
                Doctor doctor = new Doctor();

                doctor.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                doctor.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                doctor.setSpecialty(cursor.getString(cursor.getColumnIndexOrThrow("specialty")));
                doctor.setPhone(cursor.getString(cursor.getColumnIndexOrThrow("phone")));
                doctor.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
                doctor.setFee(cursor.getDouble(cursor.getColumnIndexOrThrow("fee")));
                doctor.setHospital(cursor.getString(cursor.getColumnIndexOrThrow("hospital")));

                doctorList.add(doctor);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return doctorList;
    }
    // methods to get doctors by category
    public ArrayList<Doctor> getDoctorsByCategory(String category) {
        ArrayList<Doctor> doctorList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM doctors WHERE specialty = ?", new String[]{category});
        if (cursor.moveToFirst()) {
            do {
                Doctor doctor = new Doctor();
                doctor.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                doctor.setSpecialty(cursor.getString(cursor.getColumnIndexOrThrow("specialty")));
                doctor.setHospital(cursor.getString(cursor.getColumnIndexOrThrow("hospital")));
                doctor.setPhone(cursor.getString(cursor.getColumnIndexOrThrow("phone")));
                doctor.setFee(cursor.getDouble(cursor.getColumnIndexOrThrow("fee")));
                doctorList.add(doctor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return doctorList;
    }


    public boolean checkUser(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ? AND "
                + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return userExists;


    }
}
