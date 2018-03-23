package com.urbanclap.checkoutcartsample.market;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.urbanclap.checkoutcart.frame_work.navigation_bar.NavigationItemView;
import com.urbanclap.checkoutcart.frame_work.navigation_bar.NavigationItemViewHolder;
import com.urbanclap.checkoutcartsample.R;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 15 Mar 2018 7:48 PM
 */


public class PokemonNavigationViewHolder extends NavigationItemViewHolder<String> {

    PokemonNavigationViewHolder(int layoutId, @NonNull Context context) {
        super(layoutId, context);
    }

    @Override
    public void onBind(String viewModel) {
        NavigationItemView itemView = getNavigationItemView();
        TextView tvTitle = itemView.findViewById(R.id.tv_nav);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "you clicked and it still scrolls.... wow", Toast.LENGTH_SHORT).show();
            }
        });
        tvTitle.setText("" + viewModel.charAt(0));
    }
}
