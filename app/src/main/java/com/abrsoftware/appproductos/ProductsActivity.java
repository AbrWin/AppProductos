package com.abrsoftware.appproductos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.abrsoftware.appproductos.view.ProductsFragment;

public class ProductsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Fragment mProductsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mProductsFragment = getSupportFragmentManager().findFragmentById(R.id.products_container);

        setSupportActionBar(toolbar);
        setUpProductsFragment();
    }

    private void setUpProductsFragment() {
        if (mProductsFragment == null) {
            mProductsFragment = ProductsFragment.newInstance(null, null);
            getSupportFragmentManager().beginTransaction().add(R.id.products_container, mProductsFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_products, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
