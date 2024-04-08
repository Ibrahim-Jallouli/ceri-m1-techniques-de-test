package fr.univavignon.pokedex.api.impl;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.Pokemon;

public class PokemonFactory implements IPokemonFactory {

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
         double iv = (cp + hp) / (double) (dust + candy);

        String name = "Pokemon" + index;
        int attack = 50;
        int defense = 50;
        int stamina = 50;

        return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
    }
}
