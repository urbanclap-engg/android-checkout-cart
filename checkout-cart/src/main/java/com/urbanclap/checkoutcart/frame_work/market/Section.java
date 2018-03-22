package com.urbanclap.checkoutcart.frame_work.market;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import com.urbanclap.checkoutcart.frame_work.sticky.IStickyViewItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:15 PM
 */


public class Section<T> {

    @NonNull
    private String id;
    @NonNull
    private List<ItemData<T>> itemDataList;
    @Nullable
    private IStickyViewItem stickyViewItem;

    public Section(@NonNull String id, @NonNull List<ItemData<T>> itemDataList) {
        this(id, itemDataList, null);
    }

    public Section(@NonNull String id,
                   @NonNull List<ItemData<T>> itemDataList,
                   @Nullable IStickyViewItem stickyViewItem) {
        this.id = id;
        this.itemDataList = itemDataList;
        this.stickyViewItem = stickyViewItem;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public List<ItemData<T>> getItemDataList() {
        return itemDataList;
    }

    @Nullable
    public IStickyViewItem getStickyViewItem() {
        return stickyViewItem;
    }
}
