package com.bretahajek.scannerapp;

import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;

import androidx.appcompat.app.AppCompatActivity;

import com.bretahajek.scannerapp.db.AppDatabase;

import org.opencv.android.OpenCVLoader;


public class MainActivity extends AppCompatActivity {
    private TextureView viewFinder;

    static {
        if (!OpenCVLoader.initDebug()) {
            Log.e("ERROR", "Unable to load OpenCV");
        } else {
            Log.d("SUCCESS", "OpenCV loaded");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase.getInstance(getBaseContext());

    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }
}
