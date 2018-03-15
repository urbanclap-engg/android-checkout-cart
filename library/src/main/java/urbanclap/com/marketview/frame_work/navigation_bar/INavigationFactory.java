package urbanclap.com.marketview.frame_work.navigation_bar;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 2:27 PM
 */


public interface INavigationFactory<T> {
    NavigationItemViewHolder<T> createViewHolder(int viewType, @NonNull ViewGroup parent);
}
