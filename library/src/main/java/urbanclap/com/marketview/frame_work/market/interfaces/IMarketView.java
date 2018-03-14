package urbanclap.com.marketview.frame_work.market.interfaces;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 14 Mar 2018 1:37 PM
 */


public interface IMarketView {
    void addNavigationBar(@Nullable View navigationBar);

    void addStickyViewHolder(@Nullable View stickyView);

    void addIMarketSectionView(@Nullable View marketSectionView);
}
