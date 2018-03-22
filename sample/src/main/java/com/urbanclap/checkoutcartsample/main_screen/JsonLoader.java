package com.urbanclap.checkoutcartsample.main_screen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 21 Mar 2018 3:46 PM
 */


class JsonLoader implements MainContract.IJsonLoader {

    @NonNull
    private Context context;

    public JsonLoader(@NonNull Context context) {
        this.context = context;
    }

    @Override
    @Nullable
    public String getPokemonJsonStr() {
        String json;
        try {
            InputStream is = context.getAssets().open("pokemons.json");
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
}
