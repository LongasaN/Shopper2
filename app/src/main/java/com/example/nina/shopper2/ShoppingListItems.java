package com.example.nina.shopper2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by 3a on 2/11/2016.
 */
public class ShoppingListItems extends CursorAdapter {

    public ShoppingListItems(Context context, Cursor cursor, int flags){
        super (context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.li_item_list, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(R.id.nameTextView)).
                setText(cursor.getString(cursor.getColumnIndex("item_name")));
        ((TextView) view.findViewById(R.id.priceTextView)).
                setText(cursor.getString(cursor.getColumnIndex("item_price")));
        ((TextView) view.findViewById(R.id.quantityTextView)).
                setText(cursor.getString(cursor.getColumnIndex("item_quantity")));
        ((TextView) view.findViewById(R.id.hasTextView)).
                setText(cursor.getString(cursor.getColumnIndex("item_has")));
    }
}
