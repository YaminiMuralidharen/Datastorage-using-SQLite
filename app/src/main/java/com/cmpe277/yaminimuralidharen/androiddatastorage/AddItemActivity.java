package com.cmpe277.yaminimuralidharen.androiddatastorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {
private EditText item_name_edit, item_desc_edit, item_price_edit,  item_review_edit;
private Button addButton, cancelButton;
private String item_name,item_desc, item_price,  item_review;
private ProductDBHelper productDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        item_name_edit=(EditText)findViewById(R.id.item_name_edit);
        item_desc_edit=(EditText)findViewById(R.id.item_desc_edit);
        item_price_edit=(EditText)findViewById(R.id.item_price_edit);
        item_review_edit=(EditText)findViewById(R.id.item_review_edit);
        addButton=(Button)findViewById(R.id.addButton);
        cancelButton=(Button)findViewById(R.id.cancelButton);
        productDB = new ProductDBHelper(this);
        productDB.openDatabase();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(item_name_edit.getText().toString() != null && !item_name_edit.getText().toString().isEmpty() &&  item_desc_edit.getText().toString() != null && ! item_desc_edit.getText().toString().isEmpty() &&  item_price_edit.getText().toString() != null && ! item_price_edit.getText().toString().isEmpty() &&  item_review_edit.getText().toString() != null && ! item_review_edit.getText().toString().isEmpty())

                {
                    item_name = item_name_edit.getText().toString();
                    item_desc = item_desc_edit.getText().toString();
                    item_price = item_price_edit.getText().toString();
                    item_review = item_review_edit.getText().toString();
                    productDB.addProduct(item_name,item_desc,item_price,item_review);
                    Toast.makeText(AddItemActivity.this, "Item added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(AddItemActivity.this, "Please complete all the fields", Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        productDB.closeDatabase();
    }
}


