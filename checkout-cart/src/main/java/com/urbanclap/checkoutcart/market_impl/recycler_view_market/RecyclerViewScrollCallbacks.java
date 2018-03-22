package com.urbanclap.checkoutcart.market_impl.recycler_view_market;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.urbanclap.checkoutcart.frame_work.market.interfaces.MarketCallbacks;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 13 Mar 2018 4:38 PM
 */


public interface RecyclerViewScrollCallbacks extends MarketCallbacks {

    void onScrolled(RecyclerView recyclerView, int dx, int dy);

    void onScrollStateChanged(RecyclerView recyclerView, int newState);

    void firstCompletelyVisibleItem(int pos, @Nullable String id);

    void lastCompletelyVisibleItem(int pos, @Nullable String id);

    void firstVisibleItem(int pos, @Nullable String id);

    void lastVisibleItem(int pos, @Nullable String id);
}
