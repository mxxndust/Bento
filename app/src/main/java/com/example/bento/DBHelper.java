package com.example.bento;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "bentos";
    private static final int DB_VERSION = 1;

    DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE BENTO (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "INGREDIENTS TEXT, "
                + "IMAGE INTEGER);");
        insertData(db, "Детское бенто", "Рис, Сосиски", R.drawable.kidbento, "BENTO");
        insertData(db, "Бенто Панда", "Рис, Пельмени, Лотос", R.drawable.bentopanda, "BENTO");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertData(SQLiteDatabase db, String name,
                                    String ingredients, int resourceId, String table) {
        ContentValues dataValues = new ContentValues();
        dataValues.put("NAME", name);
        dataValues.put("INGREDIENTS", ingredients);
        dataValues.put("IMAGE", resourceId);
        db.insert(table, null, dataValues);
    }
}
