package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.api.impl.Pokedex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Classe de test pour l'interface IPokedexFactory.
 * Utilise Mockito pour simuler le comportement des interfaces dépendantes et vérifier
 * la création correcte d'un objet Pokedex.
 */

@ExtendWith(MockitoExtension.class)
public class IPokedexFactoryTest {

    @Test
    public void testCreatePokedex() {
        // Creation des mocks
        IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
        IPokedex pokedex = new Pokedex(metadataProvider, pokemonFactory);

        IPokedexFactory pokedexFactory = mock(IPokedexFactory.class);

        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);

        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        assertNotNull(createdPokedex, "La création du Pokedex n'aurait pas dû échouer");
        assertEquals(pokedex, createdPokedex, "Le Pokedex créé devrait être celui qui est mocké");
    }
}
