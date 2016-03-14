package com.zxxk.demo.nutrition.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zxxk.demo.nutrition.common.Constant;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-14
 * Time: 10:07
 * Description:
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "Database";
    private static final int DB_VERSION = Constant.DATABASE_VERSION;
    private static final String DB_NAME = Constant.DATABASE_NAME;
    private static DbOpenHelper dbOpenHelper;

    public static DbOpenHelper getInstance(Context context){
        if (dbOpenHelper == null ){
            synchronized (DbOpenHelper.class){
                if (dbOpenHelper == null){
                    dbOpenHelper = new DbOpenHelper(context);
                }
            }
        }
        return dbOpenHelper;
    }

    private void createAllTables(SQLiteDatabase db,boolean ifNotExist){
        CacheDao.createTable(db,ifNotExist);
    }

    private void dropAllTables(SQLiteDatabase db,boolean ifExist){
        CacheDao.dropTable(db,ifExist);
    }

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createAllTables(sqLiteDatabase,false);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        dropAllTables(sqLiteDatabase,true);
        onCreate(sqLiteDatabase);
    }
}