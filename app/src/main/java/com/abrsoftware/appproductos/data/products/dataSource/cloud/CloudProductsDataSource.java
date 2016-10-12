package com.abrsoftware.appproductos.data.products.dataSource.cloud;

import android.util.Log;

import com.abrsoftware.appproductos.data.api.ErrorResponse;
import com.abrsoftware.appproductos.data.api.RestService;
import com.abrsoftware.appproductos.products.domain.criteria.ProductCriteria;
import com.abrsoftware.appproductos.products.domain.model.Product;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AbrWin on 10/10/16.
 */

public class CloudProductsDataSource implements ICloudProductsDataSource{


    public final String TAG = CloudProductsDataSource.class.getSimpleName();
    // Si vas a usar un dominio real o la ip de tu pc, cambia los valores de las
    // constantes o tendrás errores de ejecución.
    public static final String BASE_URL_REAL_DOMAIN
            = "http://<PON-AQUI-TU-DOMINIO-O-IP-PUBLICA>/api.appproducts.com/v1/";
    public static final String BASE_URL_REAL_DEVICE =
            "http://<PON-AQUI-TU-IP-LOCAL/api.appproducts.com/v1/";
    public static final String BASE_URL_AVD = "http://10.0.2.2/api.appproducts.com/v1/";
    public static final String BASE_URL_GENYMOTION = "http://10.0.3.2/api.appproducts.com/v1/";


    private final Retrofit mRetrofit;
    private final RestService mRestService;

    public CloudProductsDataSource() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_AVD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRestService = mRetrofit.create(RestService.class);
    }

    @Override
    public void getProducts(final ProductServiceCallback callback, ProductCriteria criteria) {

        Call<List<Product>> call = mRestService.getproducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                //Prosesamos los posibles datos
                processGetProductsResponse(response, callback);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });


    }

    private void processGetProductsResponse(Response<List<Product>> responce, ProductServiceCallback calback){
        String error = "Ha ocurrido un error";
        ResponseBody errorBody = responce.errorBody();

        if(errorBody != null){
            //¿Fué error de la API?
            if(errorBody.contentType().subtype().equals("json")){
                try{
                    //Parseamos el objeto error
                    ErrorResponse er = new Gson().fromJson(errorBody.string(), ErrorResponse.class);
                    error = er.getmMessage();
                    Log.e(TAG, error);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            calback.onError(error);
        }
        //Respuesta correcta del servicio
        if(responce.isSuccessful()){
            calback.onLoaded(responce.body());
        }
    }
}
