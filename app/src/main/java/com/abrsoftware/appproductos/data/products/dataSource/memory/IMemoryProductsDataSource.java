package com.abrsoftware.appproductos.data.products.dataSource.memory;

import com.abrsoftware.appproductos.products.domain.criteria.ProductCriteria;
import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.List;

/**
 * Created by AbrWin on 10/10/16.
 */

public interface IMemoryProductsDataSource {
    List<Product> find(ProductCriteria criteria);

    void save(Product product);

    void deleteAll();

    boolean mapIsNull();
}
