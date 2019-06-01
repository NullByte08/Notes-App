package com.example.admin.alertdialogdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.english:textView.setText("Hello World!");
                break;
            case R.id.Hindi:textView.setText("नमस्ते दुनिया!");
                break;
            case R.id.japanese:textView.setText("こんにちは世界!");
                break;
            case R.id.spanish:textView.setText("Hola Mundo!");
                break;
            default:break;
        }
        sharedPreferences.edit().putString("tview", textView.getText().toString()).apply();

        return true;
    }
    public void setLang(int n){

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
        sharedPreferences.edit().putString("tview", textView.getText().toString()).apply();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences=this.getSharedPreferences("com.example.admin.alertdialogdemo", Context.MODE_PRIVATE);
        textView=findViewById(R.id.hello);


        textView.setText(sharedPreferences.getString("tview","default value"));

        if(textView.getText().toString().equals("default value")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Select Language")
                    .setItems(new CharSequence[]{"English", "Hindi", "Japanese", "Spanish"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLang(i);
                        }
                    })
                    .show();
        }

    }
}
