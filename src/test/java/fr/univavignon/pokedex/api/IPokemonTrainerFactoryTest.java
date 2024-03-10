package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {

    @Test
    void testCreateTrainer() {
        // Create mocks for dependencies
        IPokemonTrainerFactory trainerFactory = mock(IPokemonTrainerFactory.class);
        IPokedexFactory pokedexFactory = mock(IPokedexFactory.class);
        IPokedex pokedexMock = mock(IPokedex.class);

        // Define test data
        String name = "Darius";
        Team team = Team.MYSTIC;

        // Configure mock behavior
        when(trainerFactory.createTrainer(name, team, pokedexFactory))
                .thenReturn(new PokemonTrainer(name, team, pokedexMock));

        // Call the method under test
        PokemonTrainer createdTrainer = trainerFactory.createTrainer(name, team, pokedexFactory);

        // Verify the behavior and outcome
        assertEquals(pokedexMock, createdTrainer.getPokedex());
        verify(trainerFactory).createTrainer(name, team, pokedexFactory);
    }
}
