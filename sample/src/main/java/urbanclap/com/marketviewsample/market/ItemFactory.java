package urbanclap.com.marketviewsample.market;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerItemFactory;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerItemViewHolder;
import urbanclap.com.marketviewsample.R;
import urbanclap.com.marketviewsample.market.entity.PokemonCartBaseItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 15 Mar 2018 2:53 PM
 */


public class ItemFactory extends RecyclerItemFactory<PokemonCartBaseItem, Void> {

    @Override
    public RecyclerItemViewHolder<PokemonCartBaseItem, Void> createViewHolder(@NonNull Context context,
                                                                              @NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case PokemonCartBaseItem.TYPE_CART:
                return new CartItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
            case PokemonCartBaseItem.TYPE_SECTION:
                return new SectionItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_section_layout, parent, false));
        }
        return null;
    }
}