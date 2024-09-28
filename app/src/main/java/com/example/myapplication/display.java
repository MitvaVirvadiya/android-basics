package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class display extends AppCompatActivity {
    TextView txt_finalResult;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        try {
            txt_finalResult = findViewById(R.id.txt_finalResult);
            sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);
            if (sharedPreferences.contains("email") || sharedPreferences.contains("aadhar") || sharedPreferences.contains("skill")) {
                String email = sharedPreferences.getString("email", "");
                int aadhar = sharedPreferences.getInt("aadhar", 0);
                String skill = sharedPreferences.getString("skill", "");
                String res = "Email: " + email + "\n" + "Aadhar: " + aadhar + "\n" + "Skill: " + skill;
                txt_finalResult.setText(res);
            }
        }catch(Exception e){
            Log.e("Display error",e.getMessage());
        }
    }
    public void fn_signout(View view) {
        editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.op_skill){
            Toast.makeText(this,"Skill is clicked!!",Toast.LENGTH_LONG).show();
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            Intent intent = new Intent(this, MainActivity3.class);
            startActivity(intent);
        } else if (id==R.id.op_about) {
            Intent intent = new Intent(this, about_srki.class);
            startActivity(intent);
        } else if (id==R.id.op_logout) {
            editor=sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent i = new Intent(this,Login.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}