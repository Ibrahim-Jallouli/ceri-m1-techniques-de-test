package fr.univavignon.pokedex.api.impl;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

import java.util.HashMap;
import java.util.Map;

/**
 * Fournit les métadonnées des Pokémon en implémentant l'interface {@link IPokemonMetadataProvider}.
 * Stocke et récupère les informations de métadonnées pour chaque Pokémon identifié par son index unique.
 * Les métadonnées incluent des détails tels que le nom, l'attaque, la défense, et l'endurance de chaque Pokémon.
 */

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

