package com.urbanclap.checkoutcart.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.urbanclap.checkoutcart.frame_work.navigation_bar.INavigationBar;
import com.urbanclap.checkoutcart.frame_work.sticky.IStickyView;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 15 Mar 2018 5:34 PM
 */


public class MarketUtils {

    public static IStickyView getDefaultStickyView(@NonNull Context context) {
        return new StickyFrameLayout(context);
    }

    public static RecyclerView getDefaultRecycler(@NonNull Context context,
                                                  boolean enableNestedScrolling) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setNestedScrollingEnabled(enableNestedScrolling);
        return recyclerView;
    }

    public static INavigationBar getDefaultHorizontalNavigationBar(@NonNull Context context) {
        return new NavigationHorizontalScroll(context);
    }
}
