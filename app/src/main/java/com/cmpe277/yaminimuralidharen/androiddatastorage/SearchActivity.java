package com.cmpe277.yaminimuralidharen.androiddatastorage;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    private Button searchButton, cancelButton;
    private EditText item_name;
    private ProductDBHelper productDB;
    private TextView displayResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        item_name=(EditText)findViewById(R.id.item_name_search_edit);
        searchButton=(Button)findViewById(R.id.search_item_button);
        cancelButton=(Button)findViewById(R.id.cancel_search_Button);
        displayResult=(TextView)findViewById(R.id.result_text);
        productDB = new ProductDBHelper(this);
        productDB.openDatabase();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item_name.getText().toString() != null && !item_name.getText().toString().isEmpty()) {
                    Cursor cursor = productDB.getProductbyName(item_name.getText().toString());
                    Log.d("cursor count", " " + cursor.getCount());
                    if (cursor.getCount() > 0) {
                        //      cursor.moveToFirst();

                        displayResult.setText("Search results");
                        try {
                            while (cursor.moveToNext()) {
                                displayResult.append("\n Item Name: " + cursor.getString(cursor.getColumnIndex(ProductDBHelper.ITEM_NAME)) + "\n" +
                                        "Item Price: " + cursor.getString(cursor.getColumnIndex(ProductDBHelper.ITEM_PRICE)) + "\n" +
                                        "Item Description: " + cursor.getString(cursor.getColumnIndex(ProductDBHelper.ITEM_DESC)) + "\n" +
                                        "Item Review: " + cursor.getString(cursor.getColumnIndex(ProductDBHelper.ITEM_REVIEW)) + "\n"
                                );
                            }
                        } catch (CursorIndexOutOfBoundsException e) {
                            e.printStackTrace();
                        } finally {
                            cursor.close();
                        }


                        //  cursor.close();
                        //  productDB.closeDatabase();
                        // Log.d(cu)
                    } else {
                        displayResult.setText("Item not found");
                    }
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("");
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        productDB.closeDatabase();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
