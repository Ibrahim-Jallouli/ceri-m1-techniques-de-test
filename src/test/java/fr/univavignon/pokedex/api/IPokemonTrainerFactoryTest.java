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
    private IPokemonTrainerFactory trainerFactory;

    @Mock
    private IPokedexFactory pokedexFactory;
    @Mock
    private IPokedex pokedexMock;

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
