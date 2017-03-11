package com.pinzhao.mvctomvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mUserName;
    private EditText mPassWord;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //用户名
        mUserName = (EditText) findViewById(R.id.username);
        //密码
        mPassWord = (EditText) findViewById(R.id.password);
        dialog = new ProgressDialog(this);
    }
    /**
     * 按钮点击事件
     * */
    public void login(View view) {
            String username=mUserName.getText().toString().trim();
            String password=mPassWord.getText().toString().trim();
        boolean checkUserInfo=checkUserInfo(username,password);
                if (checkUserInfo){
                    dialog.show();
                    final User user=new User();
                    user.username=username;
                    user.password=password;
                    new Thread(){
                        @Override
                        public void run() {
                           UserLogin login=new UserLogin();
                            if (login.sendUserLogin(user)){
                                success();
                            }else {
                                failed();
                            }
                        }
                    }.start();
                } else {
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
    }
        /**
         * 登录失败
         * */
    private void failed() {
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
     * */
    private void success() {
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
     * */
    private boolean checkUserInfo(String username,String password){
        if (username.isEmpty()||password.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}
