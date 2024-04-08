package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.api.impl.Pokedex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class IPokedexTest {

    @Mock
    private IPokemonMetadataProvider metadataProvider;

    @Mock
    private IPokemonFactory pokemonFactory;

    private IPokedex pokedex;

    @BeforeEach
    public void setUp() {
        pokedex = new Pokedex(metadataProvider, pokemonFactory);
    }

    @Test
    public void testSize() {
        assertEquals(0, pokedex.size(), "Initially, the Pokedex should be empty.");

        pokedex.addPokemon(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        assertEquals(1, pokedex.size(), "After adding a Pokemon, the size should increase.");
    }

    @Test
    public void testAddPokemon() {
        Pokemon pokemon = new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5);
        pokedex.addPokemon(pokemon);
        assertEquals(1, pokedex.size(), "Pokedex size should increase by 1 after adding a Pokemon.");
        assertTrue(pokedex.getPokemons().contains(pokemon), "Added Pokemon should be in the Pokedex.");
    }

    @Test
    void getPokemon() throws PokedexException {
        Pokemon expectedPokemon = new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5);
        int pokemonId = pokedex.addPokemon(expectedPokemon);
        Pokemon retrievedPokemon = pokedex.getPokemon(pokemonId);
        assertNotNull(retrievedPokemon, "Retrieved Pokemon should not be null.");
        assertEquals(expectedPokemon.getIndex(), retrievedPokemon.getIndex(), "Retrieved Pokemon should have the same index as the expected.");
    }

    @Test
    void getPokemons() {
        pokedex.addPokemon(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        pokedex.addPokemon(new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0));
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertNotNull(pokemons, "The retrieved list of Pokemons should not be null.");
        assertEquals(2, pokemons.size(), "The size of the retrieved list should match the number of added Pokemons.");
    }

    @Test
    void testGetPokemonsSortedByIndex() {
        pokedex.addPokemon(new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0));
        pokedex.addPokemon(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        pokedex.addPokemon(new Pokemon(3, "CCC", 0, 0, 0, 0, 0, 0, 0, 1.5));
        List<Pokemon> sortedPokemons = pokedex.getPokemons(PokemonComparators.INDEX);
        assertEquals(3, sortedPokemons.size(), "The size of the sorted list should be 3.");
        for (int i = 0; i < sortedPokemons.size(); i++) {
            assertEquals(i + 1, sortedPokemons.get(i).getIndex(), "Pokemons should be sorted by index.");
        }
    }
}
