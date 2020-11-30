package com.example.bento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


public class OrderActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;
    private Spinner mSpinner;
    private boolean checked;

    EditText ed1, ed2;
    Button btn_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ed1 = findViewById(R.id.ent_name);
        ed2 = findViewById(R.id.ent_phone);
        btn_order = findViewById(R.id.button2);

        SQLiteOpenHelper DBHelper = new DBHelper(this);
        Spinner spinBentos = findViewById(R.id.spinner);
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

        Switch switch2 = findViewById(R.id.switch1);
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            checked = isChecked;
        }
        });

        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btn_order.setEnabled(ed1.getText().toString().trim().length() > 0
                        && ed2 .getText().toString().trim().length() > 0 );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ed2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btn_order.setEnabled(ed1.getText().toString().trim().length() > 0
                        && ed2 .getText().toString().trim().length() > 0 );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(OrderActivity.this, MainActivity.class);
        startActivity(intent);

        if (checked) {
            Toast toast_order = Toast.makeText(getApplicationContext(),
                    R.string.order_created, Toast.LENGTH_LONG);
            toast_order.setGravity(Gravity.CENTER, 0, 0);
            toast_order.show();
        }
        else {
            Toast toast_order = Toast.makeText(getApplicationContext(),
                    R.string.order_created2, Toast.LENGTH_LONG);
            toast_order.setGravity(Gravity.CENTER, 0, 0);
            toast_order.show();
        }
        //toast_order.setGravity(Gravity.CENTER, 0, 0);

        /*LinearLayout toastContainer = (LinearLayout) toast_order.getView();
        ImageView orderImageView = new ImageView(getApplicationContext());
        orderImageView.setImageResource(R.drawable.check);
        toastContainer.addView(orderImageView, 0);*/

        //toast_order.show();
    }

}
