package com.urbanclap.checkoutcartsample.model;

import java.util.List;

public class PokemonTypesItem {
    private String uuid;
    private String type;
    private List<PokemonItem> pokemons;

    public PokemonTypesItem(String uuid, String type, List<PokemonItem> pokemons) {
        this.uuid = uuid;
        this.type = type;
        this.pokemons = pokemons;
    }

    public String getUuid() {
        return uuid;
    }

    public String getType() {
        return type;
    }

    public List<PokemonItem> getPokemons() {
        return pokemons;
    }

    @Override
    public String toString() {
        return
                "PokemonsTypesItem{" +
                        "pokemons = '" + pokemons + '\'' +
                        ",type = '" + type + '\'' +
                        ",uuid = '" + uuid + '\'' +
                        "}";
    }
}