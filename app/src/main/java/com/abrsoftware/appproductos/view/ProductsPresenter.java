package com.abrsoftware.appproductos.view;

import com.abrsoftware.appproductos.data.products.ProductsRepository;
import com.abrsoftware.appproductos.products.domain.criteria.PagingProductCriteria;
import com.abrsoftware.appproductos.products.domain.criteria.ProductCriteria;
import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by AbrWin on 06/10/16.
 */

public class ProductsPresenter implements ProductsMvp.Presenter {

    private ProductsRepository mProductsRepository;
    private ProductsMvp.View mProductView;
    public static final int PRODUCTS_LIMIT = 20;
    private boolean isFirstLoad = true;
    private int mCurrentPage = 1;

    public ProductsPresenter(ProductsRepository productsRepository, ProductsMvp.View productView) {
        mProductsRepository = checkNotNull(productsRepository);
        mProductView = checkNotNull(productView);
    }


    @Override
    public void loadProducts(boolean reload) {
        final boolean reallyReload = reload || isFirstLoad;
        if (reallyReload) {
            mProductView.showLoadingState(true);
            mProductsRepository.refreshProducts();
            mCurrentPage = 1;
        } else {
            mProductView.showLoadMoreIndicator(true);
            mCurrentPage++;
        }

        //Se aplica el criterio para la paginacion
        ProductCriteria productCriteria = new PagingProductCriteria(mCurrentPage, PRODUCTS_LIMIT);
        mProductsRepository.getProducts(new ProductsRepository.GetProductsCallback() {
            @Override
            public void onProductsLoaded(List<Product> products) {
                mProductView.showLoadingState(false);
                processProducts(products, reallyReload);

                // Ahora si, ya no es el primer refresco
                isFirstLoad = false;
            }

            @Override
            public void onDataNotAvailable(String error) {
                mProductView.showLoadingState(false);
                mProductView.showLoadMoreIndicator(false);
                mProductView.showProductError(error);
            }
        }, productCriteria);

    }

    private void processProducts(List<Product> products, boolean reload) {
        if (products.isEmpty()) {
            if (reload) {
                mProductView.showEmptyState();
            } else {
                mProductView.showLoadMoreIndicator(false);
            }
            mProductView.allowMoreData(false);
        } else {
            if(reload){
                mProductView.showProducts(products);
            }else{
                mProductView.showLoadMoreIndicator(false);
                mProductView.showProductsPage(products);
            }
            mProductView.allowMoreData(true);
        }
    }
}
