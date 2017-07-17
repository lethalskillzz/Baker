package com.lethalskillzz.bakingapp.data.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lethalskillzz.bakingapp.utils.AppConstants;


/**
 * Created by ibrahimabdulkadir on 20/06/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;

  public DbHelper(Context context) {
    super(context, AppConstants.DB_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(IngredientPersistenceContract.SQL_QUERY_CREATE);
    db.execSQL(StepPersistenceContract.SQL_QUERY_CREATE);
    db.execSQL(RecipePersistenceContract.SQL_QUERY_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    db.execSQL("DROP TABLE IF EXISTS " + RecipePersistenceContract.RecipeEntry.TABLE_NAME);
    db.execSQL("DROP TABLE IF EXISTS " + StepPersistenceContract.StepEntry.TABLE_NAME);
    db.execSQL("DROP TABLE IF EXISTS " + IngredientPersistenceContract.IngredientEntry.TABLE_NAME);
    onCreate(db);
  }
}
