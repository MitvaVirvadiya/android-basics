package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView output;
    Button navigateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        try {
            output = findViewById(R.id.txt_output2);
            navigateButton = findViewById(R.id.button); // The Button in your XML

            navigateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to navigate to LoginActivity
                    Intent intent = new Intent(MainActivity2.this, Login.class);
                    startActivity(intent); // Start LoginActivity
                }
            });

            String name, email, gender, activity, qualification, department = "";
            int phone, aadhar = 0;
            String res="";
            Intent iExp = new Intent();
            iExp = getIntent();
            if (iExp != null) {
                if (iExp.hasExtra("name")) {
                    name = iExp.getStringExtra("name");
                    res+="Name: "+name+"\n";
                }
                if (iExp.hasExtra("phone")) {
                    phone = iExp.getIntExtra("phone", 0);
                    res+="Phone: "+phone+"\n";
                }
                if (iExp.hasExtra("email")) {
                    email = iExp.getStringExtra("email");
                    res+="Email: "+email+"\n";
                }
                if (iExp.hasExtra("aadhar")) {
                    aadhar = iExp.getIntExtra("aadhar", 0);
                    res+="Aadhar : "+aadhar+"\n";
                }
                if (iExp.hasExtra("gender")) {
                    gender = iExp.getStringExtra("gender");
                    res+="Gender : "+gender+"\n";
                }
                if (iExp.hasExtra("activity")) {
                    activity = iExp.getStringExtra("activity");
                    res+="Activity : "+activity+"\n";
                }
                if (iExp.hasExtra("qualification")) {
                    qualification = iExp.getStringExtra("qualification");
                    res+="Qualifications : "+qualification+"\n";
                }
                if (iExp.hasExtra("department")) {
                    department = iExp.getStringExtra("department");
                    res+="Department : "+department;
                }
            } else {
                Toast.makeText(this, "No Data Found!!", Toast.LENGTH_LONG);
            }
            output.setText(res);
        }
        catch (Exception e){
            Log.e("Error Generated",e.getMessage());
        }
    }
}