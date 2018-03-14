package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 13 Mar 2018 4:38 PM
 */


public interface RecyclerViewScrollCallbacks {

    void onScrolled(RecyclerView recyclerView, int dx, int dy);

    void onScrollStateChanged(RecyclerView recyclerView, int newState);

    void firstCompletelyVisibleItem(int pos, @Nullable String id);

    void lastCompletelyVisibleItem(int pos, @Nullable String id);

    void firstVisibleItem(int pos, @Nullable String id);

    void lastVisibleItem(int pos, @Nullable String id);
}
