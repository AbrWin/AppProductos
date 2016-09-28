package com.abrsoftware.appproductos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abrsoftware.appproductos.R;
import com.abrsoftware.appproductos.products.domain.model.Product;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

/**
 * Created by AbrWin on 27/09/16.
 */

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product> mProducts;
    private ProductItemListener mItemListener;

    private final static int TYPE_PRODUCT = 1;
    private final static int TYPE_LOADING_MORE = 2;

    public ProductsAdapter(List<Product> mProducts, ProductItemListener itemListener) {
        setList(mProducts);
        mItemListener = itemListener;
    }

    @Override
    public int getItemViewType(int position) {
        if(position < getDataItemCount() && getDataItemCount() > 0){
            return TYPE_PRODUCT;
        }
        return TYPE_LOADING_MORE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;


        if (viewType == TYPE_LOADING_MORE) {
            view = layoutInflater.inflate(R.layout.item_loading_footer, parent, false);
            return new LoadingMoreHolder(view);
        }

        view = layoutInflater.inflate(R.layout.item_product, parent, false);
        return new ProductsHolder(view, mItemListener);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private void setList(List<Product> notes) {
        mProducts = checkNotNull(notes);
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
            name = (TextView) itemView.findViewById(R.id.product_name);
            price = (TextView) itemView.findViewById(R.id.product_price);
            unitsInStock = (TextView) itemView.findViewById(R.id.units_in_stock);
            featuredImage = (ImageView) itemView.findViewById(R.id.product_featured_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Product product = getItem(position);
            mItemListener.onProductClick(product);
        }

        public Product getItem(int position) {
            return mProducts.get(position);
        }

    }

    private class LoadingMoreHolder extends RecyclerView.ViewHolder {
        public ProgressBar progress;

        public LoadingMoreHolder(View view) {
            super(view);
            progress = (ProgressBar) view.findViewById(R.id.progressBar);
        }
    }

    public interface ProductItemListener {
        void onProductClick(Product clickedNote);
    }

    private int getDataItemCount(){
        return mProducts.size();
    }
}
