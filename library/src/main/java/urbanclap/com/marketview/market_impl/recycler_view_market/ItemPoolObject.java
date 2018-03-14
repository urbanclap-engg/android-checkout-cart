package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.support.annotation.NonNull;

import urbanclap.com.marketview.frame_work.market.ItemData;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:26 PM
 */


public class ItemPoolObject<T> {

    @NonNull
    private String sectionId;
    @NonNull
    private ItemData<T> itemData;

    public ItemPoolObject(@NonNull String sectionId, @NonNull ItemData<T> itemData) {
        this.sectionId = sectionId;
        this.itemData = itemData;
    }

    @NonNull
    public String getSectionId() {
        return sectionId;
    }

    @NonNull
    public ItemData<T> getItemData() {
        return itemData;
    }
}
