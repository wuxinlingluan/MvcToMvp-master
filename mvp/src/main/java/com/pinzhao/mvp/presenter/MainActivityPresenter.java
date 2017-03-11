package com.pinzhao.mvp.presenter;

import com.pinzhao.mvp.MainActivity;
import com.pinzhao.mvp.User;
import com.pinzhao.mvp.UserLogin;

/**
 * Created by Administrator on 2017/3/11.
 */

public class MainActivityPresenter {
   MainActivity MainActivity;

    public MainActivityPresenter(com.pinzhao.mvp.MainActivity mainActivity) {
        MainActivity = mainActivity;
    }

    public void login(String username, String password){
        final User user=new User();
        user.username=username;
        user.password=password;
        new Thread(){
            @Override
            public void run() {
                UserLogin login=new UserLogin();
                if (login.sendUserLogin(user)){
                    MainActivity.success();
                }else {
                    MainActivity.failed();
                }
            }
        }.start();
    }
}
