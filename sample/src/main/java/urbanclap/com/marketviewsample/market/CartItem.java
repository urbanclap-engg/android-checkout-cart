package urbanclap.com.marketviewsample.market;

import urbanclap.com.marketview.frame_work.market.ItemData;
import urbanclap.com.marketviewsample.market.entity.PokemonCartBaseItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
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
