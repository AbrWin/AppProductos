package com.abrsoftware.appproductos.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abrsoftware.appproductos.R;
import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.List;

/**
 * Created by AbrWin on 27/09/16.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsHolder> {

    private List<Product> mProducts;
    private ProductItemListener mItemListener;

    @Override
    public ProductsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ProductsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ProductsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView name;
        private TextView price;
        private ImageView featuredImage;
        private TextView unitsInStock;
        private ProductItemListener mItemListener;

        public ProductsHolder(View itemView, ProductItemListener listener) {
            super(itemView);
            mItemListener = listener;
            name = (TextView)itemView.findViewById(R.id.product_name);
            price = (TextView)itemView.findViewById(R.id.product_price);
            unitsInStock = (TextView)itemView.findViewById(R.id.units_in_stock);
            featuredImage = (ImageView)itemView.findViewById(R.id.product_featured_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Product product = getItem(position);
            mItemListener.onProductClick(product);
        }
        public Product getItem(int position){
            return mProducts.get(position);
        }

    }

    public interface ProductItemListener{
        void onProductClick(Product clickedNote);
    }
}
