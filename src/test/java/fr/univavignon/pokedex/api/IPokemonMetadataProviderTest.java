package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class IPokemonMetadataProviderTest {

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        IPokemonMetadataProvider pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);

        int index = 1;
        PokemonMetadata expectedPokemonMetadata = new PokemonMetadata(index, "Bulbasaur", 126, 126, 90);

        when(pokemonMetadataProvider.getPokemonMetadata(index)).thenReturn(expectedPokemonMetadata);

        PokemonMetadata createdPokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(index);

        assertEquals(expectedPokemonMetadata, createdPokemonMetadata);
    }

    @Test
    public void testGetPokemonMetadataWithInvalidIndex() throws PokedexException {
        // Create a mock instance of IPokemonMetadataProvider
        IPokemonMetadataProvider pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);

        int index = -1;

        when(pokemonMetadataProvider.getPokemonMetadata(index)).thenThrow(new PokedexException("Invalid index"));

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

        assertEquals(index, pokemonMetadata.getIndex());
        assertEquals(name, pokemonMetadata.getName());
        assertEquals(attack, pokemonMetadata.getAttack());
        assertEquals(defense, pokemonMetadata.getDefense());
        assertEquals(stamina, pokemonMetadata.getStamina());
    }
}
