package urbanclap.com.marketview.frame_work.navigation_bar;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 2:40 PM
 */


public class NavigationBarGenerator<T> {

    @NonNull
    private INavigationBar navigationBar;
    @NonNull
    private INavigationFactory<T> navigationFactory;

    public NavigationBarGenerator(@NonNull INavigationBar navigationBar,
                                  @NonNull INavigationFactory<T> navigationFactory) {
        this.navigationBar = navigationBar;
        this.navigationFactory = navigationFactory;
    }

    public void generate(@NonNull List<Routable<T>> routables) {
        for (Routable<T> routable : routables) {
            addView(routable);
        }
    }

    public void addView(@NonNull Routable<T> routable) {
        NavigationItemViewHolder<T> viewHolder =
                navigationFactory.createViewHolder(routable.getRouteViewType(), navigationBar.getView().getContext());
        viewHolder.onBind(routable.getRouteViewModel());
        navigationBar.addView(viewHolder.getNavigationItemView());
    }

    public void addView(@NonNull Routable<T> routable, int pos) {
        NavigationItemViewHolder<T> viewHolder =
                navigationFactory.createViewHolder(routable.getRouteViewType(), navigationBar.getView().getContext());
        viewHolder.onBind(routable.getRouteViewModel());
        navigationBar.addView(viewHolder.getNavigationItemView(), pos);
    }

    public void updateView(@NonNull Routable<T> routable, int pos) {
        NavigationItemViewHolder<T> viewHolder =
                navigationFactory.createViewHolder(routable.getRouteViewType(), navigationBar.getView().getContext());
        viewHolder.onBind(routable.getRouteViewModel());
        navigationBar.updateView(viewHolder.getNavigationItemView(), pos);
    }

    public void removeView(int pos) {
        navigationBar.removeView(pos);
    }
}
