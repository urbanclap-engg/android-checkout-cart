package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.support.annotation.Nullable;

import urbanclap.com.marketview.frame_work.cart.CartCallback;
import urbanclap.com.marketview.frame_work.market.interfaces.IItemFactory;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 13 Mar 2018 3:46 PM
 */


public abstract class RecyclerViewItemFactory<T, CT> implements IItemFactory<T, RecyclerViewItemViewHolder<T, CT>> {

    RecyclerViewItemViewHolder<T, CT> create(int viewType, @Nullable CartCallback<CT> cartCallback) {
        RecyclerViewItemViewHolder<T, CT> viewHolder = createViewHolder(viewType);
        viewHolder.setCartCallback(cartCallback);
        return viewHolder;
    }

    @Override
    public abstract RecyclerViewItemViewHolder<T, CT> createViewHolder(int viewType);
}
