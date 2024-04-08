package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.api.impl.PokemonTrainerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory trainerFactory;

    @Mock
    private IPokedexFactory pokedexFactory;

    @Mock
    private IPokedex pokedexMock;

    @Mock
    private IPokemonMetadataProvider metadataProvider;

    @Mock
    private IPokemonFactory pokemonFactory;

    @BeforeEach
    void setUp() {
        // Instantiate the real factory implementation here
        trainerFactory = new PokemonTrainerFactory(metadataProvider, pokemonFactory);
    }

    @Test
    void testCreateTrainer() {
        String name = "Darius";
        Team team = Team.MYSTIC;

        // Configure the mock pokedexFactory to return a mock IPokedex instance
        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedexMock);
        // Use the real factory to create a PokemonTrainer
        PokemonTrainer createdTrainer = trainerFactory.createTrainer(name, team, pokedexFactory);

        // Verify the created trainer's properties
        assertEquals(name, createdTrainer.getName());
        assertEquals(team, createdTrainer.getTeam());
        assertEquals(pokedexMock, createdTrainer.getPokedex());
    }

    @Test
    public void testPokemonTrainerGetters() {
        String name = "Ash";
        Team team = Team.VALOR;

        // Direct instantiation of a PokemonTrainer for testing getters
        PokemonTrainer trainer = new PokemonTrainer(name, team, pokedexMock);

        assertEquals(name, trainer.getName());
        assertEquals(team, trainer.getTeam());
        assertEquals(pokedexMock, trainer.getPokedex());
    }
}
