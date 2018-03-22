package com.urbanclap.checkoutcart.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.urbanclap.checkoutcart.market_impl.recycler_view_market.RecyclerViewScrollCallbacks;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 20 Mar 2018 7:00 PM
 */


public abstract class SimpleRecyclerViewScrollCallbacks implements RecyclerViewScrollCallbacks {
    @Override
    public void firstCompletelyVisibleSection(@NonNull String sectionId) {

    }

    @Override
    public void firstVisibleSection(@NonNull String sectionId) {

    }

    @Override
    public void lastCompletelyVisibleSection(@NonNull String sectionId) {

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

    }

    @Override
    public void lastVisibleSection(@NonNull String sectionId) {

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

    }

    @Override
    public void firstCompletelyVisibleItem(int pos, @Nullable String id) {

    }

    @Override
    public void lastCompletelyVisibleItem(int pos, @Nullable String id) {

    }

    @Override
    public void firstVisibleItem(int pos, @Nullable String id) {

    }

    @Override
    public void lastVisibleItem(int pos, @Nullable String id) {

    }
}
