package com.example.bento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
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

        ed1 = (EditText) findViewById(R.id.ent_name);
        ed2 = (EditText) findViewById(R.id.ent_phone);
        btn_order = (Button) findViewById(R.id.button2);

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
            /*listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinBentos.setPrompt("Выберите Бенто!");

            spinBentos.setAdapter(
                    new NothingSelectedSpinnerAdapter(
                            listAdapter,
                            R.layout.contact_spinner_row_nothing_selected,
                            // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                            this));*/
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "База недоступна!", Toast.LENGTH_SHORT);
            toast.show();
        }

        Switch switch2 = (Switch) findViewById(R.id.switch1);
        /*if (switch2 != null) {
            switch2.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);*/
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            checked = isChecked;
        }
        });

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateSignInButtonState();
            }
        };

       /* public void updateSignInButtonState() {
            btn_order.setEnabled(ed1.getText().length() > 0 &&
                    ed2.getText().length() > 0);
        }*/

        /*ed_1 = (EditText) findViewById(R.id.ent_name);
        ed_2 = (EditText) findViewById(R.id.ent_phone);
        btn_ok = (Button) findViewById(R.id.button2);
        btn_ok.setEnabled(false);

        EditText[] edList = {ed_1, ed_2};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList, btn_ok);
        for (EditText editText : edList) editText.addTextChangedListener(textWatcher);*/
    }

    private void updateSignInButtonState() {
        btn_order.setEnabled(ed1.getText().length() > 0 &&
                ed2.getText().length() > 0);
        /*if(ed1.getText().toString().trim().isEmpty) || ed2.isEmpty())
        {
            btn_order.setEnabled(false);
        }*/
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

    /*public void showToast(View view) {
        Toast toast_order = Toast.makeText(getApplicationContext(),
                R.string.order_created, Toast.LENGTH_LONG);
        toast_order.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastContainer = (LinearLayout) toast_order.getView();
        ImageView catImageView = new ImageView(getApplicationContext());
        catImageView.setImageResource(R.drawable.check);
        toastContainer.addView(catImageView, 0);
        toast_order.show();
    }*/
}
