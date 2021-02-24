package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.model.User;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;


public class MainActivity2 extends AppCompatActivity {

    Button login;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   SharedPreferences prefs = this.getSharedPreferences("user", 0);
//        saved= (prefs.getString("loginSaved", false));
        setContentView(R.layout.log_in);

        login = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.editTextTextPersonName);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

        public void login (){
        if(username.getText().toString().isEmpty()){
            username.setError("Username is required");
            username.requestFocus();
            return;
        } else if (password.getText().toString().isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

            Log.d("MyApp",username.getText().toString());
        String username1 = username.getText().toString();
        String password1 = password.getText().toString();
        User user = new User(username1,password1);
        Log.d("CVVVVVVVVVVV", user.getUsername());


        Call<String> call = RetrofitClient.getInstance().getAPI().verifyuser(user);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String s = "";

                  s = response.body().toString();
                  Log.d("dfbdszvsdgdsMM",s);
                    if (response.body().toString().equals("true")) {
                        Toast.makeText(MainActivity2.this, "User logged in!", Toast.LENGTH_LONG).show();
                        SharedPreferences prefs = getSharedPreferences("loginSaved", 0);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("username", username1);
                        editor.commit();
                        Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                        startActivity(intent);
                       // setContentView(R.layout.edit_account);
                         } else {
                        Toast.makeText(MainActivity2.this, "Incorrect Credentials! Try again!", Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(MainActivity2.this, "he!", Toast.LENGTH_LONG).show();

                    Toast.makeText(MainActivity2.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }

    }

