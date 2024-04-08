package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.api.impl.PokemonMetadataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Teste les fonctionnalités de l'interface IPokemonMetadataProvider pour s'assurer que les métadonnées des Pokémon
 * sont correctement récupérées. Ces tests valident la récupération des métadonnées pour un index valide,
 * gèrent les exceptions pour les indices invalides, et vérifient que les getters de l'objet PokemonMetadata
 * retournent les valeurs attendues.
 */

@ExtendWith(MockitoExtension.class)
public class IPokemonMetadataProviderTest {


    IPokemonMetadataProvider pokemonMetadataProvider= new PokemonMetadataProvider();

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        int index = 1;
        PokemonMetadata expectedPokemonMetadata = new PokemonMetadata(index, "Bulbasaur", 126, 126, 90);

        when(pokemonMetadataProvider.getPokemonMetadata(index)).thenReturn(expectedPokemonMetadata);

        PokemonMetadata createdPokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(index);

        assertEquals(expectedPokemonMetadata, createdPokemonMetadata);
    }

    @Test
    public void testGetPokemonMetadataWithInvalidIndex() throws PokedexException {
        int index = -1;

        when(pokemonMetadataProvider.getPokemonMetadata(index))
                .thenThrow(new PokedexException("Invalid index"));

        assertThrows(PokedexException.class, () -> pokemonMetadataProvider.getPokemonMetadata(index));
    }

    @Test
    public void testPokemonMetadataGetters() {
        int index = 1;
        String name = "Bulbasaur";
        int attack = 126;
        int defense = 126;
        int stamina = 90;
        PokemonMetadata pokemonMetadata = new PokemonMetadata(index, name, attack, defense, stamina);

        assertEquals(index, pokemonMetadata.getIndex());
        assertEquals(name, pokemonMetadata.getName());
        assertEquals(attack, pokemonMetadata.getAttack());
        assertEquals(defense, pokemonMetadata.getDefense());
        assertEquals(stamina, pokemonMetadata.getStamina());
    }
}
