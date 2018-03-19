package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import urbanclap.com.marketview.frame_work.cart.CartCallback;
import urbanclap.com.marketview.frame_work.market.MarketManager;
import urbanclap.com.marketview.frame_work.market.Section;
import urbanclap.com.marketview.frame_work.market.interfaces.IMarketView;
import urbanclap.com.marketview.frame_work.sticky.IStickyManager;
import urbanclap.com.marketview.utils.MarketUtils;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
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

        this(config, MarketUtils.getDefaultRecycler(context), itemFactory);
    }

    private RecyclerMarketManager(@NonNull Config<IT, NT, CT> config,
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
        adapter.setScrollCallback(callback);
    }

    @Override
    public void navigateTo(@NonNull String id) {
        int pos = -1;
        for (int i = 0, len = itemPool.getItemDataList().size(); i < len; i++) {
            if (itemPool.getItemDataList().get(i).getUUID().equals(id)) {
                pos = i;
                break;
            }
        }

        if (pos != -1) {
            // TODO: 16/Mar/18 @adnaan: handle ended scrolling...
            smoothScroller.setTargetPosition(pos);
            layoutManager.startSmoothScroll(smoothScroller);
        }
    }

    @Override
    public void addSection(@NonNull Section<IT> section) {
        // TODO: 13/Mar/18 @adnaan: add..
    }

    @Override
    public boolean removeSection(@NonNull String sectionId) {
        // TODO: 13/Mar/18 @adnaan: todo...
        return false;
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
    public void incrementInCart(@NonNull String uuid, CT item) {
        if (cart != null)
            cart.increment(uuid, item);
    }

    @Override
    public void decrementInCart(@NonNull String uuid) {
        if (cart != null)
            cart.decrement(uuid);
    }

    public void setScrollCallbacks(@Nullable RecyclerViewScrollCallbacks scrollCallbacks) {
        adapter.setRecyclerViewScrollCallbacks(scrollCallbacks);
    }
}
