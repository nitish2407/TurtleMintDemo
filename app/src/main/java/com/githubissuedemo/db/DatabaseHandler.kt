package com.githubissuedemo.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.githubissuedemo.models.CommentsResponse
import com.githubissuedemo.models.IssuesResponse
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.json.JSONArray
import org.json.JSONObject

class DatabaseHandler(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_ISSUES + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                ISSUE_ID + " INTEGER," +
                ISSUE_JSON + " TEXT," +
                COMMENTS_JSON + " TEXT" + "," +
                " UNIQUE(" + ISSUE_ID + ") ON CONFLICT REPLACE)")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun commentUpdate(id: Int, comments: ArrayList<CommentsResponse>) {
        val db = this.writableDatabase

        val values = ContentValues()
        val commentJson = Gson().toJson(comments);
        values.put(COMMENTS_JSON, commentJson)
        db.update(
            TABLE_ISSUES,
            values,
            "$ISSUE_ID = ?",
            arrayOf(java.lang.String.valueOf(id))
        )
    }

    fun getAllIssuesFromDb(): MutableLiveData<ArrayList<IssuesResponse>> {
        val db = this.readableDatabase
        val issueList = ArrayList<IssuesResponse>()
        val mutableList = MutableLiveData<ArrayList<IssuesResponse>>()
        val c: Cursor = db.rawQuery("SELECT $ISSUE_JSON FROM $TABLE_ISSUES", null)
        if (c.moveToFirst()) {
            do {
                val issueResponse = Gson().fromJson(c.getString(0), IssuesResponse::class.java)
                issueList.add(issueResponse)
            } while (c.moveToNext())
        }
        c.close()
        mutableList.value = issueList
        return mutableList
    }

    @SuppressLint("Range")
    fun getAllCommentsFromDb(id: Int?): MutableLiveData<ArrayList<CommentsResponse>> {
        val db = this.readableDatabase
        val commentsMutableLiveData = MutableLiveData<ArrayList<CommentsResponse>>()
        val mCommentList = ArrayList<CommentsResponse>()
        val c = db.rawQuery(
            "select * from $TABLE_ISSUES" + " where $ISSUE_ID =?",
            arrayOf(id.toString())
        )
        if (c.moveToFirst()) {
            val comments = c.getString(c.getColumnIndex("comments_json"))
            if (comments != null) {
                val jsonArray = JSONArray(comments)
                for (i in 0..jsonArray.length() - 1) {
                    try {

                        val jsonobj = jsonArray[i] as JSONObject

                        val responseModel = Gson().fromJson(jsonobj.toString(), CommentsResponse::class.java)

                        mCommentList.add(responseModel)

                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
            }
        }
        c.close()
        commentsMutableLiveData.value = mCommentList
        return commentsMutableLiveData
    }

    fun addIssue(issues: ArrayList<IssuesResponse>) {
        val db = this.writableDatabase
        for (issue in issues) {
            val issueJson = Gson().toJson(issue)
            val cv = ContentValues()
            cv.put(ISSUE_ID, issue.id)
            cv.put(ISSUE_JSON, issueJson)
            db.insertWithOnConflict(TABLE_ISSUES, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    companion object {
        private val DATABASE_NAME = "Github.db"
        private val DATABASE_VERSION = 1
        val TABLE_ISSUES = "table_issues"
        val ID_COL = "id"
        val ISSUE_ID = "issue_id"
        val ISSUE_JSON = "issue_json"
        val COMMENTS_JSON = "comments_json"

    }
}