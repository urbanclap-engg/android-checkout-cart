package urbanclap.com.marketview.frame_work.cart;

import android.support.annotation.NonNull;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 13 Mar 2018 4:59 PM
 */


public interface CartCallback<T> {
    void incrementInCart(@NonNull String uuid, T item);

    void decrementInCart(@NonNull String uuid);
}
