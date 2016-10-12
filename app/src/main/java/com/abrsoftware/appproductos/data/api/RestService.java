package com.abrsoftware.appproductos.data.api;

import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Segmentos de URL donde se va actur
 */

public interface RestService {
    @GET("products")
    Call<List<Product>>getproducts();
}
