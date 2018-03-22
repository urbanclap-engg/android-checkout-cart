package com.urbanclap.checkoutcartsample.main_screen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import com.urbanclap.checkoutcart.frame_work.market.Section;
import com.urbanclap.checkoutcartsample.market.entity.PokemonCartBaseItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 21 Mar 2018 3:40 PM
 */


interface MainContract {
    interface View {

        void showSections(@NonNull List<Section<PokemonCartBaseItem>> sections);

        void showError(@NonNull String errorMsg);
    }

    interface Presenter {
        void start();
    }

    interface IJsonLoader {
        @Nullable
        String getPokemonJsonStr();
    }
}
