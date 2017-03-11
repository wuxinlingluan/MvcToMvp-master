package com.pinzhao.mvp.dagger2.module;

import com.pinzhao.mvp.MainActivity;
import com.pinzhao.mvp.presenter.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/11.
 */
@Module
public class MainActivityModule {
        MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    MainActivityPresenter provideMainActivityPrensenter(){
        return new MainActivityPresenter(activity);
    }
}
