package com.urbanclap.checkoutcartsample.market;

import com.urbanclap.checkoutcart.frame_work.market.ItemData;
import com.urbanclap.checkoutcartsample.market.entity.PokemonCartBaseItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 15 Mar 2018 2:02 PM
 */


public class CartItem implements ItemData<PokemonCartBaseItem> {

    private PokemonCartBaseItem item;

    public CartItem(PokemonCartBaseItem item) {
        this.item = item;
    }

    @Override
    public PokemonCartBaseItem getViewModel() {
        return item;
    }

    @Override
    public String getUUID() {
        return item.id();
    }

    @Override
    public int getViewType() {
        return item.getCartItemType();
    }
}
