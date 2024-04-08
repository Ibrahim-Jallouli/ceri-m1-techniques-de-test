package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


/**
 * Teste les fonctionnalités clés de l'interface IPokedex en utilisant des mocks avec Mockito.
 * Couvre les tests pour les opérations de base telles que l'ajout de Pokémon, la récupération de Pokémon par ID,
 * la vérification de la taille du Pokédex, et l'obtention de la liste des Pokémon triés.
 * Utilise @Mock pour créer des mocks d'IPokedex et vérifie le comportement attendu à travers divers scénarios.
 */

@ExtendWith(MockitoExtension.class) // Utilise MockitoExtension pour gérer les mocks
public class IPokedexTest {

    // Utilisez @Mock pour initialiser automatiquement les mocks
    @Mock
    private IPokedex pokedex;

    @Test
    public void testSize() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        pokemons.add(new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0));

        when(pokedex.size()).thenReturn(pokemons.size());

        int size = pokedex.size();
        assertEquals(pokemons.size(), size);

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
