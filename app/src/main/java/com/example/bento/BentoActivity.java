package com.example.bento;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BentoActivity extends AppCompatActivity {

    public static final String EXTRA_BENTOID = "bentoId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bento);

        int bentoId = (Integer)getIntent().getExtras().get(EXTRA_BENTOID);

        SQLiteOpenHelper DBHelper = new DBHelper(this);
        try {
            SQLiteDatabase db = DBHelper.getReadableDatabase();
            Cursor cursor = db.query ("BENTO",
                    new String[] {"NAME", "INGREDIENTS", "IMAGE"},
                    "_id = ?",
                    new String[] {Integer.toString(bentoId)},
                    null, null,null);

            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String ingredientsText = cursor.getString(1);
                int photoId = cursor.getInt(2);

                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);

                TextView description = (TextView)findViewById(R.id.ingredients);
                description.setText(ingredientsText);

                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
            }
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "База недоступна!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
