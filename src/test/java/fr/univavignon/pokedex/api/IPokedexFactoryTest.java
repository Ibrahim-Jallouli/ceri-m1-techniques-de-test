package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//valider ce test
public class IPokedexFactoryTest {

    private IPokedexFactory pokedexFactory;
    private IPokemonMetadataProvider pokemonMetadataProvider;
    private IPokemonFactory pokemonFactory;
    private IPokedex pokedex;

    @BeforeEach
    void setUp() {
        // Initialize the mocks
        pokedexFactory = mock(IPokedexFactory.class);
        pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
        pokemonFactory = mock(IPokemonFactory.class);
        pokedex = mock(IPokedex.class);

        // Configure the behavior of the mock IPokedexFactory
        when(pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory)).thenReturn(pokedex);
    }

    @Test
    void testCreatePokedex() throws PokedexException {
        // Call the method to create a Pokedex
        IPokedex createdPokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);

        // Verify that the created Pokedex is the same as the one returned by the mock
        assertEquals(pokedex, createdPokedex);
    }
}
