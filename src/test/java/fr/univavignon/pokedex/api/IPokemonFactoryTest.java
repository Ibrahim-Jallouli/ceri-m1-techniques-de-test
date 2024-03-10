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
}
