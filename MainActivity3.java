package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.model.User;
import com.example.myapplication.model.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity3 extends Activity {
    EditText username;
    UserInfo user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = this.getSharedPreferences("loginSaved", Context.MODE_PRIVATE);
        String username = sp.getString("username", null);

        Call<UserInfo> call = RetrofitClient.getInstance().getAPI().getUserById(username);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                String s = "";
                 user = response.body();
                SharedPreferences prefs = getSharedPreferences("loginSavedid", 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("id", user.getId());
                editor.commit();

                EditText editText = (EditText) findViewById(R.id.editTextTextPersonName2);
                //editText.setText("This sets the text.", TextView.BufferType.EDITABLE);
                editText.setText(user.getUsername());
                // (EditText)findViewById(R.id.editTextTextPassword2) =user.getUsername();
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Toast.makeText(MainActivity3.this, "he!", Toast.LENGTH_LONG).show();

                Toast.makeText(MainActivity3.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        setContentView(R.layout.edit_account);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        findViewById(R.id.editTextTextPersonName2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changepassword();
            }
        });

    }

    private void edit(){
        EditText mEdit = (EditText) findViewById(R.id.editTextTextPersonName2);
        mEdit.setCursorVisible(true);
    }
    private void save() {
        SharedPreferences id = this.getSharedPreferences("loginSavedid", Context.MODE_PRIVATE);
        Integer id1 = id.getInt("id", 0);

        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName2);
        String username2 = editText.getText().toString();

        UserInfo userInfo = new UserInfo(id1,username2,user.getPassword());

        Call<UserInfo> call = RetrofitClient.getInstance().getAPI().updateuser(id1,userInfo);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                String s = "";
                UserInfo user = response.body();
                Toast.makeText(MainActivity3.this, "MINUNAAAAAAAT!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Toast.makeText(MainActivity3.this, "he!", Toast.LENGTH_LONG).show();

                Toast.makeText(MainActivity3.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        EditText mEdit = (EditText) findViewById(R.id.editTextTextPersonName2);
        mEdit.setCursorVisible(false);
    }


    public void changepassword() {
        Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
        startActivity(intent);
    }
}
