package urbanclap.com.marketviewsample.market;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.TextView;

import urbanclap.com.marketview.frame_work.navigation_bar.NavigationItemView;
import urbanclap.com.marketview.frame_work.navigation_bar.NavigationItemViewHolder;
import urbanclap.com.marketviewsample.R;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 15 Mar 2018 7:48 PM
 */


public class PokemonNavigationViewHolder extends NavigationItemViewHolder<String> {

    PokemonNavigationViewHolder(int layoutId, @NonNull ViewGroup parent) {
        super(layoutId, parent);
    }

    @Override
    public void onBind(String viewModel) {
        // TODO: 15/Mar/18 @adnaan: pass view as parameter.. change the name to onViewInflated
        NavigationItemView itemView = getNavigationItemView();
        TextView tvTitle = itemView.findViewById(R.id.tv_nav);
        tvTitle.setText(viewModel.charAt(0));
    }
}
