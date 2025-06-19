package com.example.healthapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

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
    private static final String COLUMN_SPECIALITY = "speciality";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_DOCTOR_EMAIL = "email";


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
                COLUMN_SPECIALITY + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_DOCTOR_EMAIL + " TEXT)";
        db.execSQL(CREATE_DOCTORS_TABLE);
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
