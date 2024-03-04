package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IPokedexTest {

    @Mock
    IPokedex pokedex;

    @Test
    public void testSize() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        pokemons.add(new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0));

        when(pokedex.size()).thenReturn(pokemons.size());

        // Appeler la méthode testée
        int size = pokedex.size();
        System.out.println(size);
        assertEquals(pokemons.size(), size);


        // Tester le cas où le Pokédex est vide
        List<Pokemon> emptyPokemons = Collections.emptyList();
        when(pokedex.size()).thenReturn(emptyPokemons.size());
        assertEquals(0, pokedex.size());

    }

    @Test
    public void testAddPokemon() {
        Pokemon pokemon =new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5);
        when(pokedex.addPokemon(pokemon)).thenReturn(1);
        int bool = pokedex.addPokemon(pokemon);
        assertEquals(1, bool);
    }

    @Test
    void getPokemon() throws PokedexException {
        Pokemon pokemon = new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5);
        when(pokedex.getPokemon(1)).thenReturn(pokemon);
        Pokemon createdPokemon = pokedex.getPokemon(1);
        boolean bool= createdPokemon.equals(pokemon);
        assertTrue(bool);
    }

    @Test
    void getPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        pokemons.add(new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0));
        when(pokedex.getPokemons()).thenReturn(pokemons);
        List<Pokemon> createdPokemons = pokedex.getPokemons();
        boolean bool= createdPokemons.equals(pokemons);
        assertTrue(bool);
    }

    @Test
    void testGetPokemonsSortedByIndex() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0));
        pokemons.add(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        pokemons.add(new Pokemon(3, "CCC", 0, 0, 0, 0, 0, 0, 0, 1.5));

        // Mock de pokedex.getPokemons() avec le comparateur INDEX
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
