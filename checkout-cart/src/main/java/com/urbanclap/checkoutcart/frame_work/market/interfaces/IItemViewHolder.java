package com.urbanclap.checkoutcart.frame_work.market.interfaces;

import android.support.annotation.NonNull;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:11 PM
 */


public interface IItemViewHolder<T, CT> {
    void onBindView(T viewModel);

    boolean increment(@NonNull String uuid, CT item);

    boolean decrement(@NonNull String uuid);
}
