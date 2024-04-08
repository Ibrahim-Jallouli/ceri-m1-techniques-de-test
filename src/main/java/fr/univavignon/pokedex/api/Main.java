package fr.univavignon.pokedex.api;


import fr.univavignon.pokedex.api.impl.PokedexFactory;
import fr.univavignon.pokedex.api.impl.PokemonFactory;
import fr.univavignon.pokedex.api.impl.PokemonMetadataProvider;
import fr.univavignon.pokedex.api.impl.PokemonTrainerFactory;

/**
 * Classe principale pour tester les différentes fonctionnalités du Pokédex.
 */
public class Main {

    public static void main(String[] args) {
        // Création des fournisseurs et des factories nécessaires
        IPokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
        IPokemonFactory pokemonFactory = new PokemonFactory();
        IPokedexFactory pokedexFactory = new PokedexFactory(metadataProvider, pokemonFactory);
        IPokemonTrainerFactory trainerFactory = new PokemonTrainerFactory(metadataProvider, pokemonFactory);

        // Création d'un dresseur de Pokémon avec un Pokédex
        PokemonTrainer trainer = trainerFactory.createTrainer("Ash", Team.MYSTIC, pokedexFactory);

        // Ajout de quelques Pokémon au Pokédex du dresseur
        Pokemon pikachu = pokemonFactory.createPokemon(25, 500, 60, 3000, 50);
        Pokemon charmander = pokemonFactory.createPokemon(4, 450, 50, 2500, 45);

        trainer.getPokedex().addPokemon(pikachu);
        trainer.getPokedex().addPokemon(charmander);

        // Affichage de quelques informations
        System.out.println("Le dresseur " + trainer.getName() + " de l'équipe " + trainer.getTeam() + " possède " + trainer.getPokedex().size() + " Pokémon.");
        System.out.println("Pokémon dans le Pokédex :");
        trainer.getPokedex().getPokemons().forEach(pokemon -> {
            System.out.println(pokemon.getName() + " - CP: " + pokemon.getCp() + ", HP: " + pokemon.getHp());
        });
    }
}
