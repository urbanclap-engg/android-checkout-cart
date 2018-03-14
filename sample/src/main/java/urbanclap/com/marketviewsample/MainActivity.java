package urbanclap.com.marketviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import urbanclap.com.marketview.frame_work.market.ItemData;
import urbanclap.com.marketview.frame_work.market.Section;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerMarketView;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerViewItemViewHolder;

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
    }

    class ViewHolder extends RecyclerViewItemViewHolder<String, Void> {

        private TextView textView;

        public ViewHolder(View itemView) {
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
