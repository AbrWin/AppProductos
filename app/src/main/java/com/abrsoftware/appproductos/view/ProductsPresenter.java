package com.abrsoftware.appproductos.view;

import com.abrsoftware.appproductos.data.products.ProductsRepository;

/**
 * Created by AbrWin on 06/10/16.
 */

public class ProductsPresenter implements ProductsMvp.Presenter {

    private ProductsRepository mProductsRepository;
    private ProductsMvp.View mProductView;

    public ProductsPresenter() {

    }

    @Override
    public void loadProducts(boolean reload) {

    }
}
