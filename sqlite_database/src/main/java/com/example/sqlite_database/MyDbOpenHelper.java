package com.example.sqlite_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by clive on 3/3/14.
 * creates and upgrades database
 */
public class MyDbOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_CREATE = "create table " +
            MyDbContractConstants.MyConstants.DATABASE_TABLE + " ("
            + MyDbContractConstants.MyConstants.KEY_ID +
            " integer primary key autoincrement, " +
    MyDbContractConstants.MyConstants.NAME + " text not null, " +
    MyDbContractConstants.MyConstants.EMAIL + " text not null, " +
    MyDbContractConstants.MyConstants.PHONE + " text not null);";

    public MyDbOpenHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "
                + MyDbContractConstants.MyConstants.DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
