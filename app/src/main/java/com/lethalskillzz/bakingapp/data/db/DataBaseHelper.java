package com.lethalskillzz.bakingapp.data.db;

import android.content.Context;

import com.lethalskillzz.bakingapp.di.module.ApplicationContext;
import com.lethalskillzz.bakingapp.di.module.DatabaseInfo;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;

/**
 * Created by ibrahimabdulkadir on 19/06/2017.
 */

public class DataBaseHelper extends DaoMaster.OpenHelper {

    @Inject
    public DataBaseHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        AppLogger.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
        switch (oldVersion) {
            case 1:
            case 2:
                //db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN "
                // + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
        }
    }

}
