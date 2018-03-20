package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.support.annotation.NonNull;
import android.util.Pair;

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


class ItemPool<T> {
    @NonNull
    private List<ItemPoolObject<T>> itemPoolObjectList;

    ItemPool(List<Section<T>> sections) {
        itemPoolObjectList = new ArrayList<>();
        for (Section<T> section : sections)
            add(section);
    }

    Pair<Integer, Integer> add(Section<T> section) {
        int pos = containsFirst(section.getId());
        if (pos != -1) {
            throw new IllegalStateException(section.getId() + " Section already exists. Please use update instead");
        }
        return add(section, itemPoolObjectList.size());
    }

    Pair<Integer, Integer> add(Section<T> section, int pos) {
        String id = section.getId();
        Collections.reverse(section.getItemDataList());
        for (ItemData<T> itemData : section.getItemDataList()) {
            itemPoolObjectList.add(pos, new ItemPoolObject<>(id, itemData));
        }
        return new Pair<>(pos, pos + section.getItemDataList().size());
    }

    Pair<Integer, Integer> remove(@NonNull String id) {
        List<ItemPoolObject<T>> listToRemove = new ArrayList<>();
        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0, len = itemPoolObjectList.size(); i < len; i++) {
            ItemPoolObject<T> itemPoolObject = itemPoolObjectList.get(i);
            if (itemPoolObject.getSectionId().equals(id)) {
                if (startIndex == -1)
                    startIndex = i;
                endIndex = i;
                listToRemove.add(itemPoolObject);
            }
        }
        itemPoolObjectList.removeAll(listToRemove);
        return new Pair<>(startIndex, endIndex + 1);
    }

    boolean hasSection(@NonNull String sectionId) {
        return containsFirst(sectionId) != -1;
    }

    @NonNull
    List<ItemData<T>> getItemDataList() {
        List<ItemData<T>> itemDataList = new ArrayList<>();
        for (ItemPoolObject<T> itemPoolObject : itemPoolObjectList) {
            itemDataList.add(itemPoolObject.getItemData());
        }
        return itemDataList;
    }

    @NonNull
    List<ItemPoolObject<T>> getItemPoolObjectList() {
        return itemPoolObjectList;
    }

    private int containsFirst(String id) {
        for (int i = 0, len = itemPoolObjectList.size(); i < len; i++) {
            if (itemPoolObjectList.get(i).getSectionId().equals(id))
                return i;
        }
        return -1;
    }
}
