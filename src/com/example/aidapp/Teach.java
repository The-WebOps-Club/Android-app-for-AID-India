package com.example.aidapp;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
public class Teach extends ContentProvider {
// Content Provider Uri and Authority
	public static String AUTHORITY = "com.dummies.aidapp.Teach";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
       + "/info");
	// MIME types used for searching words or looking up a single definition
	public static final String REMINDERS_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
    + "/vnd.com.dummies.android.taskreminder.reminder";
    public static final String REMINDER_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
    + "/vnd.com.dummies.android.taskreminder.reminder";
     // Database Columns
    private static final int DATABASE_VERSION = 1; 
    private static final String DATABASE_NAME = "Teachers";
    private static final String DATABASE_TABLE = "Teacher";
     // Database Columns
    public static final String COLUMN_no ="Sl_no";
    public static final String COLUMN_ID ="Teacher_ID";
    public static final String COLUMN_Center ="Center_Name";
    public static final String COLUMN_Name = "Name";
    private static final String DATABASE_CREATE = "create table"
              + DATABASE_TABLE + " ("+COLUMN_no+"integer primary key autoincrement," + COLUMN_ID+ " text not null, " + COLUMN_Name
              + " text not null, " + COLUMN_Center + " text not null,);";
// UriMatcher stuff
    private static final int LIST_REMINDER = 0;
    private static final int ITEM_REMINDER = 1;
    private static final UriMatcher sURIMatcher = buildUriMatcher();
    private SQLiteDatabase mDb;
/**
* Builds up a UriMatcher for search suggestion and shortcut refresh
* queries.
*/private static class DatabaseHelper extends SQLiteOpenHelper { 
	DatabaseHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION); 
	}
	@Override
	public void onCreate(SQLiteDatabase db) { 
	db.execSQL(DATABASE_CREATE); 
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion,
	int newVersion) { 
	throw new UnsupportedOperationException();
	}
	}
 private static UriMatcher buildUriMatcher() {
UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
// to get definitions...
matcher.addURI(AUTHORITY, "info", LIST_REMINDER);
matcher.addURI(AUTHORITY, "info/#", ITEM_REMINDER);
return matcher;
}
@Override
	public boolean onCreate() {
		mDb = new DatabaseHelper(getContext()).getWritableDatabase();
		return true;
}
@Override
public Cursor query(Uri uri, String[] ignored1, String ignored2,String[] ignored3, String ignored4)
{ 
String[] projection = new String[] 
		{ Teach.COLUMN_no,
          Teach.COLUMN_ID, Teach.COLUMN_Center,
           Teach.COLUMN_Name }; 
// Use the UriMatcher to see the query type and format the
// db query accordingly
          Cursor c;
          switch (sURIMatcher.match(uri)) { 
          case LIST_REMINDER: 
          c = mDb.query(Teach.DATABASE_TABLE, projection, null,
        		  null, null, null, null);
          break;
          case ITEM_REMINDER: 
          c = mDb.query(Teach.DATABASE_TABLE, projection, 
        		  Teach.COLUMN_no + "=?", new String[] { Long.toString(ContentUris.parseId(uri)) }, null, null, null, null);
          if (c != null && c.getCount() > 0) { 
        	  c.moveToFirst(); 
          }
          break;
          default: 
        	  throw new IllegalArgumentException("Unknown Uri: " + uri);
 }
c.setNotificationUri(getContext().getContentResolver(), uri); 
return c; 
}
@Override
public Uri insert(Uri uri, ContentValues values) { 
values.remove(Teach.COLUMN_no); 
long id = mDb.insertOrThrow(Teach.DATABASE_TABLE, null, 
values);
getContext().getContentResolver().notifyChange(uri, null); 
return ContentUris.withAppendedId(uri, id); 
}
@Override
public int delete(Uri uri, String ignored1, String[] ignored2) { 
int count = mDb.delete(Teach.DATABASE_TABLE,
Teach.COLUMN_no + "=?",
new String[] { Long.toString(ContentUris.parseId(uri)) }); 
if (count > 0) 
getContext().getContentResolver().notifyChange(uri, null);
return count; 
}
@Override
public int update(Uri uri, ContentValues values, String ignored1,
String[] ignored2) { 
int count = mDb.update(Teach.DATABASE_TABLE, values,
COLUMN_no + "=?",
new String[] { Long.toString(ContentUris.parseId(uri)) }); 
if( count>0 )
getContext().getContentResolver().notifyChange(uri, null); 
return count; 
}
/**
* This method is required in order to query the supported types. It’s also
* useful in our own query() method to determine the type of Uri received.
*/
@Override
public String getType(Uri uri) {
switch (sURIMatcher.match(uri)) {
case LIST_REMINDER:
return REMINDERS_MIME_TYPE;
case ITEM_REMINDER:
return REMINDER_MIME_TYPE;
default:
throw new IllegalArgumentException("Unknown Uri: " + uri);
   }
}
}
//The SQLite DatabaseHelper code was omitted for brevity.
