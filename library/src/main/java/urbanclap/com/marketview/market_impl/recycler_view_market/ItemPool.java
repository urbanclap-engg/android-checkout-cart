package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import urbanclap.com.marketview.frame_work.market.ItemData;
import urbanclap.com.marketview.frame_work.market.Section;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:23 PM
 */


public class ItemPool<T> {
    @NonNull
    private List<ItemPoolObject<T>> itemPoolObjectList;

    public ItemPool(List<Section<T>> sections) {
        itemPoolObjectList = new ArrayList<>();
        add(sections);
    }

    public void add(List<Section<T>> sections) {
        for (Section<T> section : sections)
            add(section);
    }

    public void add(Section<T> section) {
        int pos = containsFirst(section.getId());
        if (pos == -1) {
            update(section, pos);
            return;
        }
        add(section, itemPoolObjectList.size());
    }

    public void add(Section<T> section, int pos) {
        String id = section.getId();
        Collections.reverse(section.getItemDataList());
        for (ItemData<T> itemData : section.getItemDataList()) {
            itemPoolObjectList.add(pos, new ItemPoolObject<>(id, itemData));
        }
    }

    public void remove(@NonNull List<String> ids) {
        for (String id : ids)
            remove(id);
    }

    public void remove(@NonNull String id) {
        List<ItemPoolObject<T>> listToRemove = new ArrayList<>();
        for (ItemPoolObject<T> itemPoolObject : itemPoolObjectList) {
            if (itemPoolObject.getSectionId().equals(id))
                listToRemove.add(itemPoolObject);
        }
        itemPoolObjectList.removeAll(listToRemove);
    }

    @NonNull
    public List<ItemData<T>> getItemDataList() {
        List<ItemData<T>> itemDataList = new ArrayList<>();
        for (ItemPoolObject<T> itemPoolObject : itemPoolObjectList) {
            itemDataList.add(itemPoolObject.getItemData());
        }
        return itemDataList;
    }

    @NonNull
    public List<ItemPoolObject<T>> getItemPoolObjectList() {
        return itemPoolObjectList;
    }

    private int containsFirst(String id) {
        for (int i = 0, len = itemPoolObjectList.size(); i < len; i++) {
            if (itemPoolObjectList.get(i).getSectionId().equals(id))
                return i;
        }
        return -1;
    }

    private void update(Section<T> section, int pos) {
        remove(section.getId());
        add(section, pos);
    }
}
