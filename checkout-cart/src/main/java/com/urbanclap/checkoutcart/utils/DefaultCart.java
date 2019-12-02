package com.urbanclap.checkoutcart.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.urbanclap.checkoutcart.frame_work.cart.ICart;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 12 Mar 2018 3:30 PM
 */


public class DefaultCart<I extends DefaultCartItem> implements ICart<I> {

    @NonNull
    protected Map<String, I> cartItems;

    public DefaultCart() {
        this.cartItems = new HashMap<>();
    }

    @NonNull
    @Override
    public List<I> getItems() {
        return new ArrayList<>(cartItems.values());
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<String, I> entry : cartItems.entrySet()) {
            I itr = entry.getValue();
            totalPrice += (itr.getQuantity() * itr.getPrice());
        }
        return totalPrice;
    }

    @Override
    public long getTotalQuantity() {
        int totalQuantity = 0;
        for (Map.Entry<String, I> entry : cartItems.entrySet()) {
            I itr = entry.getValue();
            totalQuantity += itr.getQuantity();
        }
        return totalQuantity;
    }

    @Override
    public void setQuantity(@NonNull String uuid, @NonNull I item) {
        if (!cartItems.containsKey(uuid))
            cartItems.put(uuid, item);
    }

    @Override
    public long getQuantity(@NonNull String uuid) {
        return cartItems.get(uuid).getQuantity();
    }

    @Override
    public double getPrice(@NonNull String uuid) {
        I cartItem = getItem(uuid);
        return cartItem.getQuantity() * cartItem.getPrice();
    }

    @Override
    public boolean increment(@NonNull String uuid, @NonNull I item) {
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
    public I getItem(@NonNull String uuid) {
        return cartItems.get(uuid);
    }

    @Override
    public I remove(@NonNull String uuid) {
        return cartItems.remove(uuid);
    }

    @Override
    public void clear() {
        cartItems.clear();
    }
}
