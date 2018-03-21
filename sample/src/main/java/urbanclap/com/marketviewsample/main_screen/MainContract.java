package urbanclap.com.marketviewsample.main_screen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import urbanclap.com.marketview.frame_work.market.Section;
import urbanclap.com.marketviewsample.market.entity.PokemonCartBaseItem;
import urbanclap.com.marketviewsample.model.PokemonTypesItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
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
