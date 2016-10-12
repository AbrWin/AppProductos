package com.abrsoftware.appproductos.data.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AbrWin on 12/10/16.
 */

public class ErrorResponse {
    @SerializedName("message")
    String mMessage;

    public String getmMessage(){
        return mMessage;
    }
}
