package com.urbanclap.checkoutcartsample.market;

import android.view.View;
import android.widget.TextView;

import com.urbanclap.checkoutcart.market_impl.recycler_view_market.RecyclerItemViewHolder;
import com.urbanclap.checkoutcart.utils.DefaultCartItem;
import com.urbanclap.checkoutcartsample.R;
import com.urbanclap.checkoutcartsample.market.entity.PokemonCartBaseItem;
import com.urbanclap.checkoutcartsample.market.entity.PokemonSectionItem;
import com.urbanclap.checkoutcartsample.model.PokemonItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 15 Mar 2018 4:13 PM
 */


public class SectionItemViewHolder extends RecyclerItemViewHolder<PokemonCartBaseItem, DefaultCartItem<PokemonItem>> {

    private TextView tvTitle;

    SectionItemViewHolder(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_itemSection_title);
    }

    @Override
    public void onBindView(PokemonCartBaseItem viewModel) {
        PokemonSectionItem sectionItem = (PokemonSectionItem) viewModel;
        tvTitle.setText(sectionItem.getTitle());
    }

}
