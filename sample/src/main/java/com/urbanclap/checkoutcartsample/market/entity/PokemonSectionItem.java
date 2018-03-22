package com.urbanclap.checkoutcartsample.market.entity;

import com.urbanclap.checkoutcart.frame_work.navigation_bar.Routable;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 15 Mar 2018 4:08 PM
 */


public class PokemonSectionItem implements PokemonCartBaseItem, Routable<String> {

    private String title;

    public PokemonSectionItem(String title) {
        this.title = title;
    }

    @Override
    public int getCartItemType() {
        return TYPE_HEADER;
    }

    @Override
    public String id() {
        return title.toLowerCase();
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getRouteViewModel() {
        return title;
    }

    @Override
    public String getRouteUUID() {
        return title.toLowerCase();
    }

    @Override
    public int getRouteViewType() {
        return 0;
    }
}
