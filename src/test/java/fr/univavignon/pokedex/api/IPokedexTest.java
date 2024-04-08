package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.api.impl.Pokedex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;g


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
    private IPokemonMetadataProvider metadataProvider;

    @Mock
    private IPokemonFactory pokemonFactory;

    private IPokedex pokedex = new Pokedex(metadataProvider,pokemonFactory);

    @Test
    public void testSize() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        pokemons.add(new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0));


        int size = pokemons.size();
        assertEquals(size, pokemons.size());

        List<Pokemon> emptyPokemons = Collections.emptyList();
        assertEquals(0, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        Pokemon pokemon = new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5);
        int initialSize = pokedex.size();
        pokedex.addPokemon(pokemon);
        int newSize = pokedex.size();
        assertEquals(initialSize + 1, newSize);
        assertTrue(pokedex.getPokemons().contains(pokemon));
    }


    @Test
    void getPokemon() throws PokedexException {
        // Création de l'instance de Pokemon
        Pokemon expectedPokemon = new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5);

        // Ajout du Pokemon au Pokedex
        int pokemonId = pokedex.addPokemon(expectedPokemon); // Assurez-vous que cette méthode retourne l'ID du Pokémon ajouté

        // Récupération du Pokemon par son ID
        Pokemon retrievedPokemon = pokedex.getPokemon(pokemonId);

        // Vérification que le Pokemon récupéré correspond à celui attendu
        assertNotNull(retrievedPokemon, "Le Pokémon récupéré ne devrait pas être null.");
        assertEquals(expectedPokemon.getIndex(), retrievedPokemon.getIndex(), "Les indices des Pokémon devraient correspondre.");
        assertEquals(expectedPokemon.getName(), retrievedPokemon.getName(), "Les noms des Pokémon devraient correspondre.");
        // Continuez avec d'autres assertions si nécessaire
    }



    @Test
    void getPokemons() {
        pokedex.addPokemon(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        pokedex.addPokemon(new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0));
        List<Pokemon> expectedPokemons = Arrays.asList(
                new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5),
                new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0)
        );
        List<Pokemon> actualPokemons = pokedex.getPokemons();

        assertNotNull(actualPokemons);
        assertEquals(expectedPokemons.size(), actualPokemons.size());
    }

    @Test
    void testGetPokemonsSortedByIndex() {
        // Ajouter les Pokémon dans l'ordre désordonné
        pokedex.addPokemon(new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0));
        pokedex.addPokemon(new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5));
        pokedex.addPokemon(new Pokemon(3, "CCC", 0, 0, 0, 0, 0, 0, 0, 1.5));

        // Récupérer la liste triée par l'indice
        List<Pokemon> sortedPokemons = pokedex.getPokemons(PokemonComparators.INDEX);

        // Créer la liste attendue
        List<Pokemon> expectedPokemons = Arrays.asList(
                new Pokemon(1, "AAA", 0, 0, 0, 0, 0, 0, 0, 0.5),
                new Pokemon(2, "BBB", 0, 0, 0, 0, 0, 0, 0, 1.0),
                new Pokemon(3, "CCC", 0, 0, 0, 0, 0, 0, 0, 1.5)
        );

        // Vérifier que les listes sont égales
        assertEquals(expectedPokemons.size(), sortedPokemons.size(), "La taille des listes devrait être la même");
        for (int i = 0; i < expectedPokemons.size(); i++) {
            assertEquals(expectedPokemons.get(i).getIndex(), sortedPokemons.get(i).getIndex(), "Les indices des Pokémon à la position " + i + " devraient être égaux");
        }
    }

}
