package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    EditText editTextTextEmailAddress3;
    EditText editTextTextPassword3;
    public String login = "admin";
    public String password = "adminpass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        editTextTextEmailAddress3 = (EditText) findViewById(R.id.editTextTextEmailAddress3);
        editTextTextPassword3 = (EditText) findViewById(R.id.editTextTextPassword3);
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
        if ((editTextTextEmailAddress3.getText().length() > 0) && (editTextTextPassword3.getText().length() > 0)){

            if (login.equals(editTextTextEmailAddress3.getText().toString()) & convertPassMd5(password).equals(convertPassMd5(editTextTextPassword3.getText().toString()))) {
                Intent success = new Intent(MainActivity.this, Success.class);
                startActivity(success);
            } else {
                Intent fail = new Intent(MainActivity.this, Fail.class);
                startActivity(fail);
            }
        }
    }
}

