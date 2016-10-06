package com.abrsoftware.appproductos.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abrsoftware.appproductos.R;
import com.abrsoftware.appproductos.adapter.ProductsAdapter;
import com.abrsoftware.appproductos.adapter.ProductsAdapter.ProductItemListener;
import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductsFragment extends Fragment implements ProductsMvp.View{

    private RecyclerView mProductsList;
    private ProductsAdapter mProductsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View mEmptyView;
    private ProductItemListener mItemListener = new ProductItemListener() {
        @Override
        public void onProductClick(Product clickedNote) {
            // Aquí lanzarías la pantalla de detalle del producto
        }
    };

    public ProductsFragment() {
    }

    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductsAdapter = new ProductsAdapter(new ArrayList<Product>(0), mItemListener);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        //Referencias UI
        mProductsList = (RecyclerView) rootView.findViewById(R.id.products_list);
        mEmptyView = rootView.findViewById(R.id.noProducts);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);

        return rootView;
    }

    private void setUpProductsList() {
        mProductsList.setAdapter(mProductsAdapter);
        mProductsList.setHasFixedSize(true);
    }

    private void setUptRefreshLayout() {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(),
                R.color.colorPrimary), ContextCompat.getColor(getActivity(),
                R.color.colorAccent), ContextCompat.getColor(getActivity(),
                R.color.colorPrimaryDark));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }


    @Override
    public void showProducts(List<Product> products) {
        mProductsAdapter.replaceData(products);
        mEmptyView.setVisibility(View.GONE);
        mProductsList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingState(final boolean show) {
        if(getView() == null)
            return;

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(show);
            }
        });

    }

    @Override
    public void showEmptyState() {

    }

    @Override
    public void showProductError(String msg) {

    }

    @Override
    public void showProductsPage(List<Product> products) {

    }

    @Override
    public void showLoadMoreIndicator(boolean show) {

    }

    @Override
    public void allowMoreData(boolean show) {

    }
}
