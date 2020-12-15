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
        updateMyDatabase(db, 0, DB_VERSION);
        /*db.execSQL("CREATE TABLE BENTO (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "INGREDIENTS TEXT, "
                + "IMAGE INTEGER);");
        insertData(db, "Детское бенто", "Рис, Сосиски", R.drawable.kidbento2, "BENTO");
        insertData(db, "Бенто Панда", "Рис, Пельмени, Лотос", R.drawable.bentopanda, "BENTO");*/
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE BENTO (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "INGREDIENTS TEXT, "
                    + "IMAGE INTEGER);");
            insertData(db, "Детское бенто", "Рис, cосиски, яйца, кукуруза, нори", R.drawable.kidbento2, "BENTO");
            insertData(db, "Бенто Панда", "Рис, пельмени, лотос, горошек, капуста", R.drawable.bentopanda, "BENTO");
            insertData(db, "Бенто 100", "Рис, котлеты, морковь, перец, грибы", R.drawable.soln, "BENTO");
            insertData(db, "Бенто Мишки", "Рис, креветки, фасоль, орехи, сладкий картофель", R.drawable.mishki, "BENTO");
            insertData(db, "Бенто Пингвины", "Рис, помидор, брокколи, лотос, горох ", R.drawable.ping, "BENTO");
            insertData(db, "Бенто стандартное", "Рис, помидоры, сосиски, огурцы, кешью. миндаль, грейпфрут", R.drawable.bentostan, "BENTO");
        }
        if (oldVersion < 2) {

        }
        if (oldVersion < 3) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
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
