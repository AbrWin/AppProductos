package com.abrsoftware.appproductos.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abrsoftware.appproductos.R;
import com.abrsoftware.appproductos.adapter.ProductsAdapter;
import com.abrsoftware.appproductos.adapter.ProductsAdapter.ProductItemListener;
import com.abrsoftware.appproductos.di.DependencyProvider;
import com.abrsoftware.appproductos.products.domain.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductsFragment extends Fragment implements ProductsMvp.View {

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

    private ProductsPresenter mProductPresenter;

    public ProductsFragment() {
    }

    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductsAdapter = new ProductsAdapter(new ArrayList<Product>(0), mItemListener);
        mProductPresenter = new ProductsPresenter(DependencyProvider.provideProductsRepository(getActivity()), this);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        //Referencias UI
        mProductsList = (RecyclerView) rootView.findViewById(R.id.products_list);
        mEmptyView = rootView.findViewById(R.id.noProducts);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);

        //Setup
        setUpProductsList();
        setUptRefreshLayout();
        if(savedInstanceState != null){
            hideList(false);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState == null){
            mProductPresenter.loadProducts(false);
        }
    }

    private void setUpProductsList() {
        mProductsList.setAdapter(mProductsAdapter);
        mProductsList.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = (LinearLayoutManager)mProductsList.getLayoutManager();

        //Se agrega escucha de scroll infinito
        mProductsList.addOnScrollListener(new InfinityScrollListener(mProductsAdapter, layoutManager) {
            @Override
            public void onLoadMore() {
                mProductPresenter.loadProducts(false);
            }
        });
    }

    private void setUptRefreshLayout() {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(),
                R.color.colorPrimary), ContextCompat.getColor(getActivity(),
                R.color.colorAccent), ContextCompat.getColor(getActivity(),
                R.color.colorPrimaryDark));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mProductPresenter.loadProducts(true);
            }
        });
    }


    @Override
    public void showProducts(List<Product> products) {
        mProductsAdapter.replaceData(products);
        hideList(false);
    }

    @Override
    public void showLoadingState(final boolean show) {
        if (getView() == null)
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
        hideList(true);
    }

    private void hideList(boolean hide){
        mProductsList.setVisibility(hide ? View.GONE: View.VISIBLE);
        mEmptyView.setVisibility(hide ? View.VISIBLE: View.GONE);
        return;
    }

    @Override
    public void showProductError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProductsPage(List<Product> products) {
        mProductsAdapter.addData(products);
        hideList(false);

    }

    @Override
    public void showLoadMoreIndicator(boolean show) {
        if(!show){
            mProductsAdapter.dataFinishedLoading();
        }else {
            mProductsAdapter.dataStartedLoading();
        }
    }

    @Override
    public void allowMoreData(boolean show) {
        mProductsAdapter.setMoreData(show);
    }
}
