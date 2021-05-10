package com.demo.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;


public class Database extends SQLiteOpenHelper {
    static Database instance = null;
    static SQLiteDatabase database = null;

    static final String DATABASE_NAME = "DB";
    static final int DATABASE_VERSION = 1;

    public static final String ALARM_TABLE = "note";
    public static final String COLUMN_ALARM_ID = "_id";
    public static final String COLUMN_ALARM_CONTENT = "note_content";


    public static Database getInstance(Context context) {
        if (null == instance) {
            instance = new Database(context);
        }
        return instance;
    }

    private static SQLiteDatabase getDatabase() {
        if (null == database) {
            database = instance.getWritableDatabase();
        }
        return database;
    }

    public void deactivate() {
        if (null != database && database.isOpen()) {
            database.close();
        }
        database = null;
        instance = null;
    }

    public long create(Note alarm) {

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ALARM_CONTENT, alarm.getContent());

        return getDatabase().insert(ALARM_TABLE, null, cv);
    }

    public int update(Note alarm) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ALARM_CONTENT, alarm.getContent());

        return getDatabase().update(ALARM_TABLE, cv, "_id=" + alarm.getId(), null);
    }

    public int deleteEntry(Note alarm) {
        return deleteEntry(alarm.getId());
    }

    public int deleteEntry(int id) {
        return getDatabase().delete(ALARM_TABLE, COLUMN_ALARM_ID + "=" + id, null);
    }

    public int deleteAll() {
        return getDatabase().delete(ALARM_TABLE, "1", null);
    }

    public Note getAlarm(int id) {
        String[] columns = new String[]{
                COLUMN_ALARM_ID,
                COLUMN_ALARM_CONTENT
        };
        Cursor c = getDatabase().query(ALARM_TABLE, columns, COLUMN_ALARM_ID + "=" + id, null, null, null,
                null);
        Note alarm = null;

        if (c.moveToFirst()) {

            alarm = new Note();
            alarm.setId(c.getInt(1));
            alarm.setContent(c.getString(2));
        }
        c.close();
        return alarm;
    }

    public Cursor getCursor() {
        String[] columns = new String[]{
                COLUMN_ALARM_ID,
                COLUMN_ALARM_CONTENT
        };
        return getDatabase().query(ALARM_TABLE, columns, null, null, null, null,
                null);
    }

    Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + ALARM_TABLE + " ( "
                + COLUMN_ALARM_ID + " INTEGER primary key autoincrement, "
                + COLUMN_ALARM_CONTENT + " TEXT NOT NULL "
                + " ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ALARM_TABLE);
        onCreate(db);
    }

    public List<Note> getAll() {

        List<Note> alarms = new ArrayList<>();
        Cursor cursor = getCursor();

        if (cursor.moveToFirst()) {

            do {
                Note alarm = new Note();
                alarm.setId(cursor.getInt(0));
                alarm.setContent(cursor.getString(1));

                alarms.add(alarm);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return alarms;
    }
}
