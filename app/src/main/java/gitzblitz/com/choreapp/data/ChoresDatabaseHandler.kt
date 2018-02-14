package gitzblitz.com.choreapp.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import gitzblitz.com.choreapp.model.*

/**
 * Created by george.ngethe on 14/02/2018.
 */
class ChoresDatabaseHandler(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        // SQL
        var CREATE_CHORE_TABLE = "CREATE TABLE "+ TABLE_NAME + "(" + KEY_ID + "INTEGER PRIMARY KEY," +
                KEY_CHORE_NAME + "TEXT," +
                KEY_CHORE_ASSIGNED_BY + "TEXT," +
                KEY_CHORE_ASSIGNED_TO + "TEXT," +
                KEY_CHORE_ASSIGNED_TIME + "LONG" + ")"

        db?.execSQL(CREATE_CHORE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}