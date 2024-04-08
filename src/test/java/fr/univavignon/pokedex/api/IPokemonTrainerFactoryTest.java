package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.api.impl.PokemonTrainerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests pour l'interface IPokemonTrainerFactory vérifiant la création correcte des entraîneurs Pokémon.
 * Assure que les entraîneurs sont bien créés avec les noms, les équipes et les Pokédex spécifiés,
 * et que les getters de l'objet PokemonTrainer retournent les informations correctes.
 */
@ExtendWith(MockitoExtension.class)
public class IPokemonTrainerFactoryTest {




    @Mock
    private IPokedexFactory pokedexFactory;
    @Mock
    private IPokedex pokedexMock;

    @Mock
    private IPokemonMetadataProvider metadataProvider;

    private IPokemonTrainerFactory trainerFactory= new PokemonTrainerFactory( metadataProvider, pokedexMock);
    @Test
    void testCreateTrainer() {
        String name = "Darius";
        Team team = Team.MYSTIC;

        when(trainerFactory.createTrainer(name, team, pokedexFactory))
                .thenReturn(new PokemonTrainer(name, team, pokedexMock));

        PokemonTrainer createdTrainer = trainerFactory.createTrainer(name, team, pokedexFactory);

        assertEquals(pokedexMock, createdTrainer.getPokedex());
        verify(trainerFactory).createTrainer(name, team, pokedexFactory);
    }

    @Test
    public void testPokemonTrainerGetters() {
        String name = "Ash";
        Team team = Team.VALOR;

        PokemonTrainer trainer = new PokemonTrainer(name, team, pokedexMock);

        assertEquals(name, trainer.getName());
        assertEquals(team, trainer.getTeam());
        assertEquals(pokedexMock, trainer.getPokedex());
    }
}
