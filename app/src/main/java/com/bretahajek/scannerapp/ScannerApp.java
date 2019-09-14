package com.bretahajek.scannerapp;

import android.app.Application;

import com.bretahajek.scannerapp.db.AppDatabase;

public class ScannerApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = getAppExecutors();
    }

    public AppExecutors getAppExecutors() {
        return AppExecutors.getInstance();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase(), mAppExecutors);
    }
}
