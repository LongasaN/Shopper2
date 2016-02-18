package com.example.nina.shopper2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ViewItem extends AppCompatActivity {

    Intent intent;
    Bundle bundle;
    long id;
    DBHandler dbHandler;
    EditText nameEditText;
    EditText priceEditText;
    EditText quantityEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = this.getIntent().getExtras();
        id = bundle.getLong("_id");

        dbHandler = new DBHandler(this, null);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        priceEditText = (EditText) findViewById(R.id.priceEditText);
        quantityEditText = (EditText) findViewById(R.id.quantityEditText);

        ShoppingListItem shoppingListItem = dbHandler.getShoppingListItem((int)id);

        nameEditText.setText(shoppingListItem.getName().toString());
        priceEditText.setText(shoppingListItem.getPrice().toString());
        quantityEditText.setText(shoppingListItem.getQuantity().toString());

    }


    public void deleteShoppingListItem(MenuItem menuItem){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_home :
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_create_list :
                intent = new Intent(this, CreateList.class);
                startActivity(intent);
                return true;
            case R.id.action_add_item :
                intent = new Intent(this,AddItem.class);
                intent.putExtra("_id", id);
                startActivity(intent);
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }

}
