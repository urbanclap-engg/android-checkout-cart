package com.urbanclap.checkoutcartsample.main_screen;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.urbanclap.checkoutcart.frame_work.market.ItemData;
import com.urbanclap.checkoutcart.frame_work.market.Section;
import com.urbanclap.checkoutcart.frame_work.sticky.IStickyViewItem;
import com.urbanclap.checkoutcartsample.R;
import com.urbanclap.checkoutcartsample.market.CartItem;
import com.urbanclap.checkoutcartsample.market.entity.PokemonCartBaseItem;
import com.urbanclap.checkoutcartsample.market.entity.PokemonCartItem;
import com.urbanclap.checkoutcartsample.market.entity.PokemonSectionItem;
import com.urbanclap.checkoutcartsample.model.PokemonItem;
import com.urbanclap.checkoutcartsample.model.PokemonTypesItem;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 21 Mar 2018 3:42 PM
 */


class MainPresenter implements MainContract.Presenter {

    @NonNull
    private MainContract.View view;
    @NonNull
    private MainContract.IJsonLoader jsonLoader;

    MainPresenter(@NonNull MainContract.View view,
                  @NonNull MainContract.IJsonLoader jsonLoader) {
        this.view = view;
        this.jsonLoader = jsonLoader;
    }

    @Override
    public void start() {
        try {
            init();
        } catch (JSONException e) {
            e.printStackTrace();
            view.showError(e.getMessage());
        }
    }

    private void init() throws JSONException {

        List<PokemonTypesItem> pokemonTypesItems = new ArrayList<>();

        String pokemonJsonStr = jsonLoader.getPokemonJsonStr();
        if (pokemonJsonStr == null) {
            view.showError("null json");
            return;
        }

        JSONObject pokemonTypeResponse = new JSONObject(pokemonJsonStr);
        JSONArray pokemonTypeArr = pokemonTypeResponse.getJSONArray("pokemons_types");
        for (int i = 0, len = pokemonTypeArr.length(); i < len; i++) {
            JSONObject pokemonType = pokemonTypeArr.getJSONObject(i);
            String id = pokemonType.getString("uuid");
            final String type = pokemonType.getString("type");
            List<PokemonItem> pokemonItems = createPokemonItemListFromJson(pokemonType.getJSONArray("pokemons"));

            pokemonTypesItems.add(new PokemonTypesItem(id, type, pokemonItems));
        }
        generateSections(pokemonTypesItems);
    }

    private void generateSections(List<PokemonTypesItem> pokemonTypesItems) {
        List<Section<PokemonCartBaseItem>> sections = new ArrayList<>();
        for (final PokemonTypesItem itr : pokemonTypesItems) {

            List<ItemData<PokemonCartBaseItem>> itemDataList = new ArrayList<>();
            PokemonSectionItem sectionItem = new PokemonSectionItem(itr.getType());
            itemDataList.add(new CartItem(sectionItem));
            addPokemonItemDataInList(itr.getPokemons(), itemDataList);
            Section<PokemonCartBaseItem> section;
            section = new Section<>(itr.getUuid(), itemDataList, new IStickyViewItem() {
                @Override
                public View createView(@NonNull ViewGroup parent) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section_layout, parent, false);
                    TextView textView = view.findViewById(R.id.tv_itemSection_title);
                    textView.setText(itr.getType());
                    return view;
                }
            });
            sections.add(section);
        }

        view.showSections(sections);
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
