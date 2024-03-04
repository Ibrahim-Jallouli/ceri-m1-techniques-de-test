package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IPokedexFactoryTest {

    @Mock
    IPokedexFactory pokedexFactory;

    @Mock
    IPokemonMetadataProvider pokemonMetadataProvider;

    @Mock
    IPokemonFactory pokemonFactory;

    @Mock
    IPokedex pokedex;

    @BeforeEach
    void setUp() {
        // Configurez le comportement du mock de IPokedexFactory
        when(pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory)).thenReturn(pokedex);
    }

    @Test
    void testCreatePokedex() throws PokedexException {
        IPokedex createdPokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);
        assertEquals(pokedex, createdPokedex);
    }

}



