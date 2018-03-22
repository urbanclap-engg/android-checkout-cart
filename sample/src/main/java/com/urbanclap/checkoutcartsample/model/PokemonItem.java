package com.urbanclap.checkoutcartsample.model;

public class PokemonItem {

    private int uuid;
    private String name;
    private String description;

    public PokemonItem(int uuid, String name, String description) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
    }

    public int getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return
                "PokemonsItem{" +
                        "uuid = '" + uuid + '\'' +
                        ",name = '" + name + '\'' +
                        ",description = '" + description + '\'' +
                        "}";
    }
}
