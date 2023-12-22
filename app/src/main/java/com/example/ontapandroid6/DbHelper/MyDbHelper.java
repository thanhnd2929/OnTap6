package com.example.ontapandroid6.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    static String DB_NAME = "quanli_dv";
    static int  VERSION = 1;
    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String dongvat = "CREATE TABLE dongvat (\n" +
                "    ma      INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                    NOT NULL,\n" +
                "    ten     TEXT    NOT NULL,\n" +
                "    loai    TEXT    NOT NULL,\n" +
                "    soluong INTEGER NOT NULL\n" +
                ");\n";
        db.execSQL(dongvat);

        String insert = "Insert into dongvat(ten, loai, soluong)\n" +
                "values('cho', 'an thit', 10), ('meo', 'an cut', 5);";
        db.execSQL(insert);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
