package urbanclap.com.marketviewsample.market;

import android.view.View;
import android.widget.TextView;

import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerItemViewHolder;
import urbanclap.com.marketviewsample.R;
import urbanclap.com.marketviewsample.market.entity.PokemonCartBaseItem;
import urbanclap.com.marketviewsample.market.entity.PokemonSectionItem;
import urbanclap.com.marketviewsample.model.PokemonItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 15 Mar 2018 4:13 PM
 */


public class SectionItemViewHolder extends RecyclerItemViewHolder<PokemonCartBaseItem, PokemonItem> {

    private TextView tvTitle;

    SectionItemViewHolder(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_itemSection_title);
    }

    @Override
    public void onBindView(PokemonCartBaseItem viewModel) {
        PokemonSectionItem sectionItem = (PokemonSectionItem) viewModel;
        tvTitle.setText(sectionItem.getTitle());
    }

}
