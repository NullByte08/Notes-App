package com.example.admin.alertdialogdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.prefs.Prefs;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private NoteListAdapter adapter;

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.english:
                textView.setText("Hello World!");
                break;
            case R.id.Hindi:
                textView.setText("नमस्ते दुनिया!");
                break;
            case R.id.japanese:
                textView.setText("こんにちは世界!");
                break;
            case R.id.spanish:
                textView.setText("Hola Mundo!");
                break;
            default:
                break;
        }
        sharedPreferences.edit().putString("tview", textView.getText().toString()).apply();

        return true;
    }

    public void setLang(int n) {

        switch (n) {
            case 0:
                textView.setText("Hello World!");
                break;
            case 1:
                textView.setText("नमस्ते दुनिया!");
                break;
            case 2:
                textView.setText("こんにちは世界!");
                break;
            case 3:
                textView.setText("Hola Mundo!");
                break;
            default:
                Toast.makeText(MainActivity.this, "Press a Button! ", Toast.LENGTH_SHORT).show();
        }
//        sharedPreferences.edit().putString("tview", textView.getText().toString()).apply();

    }

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_recycle_view);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
setToolBar("Note List");
        adapter = new NoteListAdapter(getApplicationContext(), new ArrayList<Note>());
        bindLinearLayoutManagers(recycleView, true);
        recycleView.setAdapter(adapter);
        fetchData();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CreateNoteActivity.class));
            }
        });


//        sharedPreferences = this.getSharedPreferences("com.example.admin.alertdialogdemo", Context.MODE_PRIVATE);
//        textView=findViewById(R.id.hello);


//        textView.setText(sharedPreferences.getString("tview","default value"));

//        if (textView.getText().toString().equals("default value")) {
//            new AlertDialog.Builder(this)
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .setTitle("Select Language")
//                    .setItems(new CharSequence[]{"English", "Hindi", "Japanese", "Spanish"}, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            setLang(i);
//                        }
//                    })
//                    .show();
//        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    private void fetchData() {
        String json = Prefs.with(getApplicationContext()).read(AppConstant.KEY_PREF);
        if(json!=null&&json!="") {
            ArrayList items = new Gson().fromJson(json,new TypeToken<ArrayList<Note>>(){}.getType());
            adapter.clear();
            adapter.add(items);
        }
    }

    public void setToolBar(String titleName) {

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
    private void bindLinearLayoutManagers(RecyclerView myRecyclerView, boolean isVerticalOrientation) {
        myRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), isVerticalOrientation == true ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL, false);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
