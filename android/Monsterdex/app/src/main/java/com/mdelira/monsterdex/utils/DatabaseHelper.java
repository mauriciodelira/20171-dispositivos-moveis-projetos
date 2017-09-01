package com.mdelira.monsterdex.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    private final Context mContext;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "monsterdex.db";
    private static final String TB_POKEMON = "pokemon";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // cria o banco com as colunas correspondentes ao JSON recebido
        db.execSQL("CREATE TABLE IF NOT EXISTS pokemon(" +
                "number INTEGER PRIMARY KEY NOT NULL," +
                "name VARCHAR" +
                " VARCHAR" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TB_POKEMON);
        this.onCreate(db);
    }
}
