package com.pinzhao.mvp.dagger2.component;

import com.pinzhao.mvp.MainActivity;
import com.pinzhao.mvp.dagger2.module.MainActivityModule;

import dagger.Component;

/**
 * 建立module 和component之间的关系
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void in(MainActivity activity);
}
