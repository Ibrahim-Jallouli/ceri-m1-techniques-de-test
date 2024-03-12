package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {

    @Test
    void testCreateTrainer() {
        IPokemonTrainerFactory trainerFactory = mock(IPokemonTrainerFactory.class);
        IPokedexFactory pokedexFactory = mock(IPokedexFactory.class);
        IPokedex pokedexMock = mock(IPokedex.class);

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
        IPokedex pokedexMock = mock(IPokedex.class);

        PokemonTrainer trainer = new PokemonTrainer(name, team, pokedexMock);
        assertEquals(name, trainer.getName());
        assertEquals(team, trainer.getTeam());
        assertEquals(pokedexMock, trainer.getPokedex());
    }

}
