package com.abrsoftware.appproductos.data.products;

import android.content.Context;

import com.abrsoftware.appproductos.Utils.Connectivity.Connection;
import com.abrsoftware.appproductos.data.products.dataSource.cloud.ICloudProductsDataSource;
import com.abrsoftware.appproductos.data.products.dataSource.memory.IMemoryProductsDataSource;
import com.abrsoftware.appproductos.products.domain.criteria.ProductCriteria;
import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Repositorio de productos
 */

public class ProductsRepository implements IProductsRepository {
    private final IMemoryProductsDataSource mMemoryProductsDataSource;
    private final ICloudProductsDataSource mCloudProductsDataSource;
    private final Context mContext;
    private boolean mReload;

    public ProductsRepository(IMemoryProductsDataSource memoryProductsDataSource, ICloudProductsDataSource cloudProductsDataSource, Context context) {
        mMemoryProductsDataSource = checkNotNull(memoryProductsDataSource);
        mCloudProductsDataSource = checkNotNull(cloudProductsDataSource);
        mContext = checkNotNull(context);
    }


    @Override
    public void getProducts(GetProductsCallback callback, ProductCriteria criteria) {
        if (!mMemoryProductsDataSource.mapIsNull() && !mReload) {
            getProductsFromServer(callback, criteria);
        } else {
            List<Product> products = mMemoryProductsDataSource.find(criteria);
            if (products.size() > 0) {
                callback.onProductsLoaded(products);
            } else {
                getProductsFromServer(callback, criteria);
            }
        }

    }

    private void getProductsFromServer(final GetProductsCallback callback, final ProductCriteria criteria) {
        if (!Connection.isOnline(mContext)) {
            callback.onDataNotAvailable("No hay conexión de red.");
            return;
        }

        mCloudProductsDataSource.getProducts(new ICloudProductsDataSource.ProductServiceCallback() {
            @Override
            public void onLoaded(List<Product> products) {
                refreshMemoryDataSource(products);
                getProductsFromMemory(callback, criteria);
            }

            @Override
            public void onError(String error) {
                callback.onDataNotAvailable(error);
            }
        }, null);
    }

    private void getProductsFromMemory(GetProductsCallback callback, ProductCriteria criteria) {
        callback.onProductsLoaded(mMemoryProductsDataSource.find(criteria));
    }

    private void refreshMemoryDataSource(List<Product> products) {
        mMemoryProductsDataSource.deleteAll();
        for (Product product : products){
            mMemoryProductsDataSource.save(product);
        }
        mReload = false;
    }

    @Override
    public void refreshProducts() {
        mReload = true;
    }
}
