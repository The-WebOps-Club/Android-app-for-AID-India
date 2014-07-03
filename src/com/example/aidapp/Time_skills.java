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
		public class Time_skills extends ContentProvider {
			// Database Related Constants
			private static final int DATABASE_VERSION = 1; 
			private static final String DATABASE_NAME = "TimestampSkills"; 
			private static final String DATABASE_TABLE = "Skills"; 
			// Database Columns
			public static final String id="id";
			public static final String s= "Skills"; 
			
			public static final String t1 = "time_1"; 
			public static final String t2 = "time_2"; 
			public static final String t3 = "time_3"; 
			public static final String t4 = "time_4";
			public static final String t5 = "time_5"; 
			public static final String t6 = "time_6"; 
			public static final String t7 = "time_7"; 
			public static final String t8 = "time_8";
			public static final String t9 = "time_9"; 
			public static final String t10= "time_10"; 
			public static final String t11= "time_11"; 
			public static final String t12= "time_12";
			
			private static final String DATABASE_CREATE = "create table"
					+ DATABASE_TABLE + " ("+id+"integer primary key autoincrement," + s+ "text not null,"   + t1+ " integer not null, " 
					+ t2+ " integer not null," + t3+ " integer not null," 
					+ t4+ " integer not null," + t5+ " integer not null," 
					+ t6+ " integer not null," + t7+ " integer not null," 
					+ t8+ " integer not null," + t9+ " integer not null,"
					+ t10+ " integer not null," + t11+ " integer not null," 
					+ t12  + " integer not null,);";
			// UriMatcher stuff
			private static final int LIST= 0;
			private static final int ITEM= 1;
			private static final UriMatcher sURIMatcher = buildUriMatcher();
			// Content Provider Uri and Authority
			public static String AUTHORITY = "com.example.aidapp.Time_skills"; 
			public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/skill"); 
			// MIME types used for searching words or looking up a single definition
			public static final String REMINDERS_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/vnd.com.example.aidapp.Time_skills"; 
			public static final String REMINDER_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/vnd.com.example.aidapp.Time_skills";
			
			private SQLiteDatabase mDb;
			/**
			* Builds up a UriMatcher for search suggestion and shortcut refresh
			* queries.
			* 
			*/
			private static class DatabaseHelper extends SQLiteOpenHelper { 
				DatabaseHelper(Context context)
				{
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
			private static UriMatcher buildUriMatcher()
			{
				UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
				// to get definitions...
				matcher.addURI(AUTHORITY, "skill", LIST);
				matcher.addURI(AUTHORITY, "skill/#", ITEM);
				return matcher;
			}
			@Override
			public boolean onCreate()
			{
				mDb = new DatabaseHelper(getContext()).getWritableDatabase();
				return true;
			}
			
			@Override
			public Cursor query(Uri uri, String[] ignored1, String ignored2,
			String[] ignored3, String ignored4)
			{ 
				String[] projection = new String[] { Time_skills.id,
				Time_skills.s, Time_skills.t1, Time_skills.t1, Time_skills.t1,
				Time_skills.t1, Time_skills.t1, Time_skills.t1, Time_skills.t1, Time_skills.t1, Time_skills.t1, Time_skills.t1, Time_skills.t1, Time_skills.t1,
				 }; 
				// Use the UriMatcher to see the query type and format the
				// db query accordingly
				Cursor c;
				switch (sURIMatcher.match(uri))
				{ 
					case LIST: 
					c = mDb.query(Time_skills.DATABASE_TABLE, projection, null,
			        null, null, null, null); 
					break;
					case ITEM: 
					c = mDb.query(Time_skills.DATABASE_TABLE, projection, 
					Time_skills.id + "=?", new String[] { 
							Long.toString(ContentUris.parseId(uri)) }, null, null, null, null);
					if (c != null && c.getCount() > 0) 
					{ 
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
				values.remove(Time_skills.id); 
				long id = mDb.insertOrThrow(Time_skills.DATABASE_TABLE, null, 
				values);
				getContext().getContentResolver().notifyChange(uri, null); 
				return ContentUris.withAppendedId(uri, id); 
				}
				@Override
				public int delete(Uri uri, String ignored1, String[] ignored2) { 
				int count = mDb.delete(Time_skills.DATABASE_TABLE,
				Time_skills.id + "=?",
				new String[] { Long.toString(ContentUris.parseId(uri)) });
				if (count > 0) 
				getContext().getContentResolver().notifyChange(uri, null);
				return count; 
				}
				@Override
				public int update(Uri uri, ContentValues values, String ignored1,
				String[] ignored2) { 
				int count = mDb.update(Time_skills.DATABASE_TABLE, values,
				id + "=?",
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
				case LIST:
				return REMINDERS_MIME_TYPE;
				case ITEM:
				return REMINDER_MIME_TYPE;
				default:
				throw new IllegalArgumentException("Unknown Uri: " + uri);
				}
				}
		}