package com.pinzhao.mvctomvp;

import android.os.SystemClock;

/**
 * Created by ${sheldon} on 2017/3/7.
 */
public class UserLogin {


    /**
     * 模拟发送用户数据
     * */
    public boolean sendUserLogin(User user){
        SystemClock.sleep(2000);
        if("pinzhao".equals(user.username)&&"jn".equals(user.password)){
            return true;//登录成功
        }else {
            return false;//登录失败
        }
    }
}
