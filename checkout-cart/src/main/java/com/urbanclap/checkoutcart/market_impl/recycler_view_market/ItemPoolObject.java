package com.urbanclap.checkoutcart.market_impl.recycler_view_market;

import android.support.annotation.NonNull;

import com.urbanclap.checkoutcart.frame_work.market.ItemData;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:26 PM
 */


class ItemPoolObject<T> {

    @NonNull
    private String sectionId;
    @NonNull
    private ItemData<T> itemData;

    ItemPoolObject(@NonNull String sectionId, @NonNull ItemData<T> itemData) {
        this.sectionId = sectionId;
        this.itemData = itemData;
    }

    @NonNull
    String getSectionId() {
        return sectionId;
    }

    @NonNull
    ItemData<T> getItemData() {
        return itemData;
    }
}
