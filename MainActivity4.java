package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.model.ChangePassword;
import com.example.myapplication.model.User;
import com.example.myapplication.model.UserInfo;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity4 extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.changepass);
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changepassword();
            }
        });
    }


    public void changepassword() {
        SharedPreferences id = this.getSharedPreferences("loginSavedid", Context.MODE_PRIVATE);
        Integer id1 = id.getInt("id", 0);

        EditText editText = (EditText) findViewById(R.id.editTextTextPassword2);
        String oldPass = editText.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.editTextTextPassword3);
        String newPass = editText2.getText().toString();

        EditText editText3 = (EditText) findViewById(R.id.editTextTextPassword4);
        String confirm_new_pass = editText3.getText().toString();

        if(!newPass.equals(confirm_new_pass)){
            editText3.setError("Password does not match!");
            editText3.requestFocus();
        }
        else {
            if(oldPass.equals(newPass)){
                editText2.setError("You must enter a different password!");
                editText2.requestFocus();
            }
            else {
                if (!newPass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^*!?<>&+=])(?=\\S+$).{8,}$")) {
                    editText2.setError("Password must contain at least 1 upper case letter, 1 lower case leter, 1 digit, 1 special caracter(@,#,$,%,^,*,!,?,<,>,&,+,=), must have at least 8 characters and can't contain 'space' ");
                    editText2.requestFocus();
                }
                else
                {
                    ChangePassword changePassword = new ChangePassword(oldPass,newPass);
                    Call<Boolean> call = RetrofitClient.getInstance().getAPI().changePass(id1,changePassword);
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                            if(response.body().toString().equals(true)) {
                                Toast.makeText(MainActivity4.this, "Password changed successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(MainActivity4.this, "An error has occur", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(MainActivity4.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }
    }

    private void edit(){
        EditText mEdit = (EditText) findViewById(R.id.editTextTextPersonName2);
        mEdit.setCursorVisible(true);
    }
}
