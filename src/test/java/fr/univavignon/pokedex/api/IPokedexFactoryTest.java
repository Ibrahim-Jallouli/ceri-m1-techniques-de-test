package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.api.impl.PokedexFactory;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Classe de test pour l'interface IPokedexFactory.
 * Utilise Mockito pour simuler le comportement des interfaces dépendantes et vérifier
 * la création correcte d'un objet Pokedex.
 */
public class IPokedexFactoryTest {

    @Test
    public void testCreatePokedex() {
        // Keep using mocks for metadataProvider and pokemonFactory since their behavior might depend on external systems
        IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);

        // Use the real PokedexFactory implementation
        IPokedexFactory pokedexFactory = new PokedexFactory( metadataProvider, pokemonFactory);

        // Create a Pokedex using the real factory
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        // Validate the creation
        assertNotNull(createdPokedex, "La création du Pokedex n'aurait pas dû échouer");
    }
}
