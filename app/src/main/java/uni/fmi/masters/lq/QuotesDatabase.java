package uni.fmi.masters.lq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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

    //creat  table
    public static final String CREATE_TABLE_QUOTES = "CREATE TABLE " + TABLE_QUOTE
            + "('" + TABLE_QUOTE_ID + "'INTEGER PRIMARY KEY AUTOINCREMENT," + "'"
            + TABLE_QUOTE_AUTHOR + "'varchar(50) NOT NULL ," + "'"
            + TABLE_QUOTE_CONTENT + "'varchar(100) NOT NULL ," + "'"
            + TABLE_QUOTE_TIME + "'varchar(50) NOT NULL UNIQUE," + "'"
            + TABLE_QUOTE_DATE + "'varchar(50) NOT NULL)";
    //error message
    public static final String MY_ERROR = "Something is wrong!";

    //constructor
    public QuotesDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL(CREATE_TABLE_QUOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUOTE);
        onCreate(db);
    }

    //save quote data in  the database
    public long addQuote(Quote quote) {

        SQLiteDatabase db =  null;
        ContentValues cv = null;
        try {
            db = this.getWritableDatabase();
            cv = new ContentValues();
            cv.put(TABLE_QUOTE_AUTHOR, quote.getAuthor());
            cv.put(TABLE_QUOTE_CONTENT, quote.getContent());
            cv.put(TABLE_QUOTE_TIME, quote.getTime());
            cv.put(TABLE_QUOTE_DATE, quote.getDate());

        }catch (SQLException e){
            Log.wtf(MY_ERROR,e.getMessage());
        }finally {
            if(db !=null){
                db.close();
            }
        }
        long ID = db.insert(TABLE_QUOTE, null, cv);
        Log.d("Inserted", "ID -> " + ID);
        return ID;
    }

    //return a  single quote
    public Quote getQuote(long id) {
        //select  * from databaseTable   where id=1
        //we read the data from  the db
        SQLiteDatabase db = this.getReadableDatabase();
        //cursor point to the specific item in the db
        Cursor cursor = db.query(TABLE_QUOTE, new String[]{TABLE_QUOTE_ID, TABLE_QUOTE_AUTHOR, TABLE_QUOTE_CONTENT, TABLE_QUOTE_TIME, TABLE_QUOTE_DATE}, TABLE_QUOTE_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            //cursor starts at minus 1 so we  use moveToFirst to move  forward
            cursor.moveToFirst();
        }
        //returning the quote
        Quote quote = new Quote(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return quote;
    }

    //return list  of   quotes in recycle view
    public List<Quote> getQuotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Quote> allQuotes = new ArrayList<>();
        //  select  * from database table
        String query = "SELECT * FROM " + TABLE_QUOTE;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                //create neq   quote and  save  the data
                Quote quote = new Quote();
                quote.setID(cursor.getLong(0));
                quote.setAuthor(cursor.getString(1));
                quote.setContent(cursor.getString(2));
                quote.setTime(cursor.getString((3)));
                quote.setDate(cursor.getString(4));
            } while (cursor.moveToNext());

        }
        return allQuotes;
    }
}
