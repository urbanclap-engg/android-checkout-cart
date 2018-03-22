package com.urbanclap.checkoutcart.utils;

import android.support.annotation.NonNull;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 12 Mar 2018 3:55 PM
 */


public class DefaultCartItem<I> {
    private int quantity;
    private double price;
    @NonNull
    private I item;

    public DefaultCartItem(int quantity,
                           double price,
                           @NonNull I item) {

        this.quantity = quantity;
        this.price = price;
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
