package urbanclap.com.marketviewsample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import urbanclap.com.marketview.frame_work.market.ItemData;
import urbanclap.com.marketview.frame_work.market.MarketManager;
import urbanclap.com.marketview.frame_work.market.Section;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerMarketView;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerViewItemFactory;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerViewItemViewHolder;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerViewMarketManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerMarketView marketView = findViewById(R.id.market);
        ItemData<String> itemData = new ItemData<String>() {
            @Override
            public String getViewModel() {
                return "wow";
            }

            @Override
            public String getUUID() {
                return "0";
            }

            @Override
            public int getViewType() {
                return 0;
            }
        };
        ItemData<String> itemData1 = new ItemData<String>() {
            @Override
            public String getViewModel() {
                return "wow1";
            }

            @Override
            public String getUUID() {
                return "1";
            }

            @Override
            public int getViewType() {
                return 0;
            }
        };

        Section<String> section = new Section<>("first", Arrays.asList(itemData, itemData1));
        List<Section<String>> sections = new ArrayList<>();
        sections.add(section);
        MarketManager.Config<String, Void, Void> config = new MarketManager.Config<String, Void, Void>()
                .setSections(sections);

        RecyclerViewItemFactory<String, Void> itemFactory = new RecyclerViewItemFactory<String, Void>() {
            @Override
            public RecyclerViewItemViewHolder<String, Void> createViewHolder(
                    @NonNull Context context,
                    @NonNull ViewGroup parent,
                    int viewType) {
                return new ViewHolder(new TextView(context));
            }
        };

        MarketManager<String, Void, Void> marketManager = new RecyclerViewMarketManager<>(
                this,
                config,
                itemFactory
        );
        marketView.bindMarketManager(marketManager);
    }

    class ViewHolder extends RecyclerViewItemViewHolder<String, Void> {

        private TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        @Override
        public void onBindView(String viewModel) {
            textView.setText(viewModel);
        }

        @Override
        public void addToCart() {
            // nothing..
        }
    }
}
