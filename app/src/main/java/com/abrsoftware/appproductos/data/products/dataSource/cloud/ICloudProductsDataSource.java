package com.abrsoftware.appproductos.data.products.dataSource.cloud;

import com.abrsoftware.appproductos.products.domain.criteria.ProductCriteria;
import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.List;

/**
 * Created by AbrWin on 10/10/16.
 */

public interface ICloudProductsDataSource {
    interface ProductServiceCallback {

        void onLoaded(List<Product> products);

        void onError(String error);

    }

    void getProducts(ProductServiceCallback callback, ProductCriteria criteria);
}
