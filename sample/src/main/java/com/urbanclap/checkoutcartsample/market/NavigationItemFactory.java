package com.urbanclap.checkoutcartsample.market;

import android.content.Context;
import android.support.annotation.NonNull;

import com.urbanclap.checkoutcart.frame_work.navigation_bar.INavigationFactory;
import com.urbanclap.checkoutcart.frame_work.navigation_bar.NavigationItemViewHolder;
import com.urbanclap.checkoutcartsample.R;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 15 Mar 2018 7:54 PM
 */


public class NavigationItemFactory implements INavigationFactory<String> {

    public NavigationItemFactory() {
    }

    @Override
    public NavigationItemViewHolder<String> createViewHolder(int viewType, @NonNull Context context) {
        return new PokemonNavigationViewHolder(R.layout.navigation_bar_layout, context);
    }
}
