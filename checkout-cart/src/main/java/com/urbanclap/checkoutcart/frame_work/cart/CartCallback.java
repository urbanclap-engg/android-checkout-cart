package com.urbanclap.checkoutcart.frame_work.cart;

import android.support.annotation.NonNull;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 13 Mar 2018 4:59 PM
 */


public interface CartCallback<T> {
    boolean incrementInCart(@NonNull String uuid, T item);

    boolean decrementInCart(@NonNull String uuid);
}
