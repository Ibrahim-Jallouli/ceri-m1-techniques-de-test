package fr.univavignon.pokedex.api.impl;
import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;

/**
 * La fabrique pour créer des instances de {@link IPokedex}.
 * Cette classe fournit une implémentation de l'interface {@link IPokedexFactory},
 * permettant la création d'objets {@link IPokedex} configurés avec les fournisseurs
 * de métadonnées et de fabrication de Pokémon spécifiés.
 */
public class PokedexFactory implements IPokedexFactory {

    private  IPokemonMetadataProvider metadataProvider;
    private  IPokemonFactory pokemonFactory;

    public PokedexFactory(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        return new Pokedex(metadataProvider, pokemonFactory);
    }
}
