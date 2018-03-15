package urbanclap.com.marketviewsample.market;

import android.support.annotation.NonNull;

import urbanclap.com.marketview.frame_work.market.ItemData;
import urbanclap.com.marketviewsample.entity.PokemonItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 15 Mar 2018 2:02 PM
 */


public class MarketItem implements ItemData<PokemonItem> {

    @NonNull
    private PokemonItem pokemonItem;

    public MarketItem(@NonNull PokemonItem pokemonItem) {
        this.pokemonItem = pokemonItem;
    }

    @Override
    public PokemonItem getViewModel() {
        return pokemonItem;
    }

    @Override
    public String getUUID() {
        return String.valueOf(pokemonItem.getUuid());
    }

    @Override
    public int getViewType() {
        return 0;
    }
}
