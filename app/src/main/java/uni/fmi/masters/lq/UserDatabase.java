package uni.fmi.masters.lq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class UserDatabase extends SQLiteOpenHelper {
    //the  name  of the db
    public static final String DB_NAME = "register.db";
    public static final int DB_VERSION = 1;

    //columns of  db table
    public static final String TABLE_USER = "user";
    public static final String TABLE_USER_ID = "id";
    public static final String TABLE_USER_FULLNAME = "fullname";
    public static final String TABLE_USER_USERNAME = "username";
    public static final String TABLE_USER_PASSWORD = "password";

    //creating db table
    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER
            + "('" + TABLE_USER_ID + "'INTEGER PRIMARY KEY AUTOINCREMENT," + "'"
            + TABLE_USER_FULLNAME + "'varchar(50) NOT NULL UNIQUE," + "'"
            + TABLE_USER_USERNAME + "'varchar(50) NOT NULL UNIQUE," + "'"
            + TABLE_USER_PASSWORD + "'varchar(50) NOT NULL)";
    //error   message
    public static final String MY_ERROR = "MyError";

    public UserDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating the table
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public boolean registerUser(User user) {
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(TABLE_USER_FULLNAME, user.getFullname());
            cv.put(TABLE_USER_USERNAME, user.getUsername());
            cv.put(TABLE_USER_PASSWORD, user.getPassword());

            if (db.insert(TABLE_USER, null, cv) != -1) {
                return true;
            }
        } catch (SQLException e) {
            Log.wtf(MY_ERROR, e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return false;
    }

    public boolean login(String username, String password) {
        SQLiteDatabase db = null;
        //the field is empty
        Cursor c = null;
        try {
            db = getReadableDatabase();
            String sql = "SELECT * FROM " + TABLE_USER
                    + " WHERE " + TABLE_USER_USERNAME + " = '" + username + "'"
                    + " AND " + TABLE_USER_PASSWORD + " = '" + password + "'";

            c = db.rawQuery(sql, null);

            return c.moveToFirst();

        } catch (SQLException e) {
            Log.wtf(MY_ERROR, e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }

            if (db != null){
                db.close();
            }
        }
        return false;
    }
}
