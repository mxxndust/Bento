package com.example.bento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class OrderActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        SQLiteOpenHelper DBHelper = new DBHelper(this);
        Spinner spinBentos = (Spinner) findViewById(R.id.spinner);
        try {
            db = DBHelper.getReadableDatabase();
            cursor = db.query("BENTO",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);
            android.widget.SimpleCursorAdapter listAdapter = new android.widget.SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);
            spinBentos.setAdapter(listAdapter);
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "База недоступна!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
