package urbanclap.com.marketviewsample.market;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import urbanclap.com.marketview.frame_work.cart.Cart;
import urbanclap.com.marketview.frame_work.cart.CartItem;
import urbanclap.com.marketviewsample.model.PokemonItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 16 Mar 2018 2:35 PM
 */


public class PokemonCart extends Cart<PokemonItem> {

    public PokemonCart() {
        super();
    }

    @NonNull
    @Override
    public List<CartItem<PokemonItem>> getItems() {
        return new ArrayList<>(cartItems.values());
    }

    @Override
    public double getTotalPrice() {
        int totalPrice = 0;
        for (Map.Entry<String, CartItem<PokemonItem>> entry : cartItems.entrySet()) {
            CartItem<PokemonItem> itr = entry.getValue();
            totalPrice += (itr.getQuantity() * 5);
        }
        return totalPrice;
    }

    @Override
    public long getTotalQuantity() {
        int totalQuantity = 0;
        for (Map.Entry<String, CartItem<PokemonItem>> entry : cartItems.entrySet()) {
            CartItem<PokemonItem> itr = entry.getValue();
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
        return getQuantity(uuid) * 5;
    }

    @Override
    public void increment(@NonNull String uuid, @NonNull PokemonItem item) {
        if (cartItems.containsKey(uuid))
            cartItems.get(uuid).incrementQuantity();
        else
            cartItems.put(uuid, new CartItem<>(1, item));

    }

    @Override
    public void decrement(@NonNull String uuid) {
        cartItems.get(uuid).decrementQuantity();
    }

    @Override
    public CartItem<PokemonItem> getItem(@NonNull String uuid) {
        return cartItems.get(uuid);
    }

    @Override
    public void remove(@NonNull String uuid) {
        cartItems.remove(uuid);
    }

    @Override
    public void clear() {
        cartItems.clear();
    }
}
