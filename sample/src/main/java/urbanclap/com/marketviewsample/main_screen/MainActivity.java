package urbanclap.com.marketviewsample.main_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import urbanclap.com.marketview.frame_work.market.MarketManager;
import urbanclap.com.marketview.frame_work.market.Section;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerMarketManager;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerMarketView;
import urbanclap.com.marketview.utils.DefaultCart;
import urbanclap.com.marketview.utils.DefaultCartItem;
import urbanclap.com.marketview.utils.MarketUtils;
import urbanclap.com.marketviewsample.R;
import urbanclap.com.marketviewsample.market.ItemFactory;
import urbanclap.com.marketviewsample.market.NavigationItemFactory;
import urbanclap.com.marketviewsample.market.entity.PokemonCartBaseItem;
import urbanclap.com.marketviewsample.model.PokemonItem;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private RecyclerMarketView marketView;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        marketView = findViewById(R.id.market);
        this.presenter = new MainPresenter(this, new JsonLoader(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void showSections(@NonNull List<Section<PokemonCartBaseItem>> sections) {
        MarketManager.Config<PokemonCartBaseItem, String, DefaultCartItem<PokemonItem>> config =
                new MarketManager.Config<>();

        config.setCart(new DefaultCart<PokemonItem>())
                .setSections(sections)
                .setSticky(MarketUtils.getDefaultStickyView(this))
                .setNavigator(
                        MarketUtils.getDefaultHorizontalNavigationBar(this),
                        new NavigationItemFactory()
                );
        marketView.bindMarketManager(new RecyclerMarketManager<>(this, config, new ItemFactory()));
    }

    @Override
    public void showError(@NonNull String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
