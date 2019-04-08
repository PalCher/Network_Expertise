package com.nexp.pavel.networkexpertise;

import android.app.Application;

import com.nexp.pavel.networkexpertise.di.AppComponent;
import com.nexp.pavel.networkexpertise.di.DaggerAppComponent;


public class App extends Application {

    private static App instance;

    private AppComponent appComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;


        appComponent = DaggerAppComponent.builder()
                .build();
    }

    public static App getInstance()
    {
        return instance;
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
