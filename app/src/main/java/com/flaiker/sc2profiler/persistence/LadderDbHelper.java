package com.flaiker.sc2profiler.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.flaiker.sc2profiler.persistence.LadderContract.ProfileEntry;

import static com.flaiker.sc2profiler.persistence.LadderContract.LadderEntry;

/**
 * Helper class for communication with the SQLite database
 */
public final class LadderDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ladder.db";
    private static final int DATABASE_VERSION = 13;

    public LadderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //@formatter:off
        final String SQL_CREATE_LADDER_TABLE =
                "CREATE TABLE " + LadderEntry.TABLE_NAME + " (" +
                    LadderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    LadderEntry.COLUMN_LADDER_ID + " INTEGER," +
                    LadderEntry.COLUMN_CHARACTER_ID + " INTEGER, " +
                    LadderEntry.COLUMN_REALM + " INTEGER, " +
                    LadderEntry.COLUMN_DISPLAY_NAME + " TEXT NOT NULL," +
                    LadderEntry.COLUMN_CLAN_NAME + " TEXT," +
                    LadderEntry.COLUMN_CLAN_TAG + " TEXT," +
                    LadderEntry.COLUMN_PROFILE_PATH + " TEXT NOT NULL," +
                    LadderEntry.COLUMN_POINTS + " INTEGER," +
                    LadderEntry.COLUMN_WINS + " INTEGER," +
                    LadderEntry.COLUMN_LOSSES + " INTEGER," +
                    LadderEntry.COLUMN_RACE + " TEXT," +
                    LadderEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    " UNIQUE (" +
                        LadderEntry.COLUMN_LADDER_ID +
                    ") ON CONFLICT REPLACE" +
                ");";
        //@formatter:on

        //@formatter:off
        final String SQL_CREATE_PROFILES_TABLE =
                "CREATE TABLE " + ProfileEntry.TABLE_NAME + " (" +
                    ProfileEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ProfileEntry.COLUMN_REALM + " INTEGER," +
                    ProfileEntry.COLUMN_CHARACTER_ID + " INTEGER," +
                    ProfileEntry.COLUMN_DISPLAY_NAME + " TEXT NOT NULL," +
                    ProfileEntry.COLUMN_CLAN_NAME + " TEXT," +
                    ProfileEntry.COLUMN_CLAN_TAG + " TEXT," +
                    ProfileEntry.COLUMN_PROFILE_PATH + " TEXT NOT NULL," +
                    ProfileEntry.COLUMN_PORTRAIT_LINK + " TEXT," +
                    ProfileEntry.COLUMN_PORTRAIT_X + " INTEGER," +
                    ProfileEntry.COLUMN_PORTRAIT_Y + " INTEGER," +
                    ProfileEntry.COLUMN_PORTRAIT_W + " INTEGER," +
                    ProfileEntry.COLUMN_PORTRAIT_H + " INTEGER," +
                    ProfileEntry.COLUMN_PORTRAIT_OFFSET + " INTEGER," +
                    ProfileEntry.COLUMN_RACE + " TEXT NOT NULL," +
                    ProfileEntry.COLUMN_FAVORITE + " INTEGER," +
                    ProfileEntry.COLUMN_LOSSES + " INTEGER," +
                    ProfileEntry.COLUMN_WINS + " INTEGER," +
                    ProfileEntry.COLUMN_LEAGUE + " TEXT," +
                    ProfileEntry.COLUMN_RANK + " INTEGER," +
                    ProfileEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    " UNIQUE (" +
                        ProfileEntry.COLUMN_CHARACTER_ID + ", " +
                        ProfileEntry.COLUMN_REALM +
                    ") ON CONFLICT REPLACE" +
                ");";
        //@formatter:on

        db.execSQL(SQL_CREATE_LADDER_TABLE);
        db.execSQL(SQL_CREATE_PROFILES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LadderEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ProfileEntry.TABLE_NAME);
        onCreate(db);
    }
}
