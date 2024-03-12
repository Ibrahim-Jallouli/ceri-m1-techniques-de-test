package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class IPokemonMetadataProviderTest {

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        // Create a mock instance of IPokemonMetadataProvider
        IPokemonMetadataProvider pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);

        // Define the expected index and metadata
        int index = 1;
        PokemonMetadata expectedPokemonMetadata = new PokemonMetadata(index, "Bulbasaur", 126, 126, 90);

        // Configure the mock to return the expected metadata when getPokemonMetadata is called with the index
        when(pokemonMetadataProvider.getPokemonMetadata(index)).thenReturn(expectedPokemonMetadata);

        // Call the method under test
        PokemonMetadata createdPokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(index);

        // Verify that the returned metadata matches the expected metadata
        assertEquals(expectedPokemonMetadata, createdPokemonMetadata);
    }

    @Test
    public void testGetPokemonMetadataWithInvalidIndex() throws PokedexException {
        // Create a mock instance of IPokemonMetadataProvider
        IPokemonMetadataProvider pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);

        // Define an invalid index
        int index = -1;

        // Configure the mock to throw an exception when getPokemonMetadata is called with the invalid index
        when(pokemonMetadataProvider.getPokemonMetadata(index)).thenThrow(new PokedexException("Invalid index"));

        // Call the method under test and expect an exception
        assertThrows(PokedexException.class, () -> pokemonMetadataProvider.getPokemonMetadata(index));
    }

    @Test
    public void testPokemonMetadataGetters() {
        // Create a PokemonMetadata instance
        int index = 1;
        String name = "Bulbasaur";
        int attack = 126;
        int defense = 126;
        int stamina = 90;
        PokemonMetadata pokemonMetadata = new PokemonMetadata(index, name, attack, defense, stamina);

        // Test the getters
        assertEquals(index, pokemonMetadata.getIndex());
        assertEquals(name, pokemonMetadata.getName());
        assertEquals(attack, pokemonMetadata.getAttack());
        assertEquals(defense, pokemonMetadata.getDefense());
        assertEquals(stamina, pokemonMetadata.getStamina());
    }
}
