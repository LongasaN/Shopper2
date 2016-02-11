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
import android.widget.Toast;

public class AddItem extends AppCompatActivity {

    Bundle bundle;
    long id;
    Intent intent;
    EditText nameEditText;
    EditText priceEditText;
    EditText quantityEditText;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = this.getIntent().getExtras();
        id = bundle.getLong("_id");

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        priceEditText = (EditText) findViewById(R.id.priceEditText);
        quantityEditText = (EditText) findViewById(R.id.quantityEditText);

        dbHandler = new DBHandler(this, null);

    }

    public void addItem(MenuItem menuItem){

        String name = nameEditText.getText().toString();
        String price = priceEditText.getText().toString();
        String quantity = quantityEditText.getText().toString();

        if (name.trim().equals("") || price.trim().equals("") || quantity.trim().equals("")){
            Toast.makeText(this, "Please enter a name, price and quantity!", Toast.LENGTH_LONG).show();
        } else {
            dbHandler.addItemToShoppingList(name, Double.parseDouble(price), Integer.parseInt(quantity), (int)id);
            Toast.makeText(this, "Item added!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_home :
                intent = new Intent(this, CreateList.class);
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
