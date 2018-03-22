package com.urbanclap.checkoutcart.frame_work.navigation_bar;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 12 Mar 2018 2:17 PM
 */


public abstract class NavigationItemViewHolder<T> {

    @NonNull
    private NavigationItemView navigationItemView;

    public NavigationItemViewHolder(int layoutId, @NonNull Context context) {
        navigationItemView = new NavigationItemView(context);
        navigationItemView.setContentView(layoutId);
    }

    public void bind(@NonNull Routable<T> routable,
                     @NonNull NavigationItemView.OnNavigateCallback callback,
                     @NonNull String id) {
        navigationItemView.setCallback(callback, id);
        onBind(routable.getRouteViewModel());
    }

    public abstract void onBind(T viewModel);

    @NonNull
    public NavigationItemView getNavigationItemView() {
        return navigationItemView;
    }
}
