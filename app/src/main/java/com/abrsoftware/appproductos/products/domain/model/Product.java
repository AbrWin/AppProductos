package com.abrsoftware.appproductos.products.domain.model;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by AbrWin on 27/09/16.
 */

public class Product {
    private String mCode;
    private String mName;
    private String mDescription;
    private String mBrand;
    private float mPrice;
    private int mUnitsInStock;
    private String mImageUrl;


    public Product(float price, String name, String imageUrl) {
        mCode = UUID.randomUUID().toString();
        mPrice = price;
        mName = name;
        mImageUrl = imageUrl;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public int getmUnitsInStock() {
        return mUnitsInStock;
    }

    public void setmUnitsInStock(int mUnitsInStock) {
        this.mUnitsInStock = mUnitsInStock;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public float getmPrice() {
        return mPrice;
    }

    public void setmPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public String getFormatedPrice() {
        return String.format("$%s", mPrice);
    }

    public String getFormattedUnitsInStock() {
        return String.format(Locale.getDefault(), "%d u.", mUnitsInStock);
    }

}
