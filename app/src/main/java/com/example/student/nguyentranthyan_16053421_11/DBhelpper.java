package com.example.student.nguyentranthyan_16053421_11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBhelpper extends SQLiteOpenHelper {

    public DBhelpper(Context context) {
        super(context, "booklist", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table Book(id integer primary key, title text, idauthor integer)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists Book";
        db.execSQL(query);
        onCreate(db);
    }

    public boolean insertBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", book.getId());
        contentValues.put("title", book.getTitle());
        contentValues.put("idauthor", book.getIdAuthor());
        db.insert("Book", null, contentValues);
        db.close();
        return true;
    }

    public Book getBook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from Book where id = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null)
            cursor.moveToFirst();
        Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        return book;
    }

    public boolean deleteBook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "delete from Book where id = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor == null) {
            return false;
        } else {
            cursor.moveToFirst();
            cursor.close();
        }
        return true;
    }

    public ArrayList<Book> getALlBook() {
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from Book";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null)
            cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            list.add(new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
}
