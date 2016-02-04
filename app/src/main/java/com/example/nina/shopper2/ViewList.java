package com.example.nina.shopper2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class ViewList extends AppCompatActivity {

    Bundle bundle;
    long id;
    DBHandler dbHandler;
    String shoppingListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = this.getIntent().getExtras();
        id = bundle.getLong("_id");

            // Open dbHandler
        dbHandler = new DBHandler(this, null);

        shoppingListName = dbHandler.getShoppingListName((int) id);
        this.setTitle(shoppingListName);

    }

    public void openAddItem(View view){

    }

    public void deleteShoppingList(MenuItem menuItem){

    }

}
