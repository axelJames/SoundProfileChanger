package com.example.james.soundprofilechanger;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {
    SharedPreferences prefs;
    String prefName = "MyPref";

    private static final int DATABASE_VERSION = 1;
    public static final String KEY_ROW_ID = "_id";
    private static final String DATABASE_NAME = "profileChanger";
    public static final String STARTHOUR = "STARTHR";
    public static final String ENDHOUR = "ENDHR";
    private SQLiteDatabase db;
    public static final String PROJECTION[] = {KEY_ROW_ID,
          STARTHOUR,ENDHOUR
    };
    public static final String WEEKDAYS[] = {"MON","TUE","WED","THU","FRI","SAT","SUN"
    };
    public DBHelper(Context ctx){
        //db=ctx.openOrCreateDatabase(DATABASE_NAME,Context.MODE_PRIVATE,factory);
        super(ctx,DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
              String CREATE_TABLE_TIMES = "CREATE TABLE TIMES("+KEY_ROW_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+STARTHOUR+
                      " INTEGER,"+ENDHOUR+" INTEGER,MON INTEGER,TUE INTEGER,WED INTEGER,THU INTEGER,FRI INTEGER,SAT INTEGER,SUN INTEGER)";
        db.execSQL(CREATE_TABLE_TIMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TIMES");
        onCreate(db);
    }
    void addTime(int staHr,int staMin,int endHr,int endMin) {
       // main=new MainActivity();
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("STARTHR", staHr);
        //values.put("STARTMIN",staMin);
        values.put("ENDHR", endHr);
        //7values.put("ENDMIN", endMin);

        db.insert("TIMES", null, values);
        db.close();
    }
    public Cursor fetchAllRows(){
        db = this.getWritableDatabase();
        return db.query("TIMES", PROJECTION,
                null, null, null, null,null);
    }

    public void delete(long position) {
        String where=KEY_ROW_ID+"="+Long.toString(position);
        db = this.getWritableDatabase();
        db.delete("TIMES",where,null);
    }
    public void overlap(){
        db = this.getReadableDatabase();
        String time[]={STARTHOUR,ENDHOUR};
        Integer prev;
        Integer start=0;
        for(int i=0;i<7;i++){
            Cursor cursor=db.query("TIMES",time,WEEKDAYS[i]+"=1",null,null,null,STARTHOUR);
            if (cursor.moveToFirst()){
                do{
                    prev=start;
                    start = cursor.getInt(cursor.getColumnIndex(STARTHOUR));
                    Integer end = cursor.getInt(cursor.getColumnIndex(ENDHOUR));
                    if(prev>end){
                        break;
                    }
                }while(cursor.moveToNext());
            }
            cursor.close();
        }

    }
}
