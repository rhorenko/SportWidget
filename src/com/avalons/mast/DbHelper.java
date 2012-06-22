package com.avalons.mast;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DbHelper extends SQLiteOpenHelper 
        implements BaseColumns {
	public static final String TAG = "SportWidget->DbHelper"; 
	
    public static final String TABLE_NAME = "schedule";
    public static final String CITY = "city";
    public static final String CLUB = "club";
    public static final String ROOM = "room";
    public static final String TYPE_TRAINING = "type_training";
    public static final String TYPE_PROGRAM = "type_program";
    public static final String TRAINING = "training";
    public static final String DAY = "day";
    public static final String TIME_START = "time_start";
    public static final String DURATION = "duration";
    public static final String TRAINER = "trainer";
    public static final String PLACE = "place";
    public static final String NOTES = "notes";
    public static final String DESCRIPTION = "description";
    public static final String ADAPTER = "adapter";
    public static final String ISSELECTED = "isselected";
    public DbHelper(Context context) {
        super(context, Provider.DB_SCHEDULE, null, 1);
    }
    
    
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME 
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CITY + " TEXT, "
                + CLUB +" TEXT, "
                + ROOM + " TEXT, "
                + TYPE_TRAINING + " TEXT, "
                + TYPE_PROGRAM + " TEXT, "
                + TRAINING + " TEXT, "
                + DAY + " TEXT, "
                + TIME_START + " TEXT, "
                + DURATION + " TEXT, "
                + TRAINER + " TEXT, "
                + PLACE + " TEXT, "
                + NOTES + " TEXT, "
                + DESCRIPTION + " TEXT, "
                + ISSELECTED + " TEXT, "
                + ADAPTER + " TEXT);");
        
        ContentValues values = new ContentValues();
                      
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "+");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);  
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "По записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "+");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
        values.put(CITY, "Нижний Новгород");
        values.put(CLUB, "World Class Пушкинский");
        values.put(ROOM, "Спорт");
        values.put(TYPE_TRAINING, "Групповые программы");
        values.put(TYPE_PROGRAM, "Программы Les Mills");
        values.put(TRAINING, "BODYPUMP");
        values.put(DAY, "Пн.");
        values.put(TIME_START, "20:00");
        values.put(DURATION, "55");
        values.put(TRAINER, "Алексей З.");
        values.put(PLACE, "Большой зал");
        values.put(NOTES, "по записи");
        values.put(DESCRIPTION, "Класс с использованием базовых элементов Йоги");
        values.put(ISSELECTED, "-");
        values.put(ADAPTER, "BODYPUMP(55), Алексей З., Большой зал");
        db.insert(TABLE_NAME, CITY, values);
        
    }

    
    
    public void onUpgrade(
            SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }   
}