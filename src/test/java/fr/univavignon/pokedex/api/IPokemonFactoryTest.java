package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class IPokemonFactoryTest {

    @Mock
    IPokemonFactory pokemonFactory;

    public IPokemonFactoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePokemon() {
        int index = 1;
        int cp = 100;
        int hp = 50;
        int dust = 100;
        int candy = 10;

        // Création du Pokémon attendu
        Pokemon expectedPokemon = new Pokemon(index, "", 0, 0, 0, cp, hp, dust, candy, 0.0);

        // Configuration du mock pour retourner le Pokémon attendu
        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy)).thenReturn(expectedPokemon);

        // Appel de la méthode à tester
        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Vérification que le Pokémon retourné par la méthode est le même que le Pokémon attendu
        assertEquals(expectedPokemon, createdPokemon);

        when(pokemonFactory.createPokemon(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
                .thenAnswer(invocation -> {
                    int index1 = invocation.getArgument(0);
                    if (index1 < 1 || index1 > 150) {
                        throw new IndexOutOfBoundsException();
                    }
                    // Retourner un Pokémon valide pour les index dans la plage spécifiée
                    return new Pokemon(index1, "", 0, 0, 0, cp, hp, dust, candy, 0.0);
                });
        // Vérification que l'index est dans la plage spécifiée (1 à 150)
        assertThrows(IndexOutOfBoundsException.class, () -> pokemonFactory.createPokemon(-2, cp, hp, dust, candy));
        assertThrows(IndexOutOfBoundsException.class, () -> pokemonFactory.createPokemon(151, cp, hp, dust, candy));
    }

    @Test
    public void testPokemonGetters() {
        int index = 1;
        String name = "Bulbasaur";
        int attack = 126;
        int defense = 126;
        int stamina = 90;
        int cp = 100;
        int hp = 50;
        int dust = 100;
        int candy = 10;
        double iv = 0.5; // Assuming a 50% IV

        // Create a Pokemon instance
        Pokemon pokemon = new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);

        // Test the getters
        assertEquals(index, pokemon.getIndex());
        assertEquals(name, pokemon.getName());
        assertEquals(attack, pokemon.getAttack());
        assertEquals(defense, pokemon.getDefense());
        assertEquals(stamina, pokemon.getStamina());
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertEquals(iv, pokemon.getIv());
    }
}
