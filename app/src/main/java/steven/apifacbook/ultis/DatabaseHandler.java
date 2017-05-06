package steven.apifacbook.ultis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import steven.apifacbook.model.download.DownLoad;

/**
 * Created by TruongNV on 4/8/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ApiFb";

    // Contacts table name
    private static final String TABLE_DOWNLOAD = "download";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_URI_FILE = "uriFile";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DOWNLOAD + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_URI_FILE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOWNLOAD);

        // Create tables again
        onCreate(db);
    }

    public void addContact(DownLoad downLoad) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, downLoad.getName()); // Contact Name
        values.put(KEY_URI_FILE, downLoad.getUriFile()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_DOWNLOAD, null, values);
        db.close(); // Closing database connection
    }

    public List<DownLoad> getAllContacts() {
        List<DownLoad> contactList = new ArrayList<DownLoad>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DOWNLOAD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DownLoad downLoad = new DownLoad();
                downLoad.setId(Integer.parseInt(cursor.getString(0)));
                downLoad.setName(cursor.getString(1));
                downLoad.setUriFile(cursor.getString(2));
                // Adding contact to list
                contactList.add(downLoad);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DOWNLOAD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    public void deleteContact(DownLoad downLoad) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DOWNLOAD, KEY_ID + " = ?",
                new String[] { String.valueOf(downLoad.getId()) });
        db.close();
    }
}
