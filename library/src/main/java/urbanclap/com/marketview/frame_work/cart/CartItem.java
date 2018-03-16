package urbanclap.com.marketview.frame_work.cart;

import android.support.annotation.NonNull;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 3:55 PM
 */


public class CartItem<I> {
    private int quantity;
    @NonNull
    private I item;

    public CartItem(int quantity,
                    @NonNull I item) {

        this.quantity = quantity;
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        incrementQuantity(1);
    }

    public void incrementQuantity(int delta) {
        this.quantity += delta;
    }

    public void decrementQuantity(int delta) {
        this.quantity -= delta;
    }

    public void decrementQuantity() {
        decrementQuantity(1);
    }

    @NonNull
    public I getItem() {
        return item;
    }

    public void setItem(@NonNull I item) {
        this.item = item;
    }
}
