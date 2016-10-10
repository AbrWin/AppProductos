package com.abrsoftware.appproductos.data.products;

import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.List;

/**
 * Created by AbrWin on 10/10/16.
 */

public interface IProductsRepository {
    interface GetProductsCallback{
        void onProductsLoaded(List<Product> products);
        void onDataNotAvailable(String error);
    }

    void getProducts(GetProductsCallback callback);

    void refreshProducts();

}
