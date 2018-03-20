package urbanclap.com.marketview.frame_work.market.interfaces;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:12 PM
 */


public interface IItemFactory<T, K extends IItemViewHolder<T>> {
    K createViewHolder(@NonNull Context context, @NonNull ViewGroup parent, int viewType);
}
