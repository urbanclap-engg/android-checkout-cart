package com.urbanclap.checkoutcart.market_impl.recycler_view_market;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.urbanclap.checkoutcart.frame_work.market.Section;
import com.urbanclap.checkoutcart.frame_work.sticky.IStickyManager;
import com.urbanclap.checkoutcart.frame_work.sticky.IStickyView;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 13 Mar 2018 2:59 PM
 */


@SuppressWarnings("unused")
public class RecyclerStickyManager implements IStickyManager {

    @NonNull
    private IStickyView stickyView;
    @NonNull
    private SparseIntArray viewMap;
    @NonNull
    private List<View> stickyViews;

    RecyclerStickyManager(@NonNull IStickyView stickyView,
                          @NonNull List<? extends Section<?>> sections,
                          @NonNull ItemPool<?> itemPool) {
        this.stickyView = stickyView;
        viewMap = new SparseIntArray();
        stickyViews = new ArrayList<>();
        init(sections, itemPool);
    }

    public void update(List<? extends Section<?>> sections, @NonNull ItemPool<?> itemPool) {
        viewMap.clear();
        stickyViews.clear();
        init(sections, itemPool);
    }

    @Nullable
    @Override
    public View getStickyView(int pos) {
        int viewPos = viewMap.get(pos, -1);
        return viewPos != -1 && viewPos < stickyViews.size() ? stickyViews.get(viewPos) : null;
    }

    private void init(List<? extends Section<?>> sections, ItemPool<?> itemPool) {
        for (Section<?> section : sections) {
            if (section.getStickyViewItem() == null)
                continue;

            String id = section.getId();
            int startIndex = -1;
            int endIndex = -1;
            for (int i = 0, len = itemPool.getItemPoolObjectList().size(); i < len; i++) {
                ItemPoolObject<?> itemPoolObject = itemPool.getItemPoolObjectList().get(i);
                if (itemPoolObject.getSectionId().equals(id)) {
                    if (startIndex == -1)
                        startIndex = i;
                    endIndex = i;
                }
            }

            if (startIndex != -1 && endIndex != -1) {
                stickyViews.add(section.getStickyViewItem().createView(stickyView.getView()));
                int pos = stickyViews.size() - 1;
                for (int i = startIndex; i <= endIndex; i++) {
                    viewMap.append(i, pos);
                }
            }
        }
    }
}
