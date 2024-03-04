package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IPokemonTrainerFactoryTest {

    @Mock
    IPokemonTrainerFactory trainerFactory;

    @Mock
    IPokedexFactory pokedexFactory;

    @Mock
    IPokedex pokedexMock;

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
}
