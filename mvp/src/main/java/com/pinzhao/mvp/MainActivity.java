package com.pinzhao.mvp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pinzhao.mvp.dagger2.component.DaggerMainActivityComponent;
import com.pinzhao.mvp.dagger2.module.MainActivityModule;
import com.pinzhao.mvp.presenter.MainActivityPresenter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private EditText mUserName;
    private EditText mPassWord;
    private ProgressDialog dialog;
    //第一步
    @Inject
     MainActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //用户名
        mUserName = (EditText) findViewById(R.id.username);
        //密码
        mPassWord = (EditText) findViewById(R.id.password);
        dialog = new ProgressDialog(this);
  //      presenter=new MainActivityPresenter(this);
        DaggerMainActivityComponent component= (DaggerMainActivityComponent) DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule(this)).build();
        component.in(this);
    }

    /**
     * 按钮点击事件
     */
    public void login(View view) {
        String username = mUserName.getText().toString().trim();
        String password = mPassWord.getText().toString().trim();
        boolean checkUserInfo = checkUserInfo(username, password);
        if (checkUserInfo) {
            dialog.show();
            presenter.login(username,password);
        } else {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 登录失败
     */
    public void failed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "登录失败,用户名或密码有误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 登录成功
     */
    public void success() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "欢迎回来", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 检查输入框内容
     */
    private boolean checkUserInfo(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}