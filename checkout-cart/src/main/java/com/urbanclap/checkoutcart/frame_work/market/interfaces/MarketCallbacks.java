package com.urbanclap.checkoutcart.frame_work.market.interfaces;

import android.support.annotation.NonNull;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 20 Mar 2018 4:03 PM
 */


public interface MarketCallbacks {
    void firstCompletelyVisibleSection(@NonNull String sectionId);

    void firstVisibleSection(@NonNull String sectionId);

    void lastCompletelyVisibleSection(@NonNull String sectionId);

    void lastVisibleSection(@NonNull String sectionId);
}
