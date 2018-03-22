package com.urbanclap.checkoutcart.frame_work.navigation_bar;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 12 Mar 2018 2:13 PM
 */


@SuppressWarnings("unused")
public interface INavigationBar {
    void addView(@NonNull NavigationItemView navigationItemView);

    void addView(@NonNull NavigationItemView navigationItemView, int pos);

    void updateView(@NonNull NavigationItemView navigationItemView, int pos);

    void removeView(int pos);

    void clear();

    ViewGroup getView();

    void selectViewAt(int pos);
}
