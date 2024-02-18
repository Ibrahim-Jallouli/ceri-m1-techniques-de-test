package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IPokemonFactoryTest {

    @Mock // creer un mock
    IPokemonFactory pokemonFactory;

    /* on peut supprimer cette method car on utilise l'annotation @ExtendWith(MockitoExtension.class)
     va s'occuper de l'initialisation des mocks
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }*/
    @Test // l'annotation @Test est utilisé pour indiquer que la méthode ci-dessous est un test
    public void testCreatePokemon() {
        int index = 1;
        int cp = 100;
        int hp = 50;
        int dust = 100;
        int candy = 10;

        Pokemon expectedPokemon = new Pokemon(index, "", 0, 0, 0, cp, hp, dust, candy, 0.0);

        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy)).thenReturn(expectedPokemon);

        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        System.out.println(createdPokemon.equals(expectedPokemon));
        assertEquals(expectedPokemon, createdPokemon);
    }

}
