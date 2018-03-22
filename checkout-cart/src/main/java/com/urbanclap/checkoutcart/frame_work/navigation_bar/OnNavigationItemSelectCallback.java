package com.urbanclap.checkoutcart.frame_work.navigation_bar;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 20 Mar 2018 3:13 PM
 */


public interface OnNavigationItemSelectCallback<T> {
    void onItemSelect(@NonNull String routeId, @NonNull List<Routable<T>> routable);
}
