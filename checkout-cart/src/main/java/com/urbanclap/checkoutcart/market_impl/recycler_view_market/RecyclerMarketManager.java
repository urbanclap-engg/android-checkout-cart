package com.urbanclap.checkoutcart.market_impl.recycler_view_market;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import com.urbanclap.checkoutcart.frame_work.cart.CartCallback;
import com.urbanclap.checkoutcart.frame_work.market.ItemData;
import com.urbanclap.checkoutcart.frame_work.market.MarketManager;
import com.urbanclap.checkoutcart.frame_work.market.Section;
import com.urbanclap.checkoutcart.frame_work.market.interfaces.IMarketView;
import com.urbanclap.checkoutcart.frame_work.sticky.IStickyManager;
import com.urbanclap.checkoutcart.utils.MarketUtils;

import java.util.List;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 13 Mar 2018 4:16 PM
 */


@SuppressWarnings("unused")
public class RecyclerMarketManager<IT, NT, CT> extends MarketManager<IT, NT, CT> implements CartCallback<CT> {

    @NonNull
    private RecyclerView recyclerView;
    @NonNull
    private RecyclerView.LayoutManager layoutManager;
    @NonNull
    private RecyclerView.SmoothScroller smoothScroller;
    @NonNull
    private ItemPool<IT> itemPool;
    @NonNull
    private RecyclerViewAdapter<IT, CT> adapter;

    public RecyclerMarketManager(@NonNull Context context,
                                 @NonNull Config<IT, NT, CT> config,
                                 @NonNull RecyclerItemFactory<IT, CT> itemFactory) {

        this(config, MarketUtils.getDefaultRecycler(context, false), itemFactory);
    }

    public RecyclerMarketManager(@NonNull Config<IT, NT, CT> config,
                                  @NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerItemFactory<IT, CT> itemFactory) {
        super(config);
        this.recyclerView = recyclerView;
        this.itemPool = new ItemPool<>(sections);
        this.adapter = new RecyclerViewAdapter<>(this.itemPool, itemFactory, this);
        this.layoutManager = new LinearLayoutManager(recyclerView.getContext());
        this.smoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            protected int getVerticalSnapPreference() {
                return SNAP_TO_START;
            }
        };
    }

    @Override
    protected void initSectionsManager() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initStickyManager() {
        if (stickyView == null)
            return;

        final IStickyManager stickyManager = new RecyclerStickyManager(stickyView, sections, itemPool);
        RecyclerViewAdapter.ScrollCallback callback = new RecyclerViewAdapter.ScrollCallback() {
            @Override
            public void onScrollPosition(int pos) {
                stickyView.setStickyView(stickyManager.getStickyView(pos));
            }
        };
        adapter.addScrollCallback(callback);
    }

    @Override
    protected void initNavigationManagement() {
        RecyclerViewAdapter.ScrollCallback callback = new RecyclerViewAdapter.ScrollCallback() {
            @Override
            public void onScrollPosition(int pos) {
                if (navigationBar != null && isAutoNavBarItemScrollEnabled)
                    navigationBar.selectViewAt(pos);
            }
        };
        adapter.addScrollCallback(callback);
    }

    @Override
    public void handleNavigateTo(@NonNull String id) {
        int smoothScrollPos = -1;
        for (int i = 0, len = itemPool.getItemDataList().size(); i < len; i++) {
            if (itemPool.getItemDataList().get(i).getUUID().equals(id)) {
                smoothScrollPos = i;
                break;
            }
        }
        if (smoothScrollPos != -1) {
            int visiblePosition = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
            int scrollPos = -1;
            if(smoothScrollPos - 10 > visiblePosition)
                scrollPos = smoothScrollPos - 10;
            else if(smoothScrollPos + 10 < visiblePosition)
                scrollPos = smoothScrollPos + 10;

            if(scrollPos != -1)
                layoutManager.scrollToPosition(scrollPos);

            smoothScroller.setTargetPosition(smoothScrollPos);
            layoutManager.startSmoothScroll(smoothScroller);
        }
    }

    @Override
    public void handleAddSections(@NonNull List<Section<IT>> sectionList) {
        for (Section<IT> section : sectionList) {
            if (itemPool.hasSection(section.getId())) {
                Pair<Integer, Integer> removePair = itemPool.remove(section.getId());
                adapter.notifyItemRangeRemoved(removePair.first, removePair.second - removePair.first);
                Pair<Integer, Integer> addPair = itemPool.add(section, removePair.first);
                adapter.notifyItemRangeInserted(addPair.first, addPair.second - addPair.first);
            }
            Pair<Integer, Integer> addPair = itemPool.add(section);
            adapter.notifyItemRangeInserted(addPair.first, addPair.second - addPair.first);
        }
    }

    @Override
    public void handleRemoveSections(@NonNull List<String> sectionIds) {
        for (String id : sectionIds) {
            Pair<Integer, Integer> pair = itemPool.remove(id);
            adapter.notifyItemRangeRemoved(pair.first, pair.second - pair.first);
        }
    }

    @Override
    public void handleBindMarketManager(@NonNull IMarketView marketView) {
        View navigationBarView = navigationBar != null ? navigationBar.getView() : null;
        View stickySectionView = stickyView != null ? stickyView.getView() : null;
        marketView.addNavigationBar(navigationBarView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        marketView.addStickyViewHolder(stickySectionView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        marketView.addIMarketSectionView(recyclerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public boolean hasSection(@NonNull String sectionId) {
        return itemPool.hasSection(sectionId);
    }

    @Override
    public boolean hasItem(@NonNull String sectionId, @NonNull String itemId) {
        return itemPool.hasItem(sectionId, itemId);
    }

    @Override
    public void updateItem(@NonNull String sectionId, ItemData<IT> item) {
        int pos = itemPool.updateItem(sectionId, item);
        if (pos >= 0 && pos < itemPool.size())
            adapter.notifyItemChanged(pos);
    }

    @Override
    public boolean incrementInCart(@NonNull String uuid, CT item) {
        return cart != null && cart.increment(uuid, item);
    }

    @Override
    public boolean decrementInCart(@NonNull String uuid) {
        return cart != null && cart.decrement(uuid);
    }

    public void setScrollCallbacks(@Nullable RecyclerViewScrollCallbacks scrollCallbacks) {
        adapter.setRecyclerViewScrollCallbacks(scrollCallbacks);
    }
}
