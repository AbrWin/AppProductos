package com.abrsoftware.appproductos.products.domain.criteria;

import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.List;

/**
 * Created by AbrWin on 10/10/16.
 */

public interface ProductCriteria {
    List<Product> match(List<Product> products);
}
