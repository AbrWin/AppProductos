package com.abrsoftware.appproductos.products.domain.criteria;

import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AbrWin on 11/10/16.
 */

public class PagingProductCriteria implements ProductCriteria {
    private final int mPage;
    private final int mLimit;

    public PagingProductCriteria(int page, int limit) {
        mPage = page;
        mLimit = limit;
    }

    @Override
    public List<Product> match(List<Product> products) {
        List<Product> criteriaProducts = new ArrayList<>();
        int a, b, size, numPages;

        //Sanidad
        if(mLimit <= 0 || mPage <= 0){
            return criteriaProducts;
        }

        size = products.size();

        // ¿La página es más grande que el contenido?
        if (size < mLimit && mPage == 1) {
            return products;
        }

        numPages = size / mLimit;

        if(mPage > numPages){
            return criteriaProducts;
        }

        a = (mPage - 1) * mLimit;

        if(a == size){
            return criteriaProducts;
        }

        b = a + mLimit;

        criteriaProducts = products.subList(a, b);

        return criteriaProducts;
    }
}
