package com.example.nina.shopper2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nina on 1/28/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "shopper.db";

    public static final String TABLE_SHOPPING_LIST = "shoppinglist";
    public static final String COLUMN_LIST_ID = "_id";
    public static final String COLUMN_LIST_NAME = "list_name";
    public static final String COLUMN_LIST_STORE = "list_store";
    public static final String COLUMN_LIST_DATE = "list_date";

    public static final String TABLE_SHOPPING_LIST_ITEM = "shoppinglistitem";
    public static final String COLUMN_ITEM_ID = "_id";
    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_ITEM_PRICE = "item_price";
    public static final String COLUMN_ITEM_QUANTITY = "item_quantity";
    public static final String COLUMN_ITEM_HAS = "item_has";
    public static final String COLUMN_ITEM_LIST_ID = "item_list_id";


    public DBHandler (Context context, SQLiteDatabase.CursorFactory factory){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_SHOPPING_LIST + "(" +
                COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_LIST_NAME + " TEXT," +
                COLUMN_LIST_STORE + " TEXT," +
                COLUMN_LIST_DATE + " TEXT" +
                ");";
        db.execSQL(query);

        String query2 = "CREATE TABLE " + TABLE_SHOPPING_LIST_ITEM + "(" +
                COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ITEM_NAME + " TEXT," +
                COLUMN_ITEM_PRICE + " DECIMAL(10,2)," +
                COLUMN_ITEM_QUANTITY + " INTEGER," +
                COLUMN_ITEM_HAS + " TEXT," +
                COLUMN_ITEM_LIST_ID + " INTEGER" +
                ");";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING_LIST_ITEM);
        onCreate(db);
    }

    public void addShoppingList(String name, String store, String date){

        ContentValues values = new ContentValues();

        values.put(COLUMN_LIST_NAME, name);
        values.put(COLUMN_LIST_STORE, store);
        values.put(COLUMN_LIST_DATE, date);

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_SHOPPING_LIST, null, values);

        db.close();
    }

    public Cursor getShoppingList(){

        // Select everything from the Shopping List Database
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SHOPPING_LIST, null);
    }

    public String getShoppingListName (int id){
        String dbString = "";

        String query = "SELECT * FROM " + TABLE_SHOPPING_LIST+
                " WHERE " + COLUMN_LIST_ID + " = " + id;

        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        int numShoppingLists = c.getCount();

        if (numShoppingLists >= 1){
            c.moveToFirst();
            if ((c.getString(c.getColumnIndex("list_name")) != null)){
                dbString = c.getString(c.getColumnIndex("list_name"));
                c.moveToNext();
            }
        }

        db.close();
        return dbString;
    }

    public void addItemToShoppingList (String name, Double price, Integer quantity, Integer listId){

        ContentValues values = new ContentValues();

        values.put(COLUMN_ITEM_NAME, name);
        values.put(COLUMN_ITEM_PRICE, price);
        values.put(COLUMN_ITEM_QUANTITY, quantity);
        values.put(COLUMN_ITEM_HAS, "false");
        values.put(COLUMN_ITEM_LIST_ID, listId);

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_SHOPPING_LIST_ITEM, null, values);

        db.close();
    }

    public Cursor getShoppingListItems(Integer listId){

        SQLiteDatabase db = getWritableDatabase();

        return db.rawQuery("SELECT * FROM " + TABLE_SHOPPING_LIST_ITEM +
        " WHERE " + COLUMN_ITEM_LIST_ID + " = " + listId, null);

    }

    public String getShoppingListTotalCost(int listId){

        String dbString = "";

        String query = "SELECT * FROM " + TABLE_SHOPPING_LIST_ITEM +
                " WHERE " + COLUMN_ITEM_LIST_ID + " = " + listId;

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        Double totalCost = 0.0;
        int numShoppingListItems = c.getCount();

        if (numShoppingListItems >= 1){
            c.moveToFirst();

            while (!c.isAfterLast()){
                totalCost += (c.getDouble(c.getColumnIndex("item_price")) *
                        (c.getInt(c.getColumnIndex("item_quantity"))));
                c.moveToNext();
            }
        }

        dbString = String.valueOf(totalCost);
        db.close();

        return dbString;
    }
}
