package com.urbanclap.checkoutcartsample.market.entity;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 15 Mar 2018 4:04 PM
 */


public interface PokemonCartBaseItem {

    int TYPE_CART = 0x0;
    int TYPE_HEADER = 0x1;

    int getCartItemType();

    String id();
}
