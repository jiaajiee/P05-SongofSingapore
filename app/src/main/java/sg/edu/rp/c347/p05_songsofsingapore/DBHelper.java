package sg.edu.rp.c347.p05_songsofsingapore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 15017274 on 18/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "song.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SONG_YEAR = "song_year";
    private static final String COLUMN_SONG_TITLE = "song_title";
    private static final String COLUMN_SONG_SINGER = "song_singer";
    private static final String COLUMN_SONG_STARS = "song_stars";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SONG_YEAR + " INTEGER,"
                + COLUMN_SONG_TITLE + " TEXT,"
                + COLUMN_SONG_SINGER + " TEXT,"
                + COLUMN_SONG_STARS + " INTEGER ) ";
        db.execSQL(createSongTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);
    }

    public long insertSong(int year, String title, String singer, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_YEAR, year);
        values.put(COLUMN_SONG_TITLE, title);
        values.put(COLUMN_SONG_SINGER, singer);
        values.put(COLUMN_SONG_STARS, stars);
        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        Log.d("SQL Insert",""+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Song> getAllSong() {
        //TODO return records in Java objects
        ArrayList<Song> notes = new ArrayList<>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_SONG_YEAR + ", "
                + COLUMN_SONG_TITLE + ", "
                + COLUMN_SONG_SINGER + ", "
                + COLUMN_SONG_STARS
                + " FROM " + TABLE_SONG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int year = cursor.getInt(1);
                String title = cursor.getString(2);
                String singer = cursor.getString(3);
                int stars = cursor.getInt(4);
                Song obj = new Song(id, title, singer, year, stars);
                notes.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

    public int updateSong(Song data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, data.getId());
        values.put(COLUMN_SONG_TITLE, data.getTitle());
        values.put(COLUMN_SONG_SINGER, data.getSingers());
        values.put(COLUMN_SONG_YEAR, data.getYear());
        values.put(COLUMN_SONG_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }

    public ArrayList<Song> getSongWithRating5() {
        ArrayList<Song> songs = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_SONG_TITLE, COLUMN_SONG_SINGER, COLUMN_SONG_YEAR, COLUMN_SONG_STARS};
        String condition = COLUMN_SONG_STARS + " Like ?";
        String[] args = { "5" };
        Cursor cursor = db.query(TABLE_SONG, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int year = cursor.getInt(1);
                String title = cursor.getString(2);
                String singer = cursor.getString(3);
                int stars = cursor.getInt(4);
                Song obj = new Song(id, title, singer, year, stars);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }


}
