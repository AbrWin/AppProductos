package com.abrsoftware.appproductos.view;

import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.List;

/**
 * Created by AbrWin on 06/10/16.
 */

public interface ProductsMvp {
    interface View{
        void showProducts(List<Product> products);
        void showLoadingState(boolean show);
        void showEmptyState();
        void showProductError(String msg);
        void showProductsPage(List<Product> products);
        void showLoadMoreIndicator(boolean show);
        void allowMoreData(boolean show);
    }

    interface Presenter{
        void loadProducts(boolean reload);
    }
}
