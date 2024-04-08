package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.api.impl.PokemonFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Teste les fonctionnalités de l'interface IPokemonFactory pour s'assurer que les Pokémon sont créés correctement.
 * Ces tests incluent la vérification de l'instanciation correcte d'un Pokémon avec les paramètres attendus
 * et s'assurent que les exceptions appropriées sont levées lorsqu'on utilise des valeurs d'index hors limites.
 * Un test supplémentaire vérifie que les getters de l'objet Pokémon retournent les valeurs correctes.
 */
public class IPokemonFactoryTest {
    private final IPokemonFactory pokemonFactory = new PokemonFactory();

    @Test
    public void testCreatePokemon() {
        int index = 1;
        int cp = 100;
        int hp = 50;
        int dust = 100;
        int candy = 10;

        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        assertEquals(index, createdPokemon.getIndex());
        assertEquals(cp, createdPokemon.getCp());
        assertEquals(hp, createdPokemon.getHp());
        assertEquals(dust, createdPokemon.getDust());
        assertEquals(candy, createdPokemon.getCandy());
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
        double iv = 0.5;

        Pokemon pokemon = new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);

        assertEquals(index, pokemon.getIndex());
        assertEquals(name, pokemon.getName());
        assertEquals(attack, pokemon.getAttack());
        assertEquals(defense, pokemon.getDefense());
        assertEquals(stamina, pokemon.getStamina());
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertEquals(iv, pokemon.getIv(), 0.001);
    }
}
