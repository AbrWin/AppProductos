package com.abrsoftware.appproductos.products;

/**
 * Created by AbrWin on 03/10/16.
 */

public interface DataLoading {
    /**
     * Permite saber si un elemento relacionado con datos está cargandolos o si no posee más
     */
    boolean isLoadingData();
    boolean isThereMoreData();
}
