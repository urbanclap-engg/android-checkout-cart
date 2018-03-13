package urbanclap.com.marketview.recycler_view_market;

import android.support.annotation.NonNull;

import urbanclap.com.marketview.market.ItemData;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:26 PM
 */


public class ItemPoolObject<T> {

    @NonNull
    private String id;
    @NonNull
    private ItemData<T> itemData;

    public ItemPoolObject(@NonNull String id, @NonNull ItemData<T> itemData) {
        this.id = id;
        this.itemData = itemData;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public ItemData<T> getItemData() {
        return itemData;
    }
}
