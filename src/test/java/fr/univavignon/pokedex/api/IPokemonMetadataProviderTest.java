package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IPokemonMetadataProviderTest {

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        // Create a mock instance of IPokemonMetadataProvider
        IPokemonMetadataProvider pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);

        // Define the expected index and metadata
        int index = 1;
        PokemonMetadata expectedPokemonMetadata = new PokemonMetadata(index, "", 126, 126, 90);

        // Configure the mock to return the expected metadata when getPokemonMetadata is called with the index
        when(pokemonMetadataProvider.getPokemonMetadata(index)).thenReturn(expectedPokemonMetadata);

        // Call the method under test
        PokemonMetadata createdPokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(index);

        // Verify that the returned metadata matches the expected metadata
        assertEquals(expectedPokemonMetadata, createdPokemonMetadata);
    }
}
