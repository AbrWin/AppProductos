package com.abrsoftware.appproductos.view;

import com.abrsoftware.appproductos.data.products.ProductsRepository;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by AbrWin on 06/10/16.
 */

public class ProductsPresenter implements ProductsMvp.Presenter {

    private ProductsRepository mProductsRepository;
    private ProductsMvp.View mProductView;
    private boolean isFirstLoad = true;
    private int mCurrentPage = 1;

    public ProductsPresenter(ProductsRepository mProductsRepository, ProductsMvp.View mProductView) {
        this.mProductsRepository = checkNotNull(mProductsRepository);
        this.mProductView = checkNotNull(mProductView);
    }


    @Override
    public void loadProducts(boolean reload) {
        final boolean reallyReload = reload || isFirstLoad;
        if(reallyReload){
            mProductView.showLoadingState(true);
            mProductsRepository.refreshProducts();
            mCurrentPage = 1;
        }else{
            mProductView.showLoadMoreIndicator(true);
            mCurrentPage++;
        }

        //mProductsRepository.getProducts();

    }
}
