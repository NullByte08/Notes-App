package com.example.admin.alertdialogdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

import es.dmoral.prefs.Prefs;

public class CreateNoteActivity extends AppCompatActivity {
    EditText edtTitle;
    EditText edtDescription;
    private TextView btnSave;
    ArrayList<Note> notes=new ArrayList();
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setToolBar("Create Note");

        edtTitle = findViewById(R.id.edtTitle);
        btnSave = findViewById(R.id.btnSave);
        edtDescription = findViewById(R.id.edtDescription);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(edtTitle.getText().toString()))
                {
                    Toast.makeText(CreateNoteActivity.this, "Title is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edtDescription.getText().toString()))
                {
                    Toast.makeText(CreateNoteActivity.this, "Description is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                Note note=new Note();
                note.setTitle(edtTitle.getText().toString());
                note.setNoteDescription(edtDescription.getText().toString());
                note.setDateTime(new Date().toString());

                String json = Prefs.with(getApplicationContext()).read(AppConstant.KEY_PREF);
                if(json!=null&&json!="") {
                    notes = new Gson().fromJson(json, ArrayList.class);
                }

                    notes.add(note);
                    String noteJson = new Gson().toJson(notes,ArrayList.class);
                    Prefs.with(getApplicationContext()).write(AppConstant.KEY_PREF,noteJson);



            }
        });


    }
    public void setToolBar(String titleName) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        toolbar.setNavigationIcon(R.drawable.ic_back);
        setTitle(titleName);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }




}
