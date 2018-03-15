package urbanclap.com.marketviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerMarketView;
import urbanclap.com.marketview.market_impl.recycler_view_market.RecyclerViewMarketManager;
import urbanclap.com.marketviewsample.entity.PokemonItem;
import urbanclap.com.marketviewsample.market.ItemFactory;
import urbanclap.com.marketviewsample.market.MarketItem;

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

        List<Section<PokemonItem>> sections = new ArrayList<>();

        JSONObject pokemonTypeResponse = new JSONObject(pokemonJsonStr);
        JSONArray pokemonTypeArr = pokemonTypeResponse.getJSONArray("pokemons_types");
        for (int i = 0, len = pokemonTypeArr.length(); i < len; i++) {
            JSONObject pokemonType = pokemonTypeArr.getJSONObject(i);
            String id = pokemonType.getString("uuid");
            List<PokemonItem> pokemonItems = createPokemonItemListFromJson(pokemonType.getJSONArray("pokemons"));
            List<ItemData<PokemonItem>> itemDataList = createItemDataList(pokemonItems);
            sections.add(new Section<>(id, itemDataList));
        }

        MarketManager.Config<PokemonItem, Void, Void> config = new MarketManager.Config<>();
        config.setSections(sections);

        MarketManager<PokemonItem, Void, Void> marketManager =
                new RecyclerViewMarketManager<>(this, config, new ItemFactory());

        marketView.bindMarketManager(marketManager);
    }

    private List<ItemData<PokemonItem>> createItemDataList(List<PokemonItem> pokemonItems) {
        List<ItemData<PokemonItem>> list = new ArrayList<>();
        for (PokemonItem pokemonItem : pokemonItems) {
            list.add(new MarketItem(pokemonItem));
        }
        return list;
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
