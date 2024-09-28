package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    public static int index;
    EditText edt_input;
    ListView lv_skills;
    ArrayAdapter<String> adp_skills;
    ArrayList<String> skills=new ArrayList<>();
    String skill;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView txt_result;
    Button btn_popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        try {
            edt_input = findViewById(R.id.edt_input);
            lv_skills = findViewById(R.id.lv_skills);
            btn_popup = findViewById(R.id.btn_popup);
            adp_skills = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,skills);
            lv_skills.setAdapter(adp_skills);
            txt_result=findViewById(R.id.txt_result);
            sharedPreferences=getSharedPreferences("credentials",MODE_PRIVATE);
            if(sharedPreferences.contains("email")&&sharedPreferences.contains("aadhar")&&sharedPreferences.contains("password")){
                String email=sharedPreferences.getString("email","");
                int aadhar=sharedPreferences.getInt("aadhar",0);
                String res="Email: "+email+"\n"+"Aadhar: "+aadhar;
                txt_result.setText(res);
            }
            //For editing
            lv_skills.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    index = position;
                    edt_input.setText(skills.get(index));
                    for (int i = 0; i < lv_skills.getChildCount(); i++) {
                        if(position == i ){
                            lv_skills.getChildAt(i).setBackgroundColor(Color.GREEN);
                        }else{
                            lv_skills.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                    }
//                    lv_skills.setSelector(R.color.green);
                    CallToast("Selected: " + adp_skills.getItem(position));
                }
            });
            //For deleting
            lv_skills.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                    skills.remove(position);
//                    adp_skills.notifyDataSetChanged();
//                    CallToast("Deleted");
//                    edt_input.setText("");
                    registerForContextMenu(view);
                    return false;
                }

            });
        }catch (Exception e){
            Log.e("Error Generated",e.getMessage());
        }
    }
    public void fn_add(View view){
        skill=edt_input.getText().toString();
        if(skill.isEmpty()){
            CallToast("Enter skill!!");
        }else{
            skills.add(skill);
            adp_skills.notifyDataSetChanged();
            CallToast("Added: "+skill);
            edt_input.setText("");
        }
    }
    public void fn_edit(View view){
        skill=edt_input.getText().toString();
        if(skill.isEmpty()){
            CallToast("Enter skill!!");
        }else{
            skills.set(index,skill);
            adp_skills.notifyDataSetChanged();
            CallToast("Edited: "+skill);
            edt_input.setText("");
        }
    }
    public void CallToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    public void fn_submit2(View view) {
        String skill =edt_input.getText().toString();
        if(!skill.isEmpty()){
            editor=sharedPreferences.edit();
            editor.putString("skill",skill);
            editor.commit();
            Intent i = new Intent(this,display.class);
            startActivity(i);
        }
    }
    public void fn_delete() {
        skills.remove(index);
        adp_skills.notifyDataSetChanged();
        CallToast("Deleted");
        edt_input.setText("");
    }

    public void fn_popup(View view) {
        PopupMenu popup = new PopupMenu(this,btn_popup);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.p_add){
                    fn_add(null);
                }
                if(id==R.id.p_edit) {
                    fn_edit(null);
                }
                if(id==R.id.p_delete){
                    fn_delete();
                }
                return true;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.ct_delete){
            fn_delete();
        }
        if(id==R.id.ct_edit) {
            fn_edit(null);
        }
        return super.onContextItemSelected(item);
    }
}