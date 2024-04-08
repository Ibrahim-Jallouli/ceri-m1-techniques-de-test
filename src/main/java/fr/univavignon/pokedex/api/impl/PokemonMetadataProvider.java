package fr.univavignon.pokedex.api.impl;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Fournit les métadonnées des Pokémon en implémentant l'interface {@link IPokemonMetadataProvider}.
 * Stocke et récupère les informations de métadonnées pour chaque Pokémon identifié par son index unique.
 * Les métadonnées incluent des détails tels que le nom, l'attaque, la défense, et l'endurance de chaque Pokémon.
 */

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private Map<Integer, PokemonMetadata> pokemonMetadataMap;

    public PokemonMetadataProvider() {
        this.pokemonMetadataMap = new HashMap<>();
        // Example to initialize with some metadata
        pokemonMetadataMap.put(1, new PokemonMetadata(1, "Bulbasaur", 118, 118, 90));
        pokemonMetadataMap.put(2, new PokemonMetadata(2, "Ivysaur", 151, 151, 120));
        pokemonMetadataMap.put(3, new PokemonMetadata(3, "Venusaur", 198, 198, 160));
        pokemonMetadataMap.put(4, new PokemonMetadata(4, "Charmander", 116, 96, 78));
        pokemonMetadataMap.put(5, new PokemonMetadata(5, "Charmeleon", 158, 129, 116));
        pokemonMetadataMap.put(6, new PokemonMetadata(6, "Charizard", 223, 176, 156));
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (!pokemonMetadataMap.containsKey(index)) {
            throw new PokedexException("Invalid Pokémon index: " + index);
        }
        return pokemonMetadataMap.get(index);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokemonMetadataProvider)) return false;

        PokemonMetadataProvider that = (PokemonMetadataProvider) o;

        return pokemonMetadataMap != null ? pokemonMetadataMap.equals(that.pokemonMetadataMap) : that.pokemonMetadataMap == null;
    }

    @Override
    public int hashCode() {
        return pokemonMetadataMap != null ? pokemonMetadataMap.hashCode() : 0;
    }
}

