package urbanclap.com.marketview.market;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:15 PM
 */


public class Section<T> {

    @NonNull
    private String id;
    @NonNull
    private List<ItemData<T>> itemDataList;
    @Nullable
    private View stickyView;

    public Section(@NonNull String id, @NonNull List<ItemData<T>> itemDataList) {
        this(id, itemDataList, null);
    }

    public Section(@NonNull String id, @NonNull List<ItemData<T>> itemDataList, @Nullable View stickyView) {
        this.id = id;
        this.itemDataList = itemDataList;
        this.stickyView = stickyView;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public List<ItemData<T>> getItemDataList() {
        return itemDataList;
    }

    @Nullable
    public View getStickyView() {
        return stickyView;
    }
}
