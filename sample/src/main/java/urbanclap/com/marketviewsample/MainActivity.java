package urbanclap.com.marketviewsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import urbanclap.com.marketview.frame_work.market.ItemData;
import urbanclap.com.marketview.frame_work.market.MarketManager;
import urbanclap.com.marketview.frame_work.market.Section;
import urbanclap.com.marketview.frame_work.sticky.IStickyViewItem;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerMarketManager;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerMarketManagerUtils;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerMarketView;
import urbanclap.com.marketviewsample.market.CartItem;
import urbanclap.com.marketviewsample.market.ItemFactory;
import urbanclap.com.marketviewsample.market.NavigationItemFactory;
import urbanclap.com.marketviewsample.market.PokemonCart;
import urbanclap.com.marketviewsample.market.entity.PokemonCartBaseItem;
import urbanclap.com.marketviewsample.market.entity.PokemonCartItem;
import urbanclap.com.marketviewsample.market.entity.PokemonSectionItem;
import urbanclap.com.marketviewsample.model.PokemonItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerMarketView marketView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        marketView = findViewById(R.id.market);

        try {
            init();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void init() throws JSONException {
        String pokemonJsonStr = loadJson();
        if (pokemonJsonStr == null) {
            Toast.makeText(this, "null json", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Section<PokemonCartBaseItem>> sections = new ArrayList<>();

        JSONObject pokemonTypeResponse = new JSONObject(pokemonJsonStr);
        JSONArray pokemonTypeArr = pokemonTypeResponse.getJSONArray("pokemons_types");
        for (int i = 0, len = pokemonTypeArr.length(); i < len; i++) {
            JSONObject pokemonType = pokemonTypeArr.getJSONObject(i);
            String id = pokemonType.getString("uuid");
            final String type = pokemonType.getString("type");
            List<PokemonItem> pokemonItems = createPokemonItemListFromJson(pokemonType.getJSONArray("pokemons"));

            List<ItemData<PokemonCartBaseItem>> itemDataList = new ArrayList<>();
            PokemonSectionItem sectionItem = new PokemonSectionItem(type);
            itemDataList.add(new CartItem(sectionItem));

            addPokemonItemDataInList(pokemonItems, itemDataList);
            Section<PokemonCartBaseItem> section;
            if (type.equalsIgnoreCase("water"))
                section = new Section<>(id, itemDataList);
            else
                section = new Section<>(id, itemDataList, new IStickyViewItem() {
                    @Override
                    public View createView(@NonNull ViewGroup parent) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section_layout, parent, false);
                        TextView textView = view.findViewById(R.id.tv_itemSection_title);
                        textView.setText(type);
                        return view;
                    }
                });
            sections.add(section);
        }


        MarketManager.Config<PokemonCartBaseItem, String, PokemonItem> config = new MarketManager.Config<>();
        config.setSections(sections)
                .setCart(new PokemonCart())
                .setSticky(RecyclerMarketManagerUtils.getDefaultStickyView(this))
                .setNavigator(
                        RecyclerMarketManagerUtils.getDefaultHorizontalNavigationBar(this),
                        new NavigationItemFactory()
                );

        MarketManager<PokemonCartBaseItem, String, PokemonItem> marketManager =
                new RecyclerMarketManager<>(this, config, new ItemFactory());

        marketView.bindMarketManager(marketManager);
    }

    private void addPokemonItemDataInList(List<PokemonItem> pokemonItems,
                                          List<ItemData<PokemonCartBaseItem>> itemDataList) {
        for (PokemonItem pokemonItem : pokemonItems) {
            PokemonCartItem cartItem = new PokemonCartItem(
                    pokemonItem.getUuid(),
                    pokemonItem.getName(),
                    pokemonItem.getDescription()
            );
            itemDataList.add(new CartItem(cartItem));
        }
    }

    private String loadJson() {
        String json;
        try {
            InputStream is = getAssets().open("pokemons.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int val = is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.d("JSON", "" + val);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private List<PokemonItem> createPokemonItemListFromJson(JSONArray jsonArray) throws JSONException {
        List<PokemonItem> pokemonItems = new ArrayList<>();
        for (int i = 0, len = jsonArray.length(); i < len; i++) {
            JSONObject pokemon = jsonArray.getJSONObject(i);
            pokemonItems.add(createPokemonItemFromJson(pokemon));
        }
        return pokemonItems;
    }

    private PokemonItem createPokemonItemFromJson(JSONObject jsonObject) throws JSONException {
        return new PokemonItem(
                jsonObject.getInt("uuid"),
                jsonObject.getString("name"),
                jsonObject.getString("description")
        );
    }
}
