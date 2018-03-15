package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import urbanclap.com.marketview.frame_work.sticky.IStickyView;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 15 Mar 2018 5:34 PM
 */


public class RecyclerMarketManagerUtils {

    public static IStickyView getDefaultStickyView(@NonNull Context context) {
        return new StickyFrameLayout(context);
    }

    public static RecyclerView getDefaultRecycler(@NonNull Context context) {
        return new RecyclerView(context);
    }
}
