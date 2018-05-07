package com.cmpe277.yaminimuralidharen.androiddatastorage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button insertButton, searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertButton=(Button)findViewById(R.id.insert_button);
        searchButton=(Button)findViewById(R.id.search_button);

    }

    public void insertItem(View view) {
        Intent intent= new Intent(this, AddItemActivity.class);
        startActivity(intent);

    }


    public void searchItem(View view) {
        Intent intent= new Intent(this, SearchActivity.class);
        startActivity(intent);

    }
}
