package com.notes_dbflow.model;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * 在此写用途
 * Created by hawk on 2016/5/7.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    public static final String NAME = "AppDatabase"; // we will add the .db extension
    public static final int VERSION = 1;
}
