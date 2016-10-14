package com.abrsoftware.appproductos.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.abrsoftware.appproductos.data.products.ProductsRepository;
import com.abrsoftware.appproductos.data.products.dataSource.cloud.CloudProductsDataSource;
import com.abrsoftware.appproductos.data.products.dataSource.memory.MemoryProductsDataSource;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by AbrWin on 12/10/16.
 */

public class DependencyProvider {
    private static Context mContex;
    private static MemoryProductsDataSource memorySource = null;
    private static CloudProductsDataSource cloudSource = null;
    private static ProductsRepository mProductsRepository = null;

    public DependencyProvider() {
    }

    public static ProductsRepository provideProductsRepository(@NonNull Context contex){
        mContex = checkNotNull(contex);
        if(mProductsRepository == null){
            //mProductsRepository = new ProductsRepository(getm)
        }
        return null;
    }
}
