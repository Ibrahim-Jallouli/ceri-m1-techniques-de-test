package fr.univavignon.pokedex.api.impl;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

import java.util.HashMap;
import java.util.Map;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private Map<Integer, PokemonMetadata> pokemonMetadataMap;

    public PokemonMetadataProvider() {
        this.pokemonMetadataMap = new HashMap<>();
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (!pokemonMetadataMap.containsKey(index)) {
            throw new PokedexException("Invalid Pokémon index: " + index);
        }
        return pokemonMetadataMap.get(index);
    }
}

