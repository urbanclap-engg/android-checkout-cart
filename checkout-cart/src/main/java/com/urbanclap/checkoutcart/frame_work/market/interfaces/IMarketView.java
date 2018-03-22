package com.urbanclap.checkoutcart.frame_work.market.interfaces;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.urbanclap.checkoutcart.frame_work.market.MarketManager;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 14 Mar 2018 1:37 PM
 */


public interface IMarketView {
    void addNavigationBar(@Nullable View navigationBar, @NonNull ViewGroup.LayoutParams layoutParams);

    void addStickyViewHolder(@Nullable View stickyView, @NonNull ViewGroup.LayoutParams layoutParams);

    void addIMarketSectionView(@Nullable View marketSectionView, @NonNull ViewGroup.LayoutParams layoutParams);

    void bindMarketManager(@NonNull MarketManager<?, ?, ?> marketManager);
}
