package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Login extends AppCompatActivity {
    EditText edt_email;
    EditText edt_aadhar;
    EditText edt_password;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edt_email=findViewById(R.id.edt_email);
        edt_aadhar=findViewById(R.id.edt_aadhar);
        edt_password=findViewById(R.id.edt_password);
        sharedPreferences=getSharedPreferences("credentials",MODE_PRIVATE);
    }

    public void fn_login(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",edt_email.getText().toString());
        editor.putInt("aadhar",Integer.parseInt(edt_aadhar.getText().toString()));
        editor.putString("password",edt_password.getText().toString());
        editor.commit();
        Intent i = new Intent(this,MainActivity3.class);
        startActivity(i);
    }
}