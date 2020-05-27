package com.example.rememberaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class WeclcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weclcome);
    }

    //按下登出按鈕finssh結束掉,並且將記錄純存為false
    public void btn_logout(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("remember","false");
        editor.apply();
        Log.v("hank","登出 checkbox:" + sharedPreferences.getString("remember",""));
        finish();
    }
}
