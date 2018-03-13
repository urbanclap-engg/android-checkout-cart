package urbanclap.com.marketview.navigation_bar;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 2:17 PM
 */


public abstract class NavigationItemViewHolder<T> {

    @NonNull
    private NavigationItemView navigationItemView;

    public NavigationItemViewHolder(int layoutId, @NonNull ViewGroup parent) {
        navigationItemView = new NavigationItemView(parent.getContext());
        navigationItemView.setContentView(layoutId, parent);
    }

    public void bind(@NonNull Routable<T> routable,
                     @NonNull NavigationItemView.OnNavigateCallback callback,
                     @NonNull String id) {
        navigationItemView.setCallback(callback, id);
        onBind(routable.getRouteViewModel());
    }

    public abstract void onBind(T viewModel);

    @NonNull
    public NavigationItemView getNavigationItemView() {
        return navigationItemView;
    }
}
