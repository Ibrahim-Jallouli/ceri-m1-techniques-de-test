package fr.univavignon.pokedex.api.impl;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;
import fr.univavignon.pokedex.api.IPokedexFactory;

/**
 * Fabrique pour créer des instances de {@link PokemonTrainer}.
 * Cette classe permet la création de dresseurs de Pokémon, assignés à une équipe spécifique et munis d'un Pokédex.
 * Le Pokédex fourni est généré via une fabrique de Pokédex, configurée avec les fournisseurs de métadonnées et de fabrication de Pokémon spécifiés.
 */

public class PokemonTrainerFactory implements IPokemonTrainerFactory {

    private final IPokemonMetadataProvider metadataProvider;
    private final IPokemonFactory pokemonFactory;

    public PokemonTrainerFactory(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        return new PokemonTrainer(name, team, pokedex);
    }
}
