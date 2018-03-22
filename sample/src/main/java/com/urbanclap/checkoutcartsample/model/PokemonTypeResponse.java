package com.urbanclap.checkoutcartsample.model;

import java.util.List;

public class PokemonTypeResponse {
    private List<PokemonTypesItem> pokemonsTypes;

    public PokemonTypeResponse(List<PokemonTypesItem> pokemonsTypes) {
        this.pokemonsTypes = pokemonsTypes;
    }

    public List<PokemonTypesItem> getPokemonsTypes() {
        return pokemonsTypes;
    }

    @Override
    public String toString() {
        return
                "PokemonTypeResponse{" +
                        "pokemons_types = '" + pokemonsTypes + '\'' +
                        "}";
    }
}