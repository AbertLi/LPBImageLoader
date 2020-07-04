package com.example.interviewquestions;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentPri extends ContentProvider {
    private SQLiteDatabase db;
    private static final String MAUTHORITIESNAME = "com.example.interviewquestions.MyContentPri";
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int PERSON = 1;
    private static final int PERSON_NUMBER = 2;
    private static final int PERSON_TEXT = 3;
    private static final String TABLE_NAME = "table_person";
    // 构建URI
    static {
        // content://programandroid/person
        matcher.addURI(MAUTHORITIESNAME, "person", PERSON);
        // # 代表任意数字content://programandroid/person/4
        matcher.addURI(MAUTHORITIESNAME, "person/#", PERSON_NUMBER);
        // * 代表任意文本 content://programandroid/person/filter/ssstring
        matcher.addURI(MAUTHORITIESNAME, "person/filter/*", PERSON_TEXT);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
