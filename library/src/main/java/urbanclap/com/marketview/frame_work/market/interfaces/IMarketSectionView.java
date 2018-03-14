package urbanclap.com.marketview.frame_work.market.interfaces;

import android.support.annotation.NonNull;

import urbanclap.com.marketview.frame_work.market.Section;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 14 Mar 2018 1:40 PM
 */


public interface IMarketSectionView {
    void addSections(@NonNull Section<?> section);

    void addSection(@NonNull Section<?> section);

    void removeSection(@NonNull String id);
}
