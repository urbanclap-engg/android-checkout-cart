package urbanclap.com.marketview.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import urbanclap.com.marketview.frame_work.cart.ICart;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 3:30 PM
 */


public class DefaultCart<I> implements ICart<DefaultCartItem<I>> {

    @NonNull
    protected Map<String, DefaultCartItem<I>> cartItems;

    public DefaultCart() {
        this.cartItems = new HashMap<>();
    }

    @NonNull
    @Override
    public List<DefaultCartItem<I>> getItems() {
        return new ArrayList<>(cartItems.values());
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<String, DefaultCartItem<I>> entry : cartItems.entrySet()) {
            DefaultCartItem<I> itr = entry.getValue();
            totalPrice += (itr.getQuantity() * itr.getPrice());
        }
        return totalPrice;
    }

    @Override
    public long getTotalQuantity() {
        int totalQuantity = 0;
        for (Map.Entry<String, DefaultCartItem<I>> entry : cartItems.entrySet()) {
            DefaultCartItem<I> itr = entry.getValue();
            totalQuantity += itr.getQuantity();
        }
        return totalQuantity;
    }

    @Override
    public long getQuantity(@NonNull String uuid) {
        return cartItems.get(uuid).getQuantity();
    }

    @Override
    public double getPrice(@NonNull String uuid) {
        DefaultCartItem<I> cartItem = getItem(uuid);
        return cartItem.getQuantity() * cartItem.getPrice();
    }

    @Override
    public boolean increment(@NonNull String uuid, @NonNull DefaultCartItem<I> item) {
        if (cartItems.containsKey(uuid))
            cartItems.get(uuid).incrementQuantity();
        else
            cartItems.put(uuid, item);
        return true;
    }

    @Override
    public boolean decrement(@NonNull String uuid) {
        cartItems.get(uuid).decrementQuantity();
        return true;
    }

    @Override
    public DefaultCartItem<I> getItem(@NonNull String uuid) {
        return cartItems.get(uuid);
    }

    @Override
    public DefaultCartItem<I> remove(@NonNull String uuid) {
        return cartItems.remove(uuid);
    }

    @Override
    public void clear() {
        cartItems.clear();
    }
}
