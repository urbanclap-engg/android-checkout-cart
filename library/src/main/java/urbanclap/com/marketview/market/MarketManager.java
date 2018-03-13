package urbanclap.com.marketview.market;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import urbanclap.com.marketview.cart.ICart;
import urbanclap.com.marketview.market.interfaces.IStickyManager;
import urbanclap.com.marketview.market.interfaces.IStickyView;
import urbanclap.com.marketview.navigation_bar.INavigationBar;
import urbanclap.com.marketview.navigation_bar.INavigationFactory;
import urbanclap.com.marketview.navigation_bar.NavigationItemView;
import urbanclap.com.marketview.navigation_bar.NavigationItemViewHolder;
import urbanclap.com.marketview.navigation_bar.Routable;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:50 PM
 */

public abstract class MarketManager<IT, NT, CT> implements NavigationItemView.OnNavigateCallback {

    @NonNull
    private List<Section<IT>> sections;

    @Nullable
    private INavigationBar navigationBar;
    @Nullable
    private INavigationFactory<NT> navigationFactory;
    @Nullable
    private IStickyView stickyView;
    @Nullable
    private IStickyManager stickyManager;
    @Nullable
    private ICart<CT> cart;

    public MarketManager(@NonNull Config<IT, NT, CT> config) {

        if (config.sections == null)
            throw new IllegalStateException("Sections cannot be null for the Market Manager initialisation");

        this.sections = config.sections;
        this.navigationBar = config.navigationBar;
        this.navigationFactory = config.navigationFactory;
        this.stickyView = config.stickyView;
        this.stickyManager = config.stickyManager;
        this.cart = config.cart;

        initManagement();
    }

    private void initManagement() {
        initNavigationManagement();
        initStickyManager(stickyView, stickyManager);
        showItems(sections);
        manageCart(cart);
    }

    @NonNull
    private List<Routable<NT>> getRoutableItemList() {
        List<Routable<NT>> routables = new ArrayList<>();
        for (Section<IT> section : sections) {
            for (ItemData<IT> itemData : section.getItemDataList()) {
                if (itemData instanceof Routable)
                    //noinspection unchecked
                    routables.add((Routable<NT>) itemData);
            }
        }
        return routables;
    }

    protected void initNavigationManagement() {
        if (navigationBar == null || navigationFactory == null)
            return;

        List<Routable<NT>> routables = getRoutableItemList();
        for (Routable<NT> routable : routables) {
            NavigationItemViewHolder<NT> navigationItemViewHolder =
                    navigationFactory.createViewHolder(routable.getRouteViewType());
            navigationItemViewHolder.bind(routable, this, routable.getRouteUUID());
            navigationBar.addView(navigationItemViewHolder.getNavigationItemView());
        }
    }

    abstract void initStickyManager(@Nullable IStickyView stickyView, @Nullable IStickyManager stickyManager);

    abstract void showItems(@NonNull List<Section<IT>> sections);

    abstract void manageCart(ICart<CT> cart);

    @Override
    public abstract void navigateTo(@NonNull String id);

    public abstract void addSection(@NonNull Section<IT> section);

    public abstract boolean removeSection(@NonNull String sectionId);


    public static class Config<IT, NT, CT> {

        @Nullable
        private INavigationBar navigationBar;
        @Nullable
        private INavigationFactory<NT> navigationFactory;
        @Nullable
        private List<Section<IT>> sections;
        @Nullable
        private IStickyView stickyView;
        @Nullable
        private IStickyManager stickyManager;
        @Nullable
        private ICart<CT> cart;


        public Config() {
        }

        public Config<IT, NT, CT> setNavigator(@Nullable INavigationBar navigationBar, @Nullable INavigationFactory<NT> navigationFactory) {
            this.navigationBar = navigationBar;
            this.navigationFactory = navigationFactory;
            return this;
        }

        public Config<IT, NT, CT> setSections(@NonNull List<Section<IT>> sections) {
            this.sections = sections;
            return this;
        }

        public Config<IT, NT, CT> setSticky(@Nullable IStickyView stickyView, @Nullable IStickyManager stickyManager) {
            this.stickyView = stickyView;
            this.stickyManager = stickyManager;
            return this;
        }

        public Config<IT, NT, CT> setCart(@Nullable ICart<CT> cart) {
            this.cart = cart;
            return this;
        }
    }


}
