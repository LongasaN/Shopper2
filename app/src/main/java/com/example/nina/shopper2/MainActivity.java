package com.example.nina.shopper2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    DBHandler dbHandler;
    ShoppingLists shoppingListsAdapter;
    ListView shopperListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHandler = new DBHandler(this, null);
        shopperListView = (ListView) this.findViewById(R.id.shopperListView);

        // Initialize ShoppingListAdapter
        // Calling a method to return the Cursor
        shoppingListsAdapter = new ShoppingLists(this, dbHandler.getShoppingList(), 0);

        shopperListView.setAdapter(shoppingListsAdapter);

        // Needs and on Item Click listener to pass called
        shopperListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Start up the ViewList Activity Class
                intent = new Intent(MainActivity.this, ViewList.class);
                intent.putExtra("_id", id);
                startActivity(intent);
            }
        });

    }

    public void openCreateList (View view) {
        intent = new Intent(this, CreateList.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_home :
                intent = new Intent(this, CreateList.class);
                startActivity(intent);
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }
}
