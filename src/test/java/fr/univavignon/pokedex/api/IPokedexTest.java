package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class IPokedexTest {

    private IPokedex pokedex;

    @BeforeEach
    public void setUp() {
        pokedex = mock(IPokedex.class);
    }

    @Test
    public void testSize() {
        pokedex = mock(IPokedex.class);
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        pokemons.add(new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0));

        System.out.println(pokedex.size());
        when(pokedex.size()).thenReturn(pokemons.size());
        System.out.println(pokedex.size());

        // Call the tested method
        int size = pokedex.size();
        assertEquals(pokemons.size(), size);

        // Test the case when the Pokédex is empty
        List<Pokemon> emptyPokemons = Collections.emptyList();
        when(pokedex.size()).thenReturn(emptyPokemons.size());
        assertEquals(0, pokedex.size());
    }


    @Test
    public void testAddPokemon() {
        Pokemon pokemon = new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5);
        when(pokedex.addPokemon(pokemon)).thenReturn(1);
        int result = pokedex.addPokemon(pokemon);
        assertEquals(1, result);
    }

    @Test
    void getPokemon() throws PokedexException {
        Pokemon pokemon = new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5);
        when(pokedex.getPokemon(1)).thenReturn(pokemon);
        Pokemon createdPokemon = pokedex.getPokemon(1);
        assertTrue(createdPokemon.equals(pokemon));
    }

    @Test
    void getPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        pokemons.add(new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0));
        when(pokedex.getPokemons()).thenReturn(pokemons);
        List<Pokemon> createdPokemons = pokedex.getPokemons();
        assertTrue(createdPokemons.equals(pokemons));
    }

    @Test
    void testGetPokemonsSortedByIndex() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0));
        pokemons.add(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        pokemons.add(new Pokemon(3, "CCC", 0, 0, 0, 0, 0, 0, 0, 1.5));

        // Mock pokedex.getPokemons() with INDEX comparator
        when(pokedex.getPokemons(PokemonComparators.INDEX)).thenAnswer(invocation -> {
            Comparator<Pokemon> comparator = invocation.getArgument(0);
            List<Pokemon> sortedPokemons = new ArrayList<>(pokemons);
            sortedPokemons.sort(comparator);
            return sortedPokemons;
        });

        List<Pokemon> sortedPokemons = pokedex.getPokemons(PokemonComparators.INDEX);

        List<Pokemon> expectedSortedPokemons = new ArrayList<>(pokemons);
        expectedSortedPokemons.sort(Comparator.comparing(Pokemon::getIndex));
        assertEquals(expectedSortedPokemons, sortedPokemons);
    }
}
