package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import urbanclap.com.marketview.frame_work.cart.CartCallback;
import urbanclap.com.marketview.frame_work.market.MarketManager;
import urbanclap.com.marketview.frame_work.market.Section;
import urbanclap.com.marketview.frame_work.market.interfaces.IMarketView;
import urbanclap.com.marketview.frame_work.market.interfaces.IStickyManager;
import urbanclap.com.marketview.frame_work.market.interfaces.IStickyView;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 13 Mar 2018 4:16 PM
 */


public class RecyclerViewMarketManager<IT, NT, CT> extends MarketManager<IT, NT, CT> implements CartCallback<CT> {

    // TODO: 13/Mar/18 @adnaan: add views..

    @NonNull
    private RecyclerView recyclerView;
    @NonNull
    private ItemPool<IT> itemPool;
    @NonNull
    private RecyclerViewAdapter<IT, CT> adapter;

    @Nullable
    private IMarketView marketView;

    public RecyclerViewMarketManager(@NonNull Config<IT, NT, CT> config,
                                     @NonNull RecyclerView recyclerView,
                                     @NonNull RecyclerViewItemFactory<IT, CT> itemFactory) {
        super(config);
        this.recyclerView = recyclerView;
        this.itemPool = new ItemPool<>(sections);
        adapter = new RecyclerViewAdapter<>(this.itemPool, itemFactory, this);
    }

    @Override
    protected void showItems() {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initStickyManager(@Nullable final IStickyView stickyView, @Nullable final IStickyManager stickyManager) {
        if (stickyView == null || stickyManager == null)
            return;

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
            if (itemPool.getItemDataList().get(pos).getUUID().equals(id)) {
                pos = i;
                break;
            }
        }

        if (pos != -1)
            recyclerView.smoothScrollToPosition(pos);
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

    @Override
    public void bindMarketManager(@NonNull IMarketView marketView) {
        this.marketView = marketView;
    }
}
