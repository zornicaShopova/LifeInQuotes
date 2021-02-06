package uni.fmi.masters.lq;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class QuotesDatabase extends SQLiteOpenHelper {
    //version of the db
    public static final int DATABASE_VERSION = 2;
    //name  of  the db
    public static final String DATABASE_NAME = "quotesItems.db";
    //table name
    public static final String TABLE_QUOTE = "quote";

    //columns of db
    public static final String TABLE_QUOTE_ID = "id";
    public static final String TABLE_QUOTE_AUTHOR = "author";
    public static final String TABLE_QUOTE_CONTENT = "content";
    public static final String TABLE_QUOTE_TIME = "time";
    public static final String TABLE_QUOTE_DATE = "date";


    //consturctor
    public QuotesDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String query = "CREATE TABLE " + TABLE_QUOTE
                + "('" + TABLE_QUOTE_ID + "'INTEGER PRIMARY KEY AUTOINCREMENT," + "'"
                + TABLE_QUOTE_AUTHOR + "'varchar(50) NOT NULL ," + "'"
                + TABLE_QUOTE_CONTENT + "'varchar(100) NOT NULL ," + "'"
                + TABLE_QUOTE_TIME + "'varchar(50) NOT NULL UNIQUE," + "'"
                + TABLE_QUOTE_DATE + "'varchar(50) NOT NULL)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       if(oldVersion >= newVersion)
           return;
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUOTE);
       onCreate(db);
    }
}
