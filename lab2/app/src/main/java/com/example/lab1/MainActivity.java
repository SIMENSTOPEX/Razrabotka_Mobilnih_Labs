package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.nio.Buffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    Button buttonSignUp;
    EditText editTextTextEmailAddress3;
    EditText editTextTextPassword3;
    public static ArrayList<String> LoginPassword= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        buttonSignUp = (Button) findViewById(R.id.button2);
        buttonSignUp.setOnClickListener(this);
        editTextTextEmailAddress3 = (EditText) findViewById(R.id.editTextTextEmailAddress3);
        editTextTextPassword3 = (EditText) findViewById(R.id.editTextTextPassword3);
        LoginPassword.add(convertPassMd5("admin"+"adminpass"));
        LoginPassword.add(getIntent().getStringExtra("LoginPassword"));
    }

    public static String convertPassMd5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            StringBuilder passBuilder = new StringBuilder(new BigInteger(1, mdEnc.digest()).toString(16));
            while (passBuilder.length() < 32) {
                passBuilder.insert(0, "0");
            }
            pass = passBuilder.toString();
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return password;
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button:
                if ((editTextTextEmailAddress3.getText().length() > 0) && (editTextTextPassword3.getText().length() > 0)) {
                    String buffer = convertPassMd5(editTextTextEmailAddress3.getText().toString() + editTextTextPassword3.getText().toString());
                    int c = 0;
                    for (int i = 0; i<LoginPassword.size(); i++) {
                        if (buffer.equals(LoginPassword.get(i))) c++;
                    }
                    if (c==1){
                        Intent success = new Intent(MainActivity.this, Success.class);
                        startActivity(success);

                    } else {
                        Intent fail = new Intent(MainActivity.this, Fail.class);
                        startActivity(fail);
                    }
                }
                break;
            case R.id.button2:
                Intent create = new Intent(MainActivity.this, SignUp.class);
                startActivity(create);
                break;
        }
    }
}

