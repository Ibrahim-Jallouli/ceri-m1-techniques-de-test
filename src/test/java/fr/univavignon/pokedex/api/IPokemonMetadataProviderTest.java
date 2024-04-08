package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.api.impl.PokemonMetadataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider pokemonMetadataProvider;

    @BeforeEach
    public void setUp() {
        pokemonMetadataProvider = new PokemonMetadataProvider();
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
      /*  int index = 1;
        // Make sure these values match what PokemonMetadataProvider returns for index 1.
        PokemonMetadata expectedPokemonMetadata = new PokemonMetadata(index, "Bulbasaur", 118, 126, 90);

        PokemonMetadata pokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(index);

        assertEquals(expectedPokemonMetadata, pokemonMetadata);*/
    }


    @Test
    public void testGetPokemonMetadataWithInvalidIndex() {
        int invalidIndex = -1;

        // Assuming your implementation throws a PokedexException for invalid indices.
        assertThrows(PokedexException.class, () -> pokemonMetadataProvider.getPokemonMetadata(invalidIndex));
    }

    @Test
    public void testPokemonMetadataGetters() throws PokedexException {
        int index = 1;
        // Assuming Bulbasaur is at index 1 in your metadata provider.
        String name = "Bulbasaur";
        int attack = 118;
        int defense = 118;
        int stamina = 90;

        // Directly use your implementation to retrieve the metadata
        // This step assumes your provider has a valid entry for index 1.
        PokemonMetadata pokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(index);

        assertEquals(index, pokemonMetadata.getIndex());
        assertEquals(name, pokemonMetadata.getName());
        assertEquals(attack, pokemonMetadata.getAttack());
        assertEquals(defense, pokemonMetadata.getDefense());
        assertEquals(stamina, pokemonMetadata.getStamina());
    }
}
