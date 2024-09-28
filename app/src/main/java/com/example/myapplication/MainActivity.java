package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText phone;
    EditText email;
    EditText aadhar;
    RadioGroup rdg_gender;
    RadioButton rdb_gender;
    CheckBox ncc,nss,sports;
    Spinner qualification;
    Spinner department;
    Button submit;
    Button clear;
    TextView output;

    ArrayAdapter<String> adpQualification;
    ArrayAdapter<String> adpDepartment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name = findViewById(R.id.edt_name);
        phone = findViewById(R.id.edt_phone);
        email = findViewById(R.id.edt_email);
        aadhar = findViewById(R.id.edt_aadhar);
        rdg_gender = findViewById(R.id.rdg_gender);
        ncc = findViewById(R.id.chk_ncc);
        nss = findViewById(R.id.chk_nss);
        sports = findViewById(R.id.chk_sports);
        qualification = findViewById(R.id.ddl_qualification);
        department = findViewById(R.id.ddl_department);
        String arrQualification[]= {"12th","Bachelor","Master","PHD"};
        adpQualification = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,arrQualification);
        qualification.setAdapter(adpQualification);
        String arrDepartment[]= {"IT","CS","MB","BT","ES"};
        adpDepartment = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,arrDepartment);
        department.setAdapter(adpDepartment);
        submit = findViewById(R.id.btn_submit);
        clear = findViewById(R.id.btn_clear);
        output = findViewById(R.id.txt_output);
    }

    public void fn_submit(View view){
        try {
            String edt_name = name.getText().toString();
            int edt_phone = Integer.parseInt(phone.getText().toString());
            String edt_email = email.getText().toString();
            int edt_aadhar = Integer.parseInt(aadhar.getText().toString());
            int selectedId = rdg_gender.getCheckedRadioButtonId();
            rdb_gender = findViewById(selectedId);
            String gender = rdb_gender.getText().toString();
            String activities = "";
            if(ncc.isChecked()){
                activities+=ncc.getText().toString() +" ";
            }
            if(nss.isChecked()){
                activities+=nss.getText().toString() +" ";
            }
            if(sports.isChecked()){
                activities+=sports.getText().toString() +" ";
            }
            String ddl_qualification = qualification.getSelectedItem().toString();
            String ddl_department = department.getSelectedItem().toString();

            String res = "Name: "+edt_name+"\n"+"Phone no.: "+edt_phone+"\n"
                    +"Email: "+edt_email+"\n"+"Aadhar no.: "+edt_aadhar+"\n"
                    +"Gender: "+gender+"\n"+"Activity: "+activities+"\n"
                    +"Qualification: "+ddl_qualification+"\n"
                    +"Department: "+ddl_department;
            output.setText(res);

            //Assignment 3 Code
            Intent iExp = new Intent(this, MainActivity2.class);
            //Add data as query string in intent
            iExp.putExtra("name",edt_name);
            iExp.putExtra("phone",edt_phone);
            iExp.putExtra("email",edt_email);
            iExp.putExtra("aadhar",edt_aadhar);
            iExp.putExtra("gender",gender);
            iExp.putExtra("activity",activities);
            iExp.putExtra("qualification",ddl_qualification);
            iExp.putExtra("department",ddl_department);
            Log.d("Integer",String.valueOf(edt_aadhar));
            startActivity(iExp);
        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}