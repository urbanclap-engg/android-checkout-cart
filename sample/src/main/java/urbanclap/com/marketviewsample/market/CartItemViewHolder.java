package urbanclap.com.marketviewsample.market;

import android.view.View;
import android.widget.TextView;

import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerItemViewHolder;
import urbanclap.com.marketview.utils.DefaultCartItem;
import urbanclap.com.marketviewsample.R;
import urbanclap.com.marketviewsample.market.entity.PokemonCartBaseItem;
import urbanclap.com.marketviewsample.market.entity.PokemonCartItem;
import urbanclap.com.marketviewsample.model.PokemonItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 15 Mar 2018 2:40 PM
 */


public class CartItemViewHolder extends RecyclerItemViewHolder<PokemonCartBaseItem, DefaultCartItem<PokemonItem>> {

    private TextView tvId;
    private TextView tvName;
    private TextView tvDesc;

    CartItemViewHolder(final View itemView) {
        super(itemView);
        tvId = itemView.findViewById(R.id.tv_item_id);
        tvName = itemView.findViewById(R.id.tv_item_name);
        tvDesc = itemView.findViewById(R.id.tv_item_desc);
    }

    @Override
    public void onBindView(final PokemonCartBaseItem vm) {
        final PokemonCartItem viewModel = (PokemonCartItem) vm;
        tvId.setText("# " + viewModel.getUuid());
        tvName.setText(viewModel.getName());
        tvDesc.setText(viewModel.getDescription());
        PokemonItem pokemonItem = new PokemonItem(viewModel.getUuid(), viewModel.getName(), viewModel.getDescription());
        final DefaultCartItem<PokemonItem> cartItem = new DefaultCartItem<>(1, 5.0, pokemonItem);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment(vm.id(), cartItem);
            }
        });
    }

}
