package com.abrsoftware.appproductos.data.products;

import android.content.Context;

import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.List;

/**
 * Repositorio de productos
 */

public class ProductsRepository implements IProductsRepository {
    //private final IMemoryProductsDataSource mMemoryProductsDataSource;
    //private final ICloudProductsDataSource mCloudProductsDataSource;
    private final Context mContext;

    public ProductsRepository(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getProducts(GetProductsCallback callback) {

    }

    @Override
    public void refreshProducts() {

    }
}
