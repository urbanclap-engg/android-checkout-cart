package urbanclap.com.marketviewsample.market;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerItemFactory;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerItemViewHolder;
import urbanclap.com.marketviewsample.R;
import urbanclap.com.marketviewsample.entity.PokemonItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 15 Mar 2018 2:53 PM
 */


public class ItemFactory extends RecyclerItemFactory<PokemonItem, Void> {

    @Override
    public RecyclerItemViewHolder<PokemonItem, Void> createViewHolder(@NonNull Context context,
                                                                      @NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
    }
}
