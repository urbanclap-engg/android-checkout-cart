package com.urbanclap.checkoutcart.frame_work.market;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.urbanclap.checkoutcart.frame_work.cart.ICart;
import com.urbanclap.checkoutcart.frame_work.market.interfaces.IMarketView;
import com.urbanclap.checkoutcart.frame_work.navigation_bar.INavigationBar;
import com.urbanclap.checkoutcart.frame_work.navigation_bar.INavigationFactory;
import com.urbanclap.checkoutcart.frame_work.navigation_bar.NavigationItemView;
import com.urbanclap.checkoutcart.frame_work.navigation_bar.NavigationItemViewHolder;
import com.urbanclap.checkoutcart.frame_work.navigation_bar.OnNavigationItemSelectCallback;
import com.urbanclap.checkoutcart.frame_work.navigation_bar.Routable;
import com.urbanclap.checkoutcart.frame_work.sticky.IStickyView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:50 PM
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class MarketManager<IT, NT, CT> implements NavigationItemView.OnNavigateCallback {

    @NonNull
    protected List<Section<IT>> sections;
    @NonNull
    private List<Routable<NT>> routables;

    @Nullable
    protected INavigationBar navigationBar;
    @Nullable
    protected INavigationFactory<NT> navigationFactory;
    @Nullable
    protected IStickyView stickyView;
    @Nullable
    protected ICart<CT> cart;
    @Nullable
    protected OnNavigationItemSelectCallback<NT> navigationItemSelectCallback;

    protected boolean isAutoNavBarItemScrollEnabled;


    public MarketManager(@NonNull Config<IT, NT, CT> config) {

        this.sections = config.sections;
        this.routables = generateRoutableList();
        this.navigationBar = config.navigationBar;
        this.navigationFactory = config.navigationFactory;
        this.stickyView = config.stickyView;
        this.cart = config.cart;
        this.navigationItemSelectCallback = config.navigationItemSelectCallback;
        this.isAutoNavBarItemScrollEnabled = config.isAutoNavBarItemScrollEnabled;
    }

    private void initManagement() {
        initSectionsManager();
        initStickyManager();
        initNavigationManagement();
        navigationManagement();
    }

    @NonNull
    private List<Routable<NT>> generateRoutableList() {
        List<Routable<NT>> routables = new ArrayList<>();
        for (Section<IT> section : sections) {
            for (ItemData<IT> itemData : section.getItemDataList()) {
                if (itemData instanceof Routable)
                    //noinspection unchecked
                    routables.add((Routable<NT>) itemData);
                else if (itemData.getViewModel() instanceof Routable)
                    //noinspection unchecked
                    routables.add((Routable<NT>) itemData.getViewModel());
            }
        }
        return routables;
    }

    protected void navigationManagement() {
        if (navigationBar == null || navigationFactory == null)
            return;
        navigationBar.clear();
        generateRoutableList();

        for (Routable<NT> routable : routables) {
            NavigationItemViewHolder<NT> navigationItemViewHolder =
                    navigationFactory.createViewHolder(routable.getRouteViewType(),
                            navigationBar.getView().getContext());
            navigationItemViewHolder.bind(routable, this, routable.getRouteUUID());
            navigationBar.addView(navigationItemViewHolder.getNavigationItemView());
        }
    }

    protected void addSection(@NonNull Section<IT> section) {
        addSections(Collections.singletonList(section));
    }

    protected void addSections(@NonNull List<Section<IT>> sections) {
        handleAddSections(sections);
        navigationManagement();
    }

    protected void removeSection(@NonNull String sectionId) {
        removeSections(Collections.singletonList(sectionId));
    }

    protected void removeSections(@NonNull List<String> sectionIds) {
        handleRemoveSections(sectionIds);
        navigationManagement();
    }

    public void bindMarketManager(@NonNull IMarketView marketView) {
        handleBindMarketManager(marketView);
        initManagement();
    }

    @Override
    public void navigateTo(@NonNull String id) {
        if (navigationItemSelectCallback != null)
            navigationItemSelectCallback.onItemSelect(id, routables);
        handleNavigateTo(id);
    }

    public void updateNavigationBar(List<Routable<NT>> routables) {
        this.routables = routables;
        navigationManagement();
    }

    @NonNull
    public List<Routable<NT>> getRoutables() {
        return this.routables;
    }

    protected abstract void initSectionsManager();

    protected abstract void initStickyManager();

    protected abstract void initNavigationManagement();

    public abstract void handleNavigateTo(@NonNull String id);

    public abstract void handleAddSections(@NonNull List<Section<IT>> sectionList);

    public abstract void handleRemoveSections(@NonNull List<String> sectionIds);

    public abstract boolean hasSection(@NonNull String sectionId);

    public abstract boolean hasItem(@NonNull String sectionId, @NonNull String itemId);

    public abstract void updateItem(@NonNull String sectionId, ItemData<IT> item);

    protected abstract void handleBindMarketManager(@NonNull IMarketView marketView);


    @SuppressWarnings("UnusedReturnValue")
    public static class Config<IT, NT, CT> {

        @NonNull
        private List<Section<IT>> sections;
        @Nullable
        private INavigationBar navigationBar;
        @Nullable
        private INavigationFactory<NT> navigationFactory;
        @Nullable
        private IStickyView stickyView;
        @Nullable
        private ICart<CT> cart;
        @Nullable
        private OnNavigationItemSelectCallback<NT> navigationItemSelectCallback;
        private boolean isAutoNavBarItemScrollEnabled = false;

        public Config(@NonNull List<Section<IT>> sections) {
            this.sections = sections;
        }

        public Config<IT, NT, CT> setNavigator(@Nullable INavigationBar navigationBar, @Nullable INavigationFactory<NT> navigationFactory) {
            this.navigationBar = navigationBar;
            this.navigationFactory = navigationFactory;
            return this;
        }

        public Config<IT, NT, CT> setSticky(@Nullable IStickyView stickyView) {
            this.stickyView = stickyView;
            return this;
        }

        public Config<IT, NT, CT> setCart(@Nullable ICart<CT> cart) {
            this.cart = cart;
            return this;
        }

        public Config<IT, NT, CT> setNavigationCallback(@Nullable OnNavigationItemSelectCallback<NT> navigationItemSelectCallback) {
            this.navigationItemSelectCallback = navigationItemSelectCallback;
            return this;
        }

        public Config<IT, NT, CT> enableAutoNavBarScroll(boolean enable) {
            this.isAutoNavBarItemScrollEnabled = enable;
            return this;
        }

    }
}
