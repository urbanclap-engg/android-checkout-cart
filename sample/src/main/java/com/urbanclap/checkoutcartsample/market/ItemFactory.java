package com.urbanclap.checkoutcartsample.market;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.urbanclap.checkoutcart.market_impl.recycler_view_market.RecyclerItemFactory;
import com.urbanclap.checkoutcart.market_impl.recycler_view_market.RecyclerItemViewHolder;
import com.urbanclap.checkoutcart.utils.DefaultCartItem;
import com.urbanclap.checkoutcartsample.R;
import com.urbanclap.checkoutcartsample.market.entity.PokemonCartBaseItem;
import com.urbanclap.checkoutcartsample.model.PokemonItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 15 Mar 2018 2:53 PM
 */


public class ItemFactory extends RecyclerItemFactory<PokemonCartBaseItem, DefaultCartItem<PokemonItem>> {
    @Override
    public RecyclerItemViewHolder<PokemonCartBaseItem, DefaultCartItem<PokemonItem>> createViewHolder(@NonNull Context context,
                                                                                                      @NonNull ViewGroup parent,
                                                                                                      int viewType) {
        switch (viewType) {
            case PokemonCartBaseItem.TYPE_CART:
                return new CartItemViewHolder(LayoutInflater
                        .from(context)
                        .inflate(R.layout.item_layout, parent, false));

            case PokemonCartBaseItem.TYPE_HEADER:
                return new SectionItemViewHolder(LayoutInflater
                        .from(context)
                        .inflate(R.layout.item_section_layout, parent, false));
        }
        return null;
    }
}
