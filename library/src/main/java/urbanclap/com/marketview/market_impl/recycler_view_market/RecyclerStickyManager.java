package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import urbanclap.com.marketview.frame_work.market.Section;
import urbanclap.com.marketview.frame_work.market.interfaces.IStickyManager;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 13 Mar 2018 2:59 PM
 */


public class RecyclerStickyManager implements IStickyManager {

    @NonNull
    private SparseIntArray viewMap;
    @NonNull
    private List<View> stickyViews;

    public RecyclerStickyManager(@NonNull List<Section<?>> sections, @NonNull ItemPool<?> itemPool) {
        viewMap = new SparseIntArray();
        stickyViews = new ArrayList<>();
        init(sections, itemPool);
    }

    public void update(List<Section<?>> sections, @NonNull ItemPool<?> itemPool) {
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

    private void init(List<Section<?>> sections, ItemPool<?> itemPool) {
        for (Section<?> section : sections) {
            if (section.getStickyView() == null)
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
                stickyViews.add(section.getStickyView());
                int pos = stickyViews.size() - 1;
                for (int i = startIndex; i <= endIndex; i++) {
                    viewMap.append(i, pos);
                }
            }
        }
    }
}
