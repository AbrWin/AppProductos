package com.abrsoftware.appproductos.data.products.dataSource.memory;

import com.abrsoftware.appproductos.products.domain.criteria.ProductCriteria;
import com.abrsoftware.appproductos.products.domain.model.Product;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by AbrWin on 10/10/16.
 */

public class MemoryProductsDataSource implements IMemoryProductsDataSource {
    private static HashMap<String, Product> mCachedProducts;

    @Override
    public List<Product> find(ProductCriteria criteria) {
        ArrayList<Product> products = Lists.newArrayList(mCachedProducts.values());
        return products;
    }

    @Override
    public void save(Product product) {
        if(mCachedProducts == null){
            mCachedProducts  = new LinkedHashMap<>();
        }
        mCachedProducts.put(product.getmCode(), product);
    }

    @Override
    public void deleteAll() {
        if(mCachedProducts == null){
            mCachedProducts  = new LinkedHashMap<>();
        }
        mCachedProducts.clear();
    }

    @Override
    public boolean mapIsNull() {
        return mCachedProducts == null;
    }
}
