package ar.edu.utn.frc.tup.lciv.services.impl.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.TopPlayerDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientInternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.internal.topPlayerClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TopPlayerServiceTest {

    @Mock
    private FortniteClientInternal fortniteClient;

    @InjectMocks
    private TopPlayerService topPlayerService;

    private Map<String, List<topPlayerClientDTO>> topPlayersByChapter;

    @BeforeEach
    void setUp() {
        topPlayersByChapter = createTopPlayersByChapter();
    }

    private Map<String, List<topPlayerClientDTO>> createTopPlayersByChapter() {
        Map<String, List<topPlayerClientDTO>> topPlayersByChapter = new HashMap<>();

        topPlayerClientDTO player1 = new topPlayerClientDTO();
        player1.setName("Player 1");
        player1.setDescription("Description 1");
        player1.setImage("image1.png");

        topPlayerClientDTO player2 = new topPlayerClientDTO();
        player2.setName("Player 2");
        player2.setDescription("Description 2");
        player2.setImage("image2.png");

        topPlayerClientDTO player3 = new topPlayerClientDTO();
        player3.setName("Player 3");
        player3.setDescription("Description 3");
        player3.setImage("image3.png");

        topPlayersByChapter.put("chapter_1", List.of(player1, player2));
        topPlayersByChapter.put("chapter_2", List.of(player3));

        return topPlayersByChapter;
    }

    @Test
    void testGetTopPlayersWithName() {
        when(fortniteClient.getTopPlayers()).thenReturn(topPlayersByChapter);

        List<TopPlayerDTO> result = topPlayerService.getTopPlayers("Player 1", null);

        assertEquals(1, result.size());
        assertEquals("Player 1", result.get(0).getName());
        assertEquals("Description 1", result.get(0).getDescription());
        assertEquals("image1.png", result.get(0).getImage());
        assertEquals(1, result.get(0).getChapter());
    }

    @Test
    void testGetTopPlayersWithChapter() {
        when(fortniteClient.getTopPlayers()).thenReturn(topPlayersByChapter);

        List<TopPlayerDTO> result = topPlayerService.getTopPlayers(null, 1);

        assertEquals(2, result.size());
        assertEquals("Player 1", result.get(0).getName());
        assertEquals("Description 1", result.get(0).getDescription());
        assertEquals("image1.png", result.get(0).getImage());
        assertEquals(1, result.get(0).getChapter());

        assertEquals("Player 2", result.get(1).getName());
        assertEquals("Description 2", result.get(1).getDescription());
        assertEquals("image2.png", result.get(1).getImage());
        assertEquals(1, result.get(1).getChapter());
    }

    @Test
    void testGetTopPlayersWithoutFilters() {
        when(fortniteClient.getTopPlayers()).thenReturn(topPlayersByChapter);

        List<TopPlayerDTO> result = topPlayerService.getTopPlayers(null, null);

        assertEquals(3, result.size());
        assertEquals("Player 1", result.get(0).getName());
        assertEquals("Description 1", result.get(0).getDescription());
        assertEquals("image1.png", result.get(0).getImage());
        assertEquals(1, result.get(0).getChapter());

        assertEquals("Player 2", result.get(1).getName());
        assertEquals("Description 2", result.get(1).getDescription());
        assertEquals("image2.png", result.get(1).getImage());
        assertEquals(1, result.get(1).getChapter());

        assertEquals("Player 3", result.get(2).getName());
        assertEquals("Description 3", result.get(2).getDescription());
        assertEquals("image3.png", result.get(2).getImage());
        assertEquals(2, result.get(2).getChapter());
    }
}