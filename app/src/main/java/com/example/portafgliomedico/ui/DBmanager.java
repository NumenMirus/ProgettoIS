package com.example.portafgliomedico.ui;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.portafgliomedico.ui.memos.MemosFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBmanager extends SQLiteOpenHelper {
    public DBmanager(Context context){
        super(context, "vault.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE memo (id integer PRIMARY KEY AUTOINCREMENT,text TEXT,insert_date TEXT,due_date TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists memo;");
    }

    public Boolean insertMemo(String text, String due_date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        Log.d(TAG, "insertMemo: Inserting 1");
        
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("text", text);
        contentValues.put("due_date", due_date);
        Log.d(TAG, "insertMemo: "+ dateFormat.format(date));
        contentValues.put("insert_date", dateFormat.format(date));

        long result = db.insert("memo", null, contentValues);
        if(result == -1){
            Log.d(TAG, "insertMemo: False");
            return false;
        }else{
            Log.d(TAG, "insertMemo: True - "+result);
            return true;
        }
    }

    public Cursor getMemo(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from memo;", null);
        Log.d(TAG, "getMemo: "+cursor.toString());
        return cursor;
    }

    public Boolean deleteMemo(){
        Log.d(TAG, "deleteMemo: Deleting 1");

        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete("memo", null, null);
        if(result == -1){
            Log.d(TAG, "deleteMemo: False");
            return false;
        }else{
            Log.d(TAG, "deleteMemo: True - "+result);
            return true;
        }
    }
}

