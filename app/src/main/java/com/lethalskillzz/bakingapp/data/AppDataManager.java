package com.lethalskillzz.bakingapp.data;

import android.content.Context;

import com.lethalskillzz.bakingapp.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ibrahimabdulkadir on 20/06/2017.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
//    private final DbHelper mDbHelper;
//    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context
//                          DbHelper dbHelper,
//                          ApiHelper apiService
    ) {
        mContext = context;
//        mDbHelper = dbHelper;
//        mApiHelper = apiService;
    }

}
