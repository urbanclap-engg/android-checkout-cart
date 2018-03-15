package urbanclap.com.marketviewsample.market;

import android.view.View;
import android.widget.TextView;

import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerItemViewHolder;
import urbanclap.com.marketviewsample.R;
import urbanclap.com.marketviewsample.entity.PokemonItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 15 Mar 2018 2:40 PM
 */


public class ItemViewHolder extends RecyclerItemViewHolder<PokemonItem, Void> {

    private TextView tvId;
    private TextView tvName;
    private TextView tvDesc;

    public ItemViewHolder(View itemView) {
        super(itemView);
        tvId = itemView.findViewById(R.id.tv_item_id);
        tvName = itemView.findViewById(R.id.tv_item_name);
        tvDesc = itemView.findViewById(R.id.tv_item_desc);
    }

    @Override
    public void onBindView(PokemonItem viewModel) {
        tvId.setText("# " + viewModel.getUuid());
        tvName.setText(viewModel.getName());
        tvDesc.setText(viewModel.getDescription());
    }

    @Override
    public void addToCart() {
        // nothing to do...
    }
}
