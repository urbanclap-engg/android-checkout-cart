package urbanclap.com.marketview.frame_work.cart;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 3:30 PM
 */


public abstract class Cart<I> implements ICart<I> {

    @NonNull
    protected Map<String, CartItem<I>> cartItems;

    public Cart() {
        this.cartItems = new HashMap<>();
    }
}
