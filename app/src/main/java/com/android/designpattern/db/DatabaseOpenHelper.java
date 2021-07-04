package com.android.designpattern.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public final static String DATABASE_PATH = "/data/data/com.android.DesignPattern/databases/";

    private static final String DATABASE_NAME = "jl-demo-db.db";
    private final Context myContext;
    private SQLiteDatabase myDatabase;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }


    public void createDatabase() throws IOException {
        boolean dbExist = checkDatabase();

        if (dbExist) {
            Log.v("DB Exists", "db exists");
        }

        boolean dbExist1 = checkDatabase();

        if (!dbExist1) {
            this.getReadableDatabase();
            try {
                this.close();
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying Database");
            }
        }
    }

    private boolean checkDatabase() {
        boolean checkDB = false;

        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File dbFile = new File(myPath);
            checkDB = dbFile.exists();
        } catch (SQLiteException e) {
        }

        return checkDB;
    }

    private void copyDatabase() throws IOException {
        String outFileName = DATABASE_PATH + DATABASE_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        byte[] buffer = new byte[1024];
        int length;

        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myInput.close();
        myOutput.flush();
        myOutput.close();
    }

    public void db_delete() {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if (file.exists()) {
            file.delete();
            System.out.println("Delete Database File");
        }
    }

    public void openDatabase() throws SQLException {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDatabase() throws SQLException {
        if (myDatabase != null)
            myDatabase.close();
        super.close();
    }

//    public LiveData<ArrayList<ToSell>> getSoldProducts(String TABLE_NAME) {
//        MutableLiveData<ArrayList<ToSell>> listLiveData = new MutableLiveData<>();
//        ArrayList<ToSell> list = new ArrayList<>();
//        String query = "SELECT * FROM " + TABLE_NAME;
//        Cursor data = myDatabase.rawQuery(query, null);
//        while (data.moveToNext()) {
//            String id = data.getString(data.getColumnIndex("id"));
//            String name = data.getString(data.getColumnIndex("name"));
//            String price = data.getString(data.getColumnIndex("price"));
//            String quantity = data.getString(data.getColumnIndex("quantity"));
//            String type = data.getString(data.getColumnIndex("type"));
//            ToSell toSell = new ToSell(id, name, price, quantity, type);
//            list.add(toSell);
//        }
//        listLiveData.setValue(list);
//        return listLiveData;
//    }

    public void getAllData() {


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.v("Database Uppgrade", "Database version higher than old.");
            db_delete();
        }

    }
}
