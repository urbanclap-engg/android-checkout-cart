package com.urbanclap.checkoutcart.frame_work.sticky;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:48 PM
 */


public interface IStickyView {
    void setStickyView(@Nullable View view);

    ViewGroup getView();
}
