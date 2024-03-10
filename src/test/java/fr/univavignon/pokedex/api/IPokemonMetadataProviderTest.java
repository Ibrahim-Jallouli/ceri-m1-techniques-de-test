package fr.univavignon.pokedex.api;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IPokemonMetadataProviderTest {
    @Mock
    IPokemonMetadataProvider pokemonMetadataProvider;

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        int index = 1;
        PokemonMetadata expectedPokemonMetadata = new PokemonMetadata(index, "", 126, 126, 90);
        when(pokemonMetadataProvider.getPokemonMetadata(index)).thenReturn(expectedPokemonMetadata);
        PokemonMetadata createdPokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(index);
        assertEquals(expectedPokemonMetadata, createdPokemonMetadata);
    }
}
