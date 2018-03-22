package com.urbanclap.checkoutcart.market_impl.recycler_view_market;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.urbanclap.checkoutcart.frame_work.cart.CartCallback;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
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
    @NonNull
    private List<ScrollCallback> scrollCallbackList;

    @Nullable
    private RecyclerViewScrollCallbacks recyclerViewScrollCallbacks;

    RecyclerViewAdapter(@NonNull ItemPool<IT> itemPool,
                        @NonNull RecyclerItemFactory<IT, CT> itemFactory,
                        @NonNull CartCallback<CT> cartCallback) {
        this.itemPool = itemPool;
        this.itemFactory = itemFactory;
        this.cartCallback = cartCallback;
        this.scrollCallbackList = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            String firstCompletelyVisibleSection = "";
            String firstVisibleSection = "";
            String lastCompletelyVisibleSection = "";
            String lastVisibleSection = "";

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (recyclerViewScrollCallbacks != null)
                    recyclerViewScrollCallbacks.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                for (ScrollCallback scrollCallback : scrollCallbackList)
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

                    String id = findSectionAtPos(firstCompletelyVisiblePos);
                    if (!(id.equals(firstCompletelyVisibleSection))) {
                        firstCompletelyVisibleSection = id;
                        recyclerViewScrollCallbacks.firstCompletelyVisibleSection(id);
                    }

                    id = findSectionAtPos(firstVisiblePos);
                    if (!(id.equals(firstVisibleSection))) {
                        firstVisibleSection = id;
                        recyclerViewScrollCallbacks.firstVisibleSection(id);
                    }

                    id = findSectionAtPos(lastCompletelyVisiblePos);
                    if (!(id.equals(lastCompletelyVisibleSection))) {
                        lastCompletelyVisibleSection = id;
                        recyclerViewScrollCallbacks.lastCompletelyVisibleSection(id);
                    }

                    id = findSectionAtPos(lastVisiblePos);
                    if (!(id.equals(lastVisibleSection))) {
                        lastVisibleSection = id;
                        recyclerViewScrollCallbacks.lastVisibleSection(id);
                    }
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

    @NonNull
    private String findItemAtPos(int position) {
        return position != -1 ? itemPool.getItemDataList().get(position).getUUID() : "";
    }

    @NonNull
    private String findSectionAtPos(int position) {
        return position != -1 ? itemPool.getSectionIdAt(position) : "";
    }

    void addScrollCallback(@NonNull ScrollCallback scrollCallback) {
        scrollCallbackList.add(scrollCallback);
    }

    void removeScrollCallback(@NonNull ScrollCallback scrollCallback) {
        scrollCallbackList.remove(scrollCallback);
    }

    void setRecyclerViewScrollCallbacks(@Nullable RecyclerViewScrollCallbacks recyclerViewScrollCallbacks) {
        this.recyclerViewScrollCallbacks = recyclerViewScrollCallbacks;
    }

    interface ScrollCallback {
        void onScrollPosition(int pos);
    }
}
