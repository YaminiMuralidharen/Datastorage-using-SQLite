package com.cmpe277.yaminimuralidharen.androiddatastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yaminimuralidharen on 3/13/18.
 */

public class ProductDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="prod.db";
    public static final int DB_VERSION=3;
    public static final String TABLE="products_tbl";
    public static final String ID="_id";
    public static final String ITEM_NAME="_item_name";
    public static final String ITEM_DESC="_item_desc";
    public static final String ITEM_PRICE="_item_price";
    public static final String ITEM_REVIEW="_item_review";
    private String[] ALL_COLUMNS= new String[] {ID, ITEM_NAME, ITEM_DESC,ITEM_PRICE,ITEM_REVIEW};
    private static final String CREATE_TABLE="CREATE TABLE "+ TABLE +
            " ("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ ITEM_NAME +" TEXT NOT NULL, " + ITEM_DESC + " TEXT NOT NULL, " +
             ITEM_PRICE +" TEXT NOT NULL, " + ITEM_REVIEW + " TEXT NOT NULL)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE;
    private SQLiteDatabase db;

    public ProductDBHelper(Context context) {
    super(context,DB_NAME,null,1);
    }

    public void openDatabase() throws SQLException {
        db = this.getWritableDatabase();

    }

    public void closeDatabase() {
        db.close();
      //  db = null;
    }

    public void addProduct(String item_name, String item_desc, String item_price, String item_review) {
       // openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_NAME,item_name);
        contentValues.put(ITEM_DESC,item_desc);
        contentValues.put(ITEM_PRICE,item_price);
        contentValues.put(ITEM_REVIEW,item_review);
        db.insert(TABLE,null,contentValues);
    }

    public Cursor getProductbyName(String itemName) {


        Cursor c = db.query(TABLE,ALL_COLUMNS,ITEM_NAME + " LIKE ?" , new String[]{ "%" + itemName+ "%"},null, null, null,
                null);


        return c;
    }

    public Cursor getItemByName(String item_name) {
        String qry = "SELECT * FROM " + TABLE + " WHERE " + ITEM_NAME + " = ?";
        Cursor c = db.rawQuery(qry, new String[] {  item_name});
        return c;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old_version_no, int new_version_no) {
         if(new_version_no > old_version_no) {
              db.execSQL(DROP_TABLE);
              Log.d("on upgrade","Table dropped");
              onCreate(db);
         }
    }
}
