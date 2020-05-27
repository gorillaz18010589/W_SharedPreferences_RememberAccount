package com.example.rememberaccount;
//    getSharedPreferences(String name, int mode)//創造取得存放位置(1,檔案名,2.讀寫模式) (回傳SharedPreferences)
//        SharedPreferences.Editor
//        commit()：直接將修改的結果寫入檔案
//        apply()：修改記憶體中的暫存資料，並以非同步式寫入檔案
//        put基本資料型態(key, value)：boolean, float, int, long, String, Set<String>
//                remove(key)
//        clear()
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edit_email,edit_pwd;
    private Button btn_logIn;
    private CheckBox chx_remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        edit_email = findViewById(R.id.edit_email);
        edit_pwd = findViewById(R.id.edit_pwd);
        btn_logIn = findViewById(R.id.btn_login);
        chx_remember = findViewById(R.id.chk_remeber);

        //一開始取得checkbox的記憶儲存
        SharedPreferences sharedPreferences = getSharedPreferences("checkbox",MODE_PRIVATE);

        //將開關的節點位置取得,看是true還是false
        String checkBox = sharedPreferences.getString("remember","");


        //如果紀錄有案true,在初始化時就直接到第二頁
        if(checkBox.equals("true")){
            Intent intent = new Intent(MainActivity.this, WeclcomeActivity.class);
            Log.v("hank","checkbox有含true:" + checkBox);
            startActivity(intent);
        }else{
            Log.v("hank","checkbox有含true:" + checkBox);
            Toast.makeText(this,"請登入",Toast.LENGTH_SHORT);
        }

        //設定登入按鈕
        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeclcomeActivity.class);
                startActivity(intent);
            }
        });

        //開關按鈕
        chx_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //如果開了開關時將,編輯紀錄儲存為true
                if (buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Log.v("hank","true/preferences.getString:" + preferences.getString("remember",""));

                //如果關閉開關,編輯記路儲存為false
                } else if (!buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Log.v("hank","false/preferences.getString:" + preferences.getString("remember",""));
                }
            }
        });
    }
}
