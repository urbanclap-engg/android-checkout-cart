package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import urbanclap.com.marketview.frame_work.cart.CartCallback;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 13 Mar 2018 3:40 PM
 */


class RecyclerViewAdapter<IT, CT> extends RecyclerView.Adapter<RecyclerItemViewHolder<IT, CT>> {

    @NonNull
    private ItemPool<IT> itemPool;
    @NonNull
    private RecyclerItemFactory<IT, CT> itemFactory;
    @NonNull
    private CartCallback<CT> cartCallback;

    @Nullable
    private ScrollCallback scrollCallback;
    @Nullable
    private RecyclerViewScrollCallbacks recyclerViewScrollCallbacks;

    RecyclerViewAdapter(@NonNull ItemPool<IT> itemPool,
                        @NonNull RecyclerItemFactory<IT, CT> itemFactory,
                        @NonNull CartCallback<CT> cartCallback) {
        this.itemPool = itemPool;
        this.itemFactory = itemFactory;
        this.cartCallback = cartCallback;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (recyclerViewScrollCallbacks != null)
                    recyclerViewScrollCallbacks.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (scrollCallback != null)
                    scrollCallback.onScrollPosition(layoutManager.findFirstVisibleItemPosition());

                if (recyclerViewScrollCallbacks != null) {
                    recyclerViewScrollCallbacks.onScrolled(recyclerView, dx, dy);

                    int firstCompletelyVisiblePos = layoutManager.findFirstCompletelyVisibleItemPosition();
                    int lastCompletelyVisiblePos = layoutManager.findLastCompletelyVisibleItemPosition();
                    int firstVisiblePos = layoutManager.findFirstVisibleItemPosition();
                    int lastVisiblePos = layoutManager.findLastVisibleItemPosition();

                    firstCompletelyVisiblePos = validatePos(firstCompletelyVisiblePos);
                    lastCompletelyVisiblePos = validatePos(lastCompletelyVisiblePos);
                    firstVisiblePos = validatePos(firstVisiblePos);
                    lastVisiblePos = validatePos(lastVisiblePos);

                    recyclerViewScrollCallbacks.firstCompletelyVisibleItem(
                            firstCompletelyVisiblePos,
                            findItemAtPos(firstCompletelyVisiblePos)
                    );

                    recyclerViewScrollCallbacks.lastCompletelyVisibleItem(
                            lastCompletelyVisiblePos,
                            findItemAtPos(lastCompletelyVisiblePos)
                    );

                    recyclerViewScrollCallbacks.firstVisibleItem(
                            firstVisiblePos,
                            findItemAtPos(firstVisiblePos)
                    );

                    recyclerViewScrollCallbacks.lastVisibleItem(
                            lastVisiblePos,
                            findItemAtPos(lastVisiblePos)
                    );
                }
            }
        });
    }

    @Override
    public RecyclerItemViewHolder<IT, CT> onCreateViewHolder(ViewGroup parent, int viewType) {
        return itemFactory.create(parent.getContext(), parent, viewType, cartCallback);
    }

    @Override
    public void onBindViewHolder(RecyclerItemViewHolder<IT, CT> holder, int position) {
        if (holder == null)
            return;

        holder.onBindView(itemPool.getItemDataList().get(position).getViewModel());
    }

    @Override
    public int getItemCount() {
        return itemPool.getItemDataList().size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemPool.getItemDataList().get(position).getViewType();
    }


    private int validatePos(int position) {
        return position >= 0 && position < itemPool.getItemDataList().size() ? position : -1;
    }

    @Nullable
    private String findItemAtPos(int position) {
        if (position == -1)
            return null;
        return itemPool.getItemDataList().get(position).getUUID();
    }

    void setScrollCallback(@Nullable ScrollCallback scrollCallback) {
        this.scrollCallback = scrollCallback;
    }

    void setRecyclerViewScrollCallbacks(@Nullable RecyclerViewScrollCallbacks recyclerViewScrollCallbacks) {
        this.recyclerViewScrollCallbacks = recyclerViewScrollCallbacks;
    }

    interface ScrollCallback {
        void onScrollPosition(int pos);
    }
}
