package com.nexp.pavel.networkexpertise.di;

import com.nexp.pavel.networkexpertise.di.modules.ApiModule;
import com.nexp.pavel.networkexpertise.mvp.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class
})

public interface AppComponent {

    void inject(MainPresenter mainPresenter);

}
