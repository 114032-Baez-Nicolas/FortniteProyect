package ar.edu.utn.frc.tup.lciv.services.impl.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.GameModeDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientInternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.internal.gameModeClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameModeServiceTest {

    @Mock
    private FortniteClientInternal fortniteClient;

    @InjectMocks
    private GameModeService gameModeService;

    private List<gameModeClientDTO> gameModeClientDTOList;

    @BeforeEach
    void setUp() {
        gameModeClientDTOList = createGameModeClientDTOList();
    }

    private List<gameModeClientDTO> createGameModeClientDTOList() {
        gameModeClientDTO mode1 = new gameModeClientDTO(
                "Battle Royale (Building)",
                30.0,
                "1",
                "https://example.com/image1.jpg",
                "Classic Fortnite game mode with building mechanics."
        );
        gameModeClientDTO mode2 = new gameModeClientDTO(
                "Fortnite OG (Building)",
                50.0,
                "2",
                "https://example.com/image2.jpg",
                "Nostalgic mode with classic Fortnite features."
        );
        gameModeClientDTO mode3 = new gameModeClientDTO(
                "Battle Royale (Zero Build)",
                20.0,
                "3",
                "https://example.com/image3.jpg",
                "Battle Royale mode without building mechanics."
        );

        return Arrays.asList(mode1, mode2, mode3);
    }

    @Test
    void testGetGameModesOrdered() {
        when(fortniteClient.getGameModes()).thenReturn(gameModeClientDTOList);

        List<GameModeDTO> result = gameModeService.getGameModesOrdered();

        assertEquals(3, result.size());

        assertEquals("Fortnite OG (Building)", result.get(0).getName());
        assertEquals(50.0, result.get(0).getUsePercentage());
        assertEquals("2", result.get(0).getId());
        assertEquals("Nostalgic mode with classic Fortnite features.", result.get(0).getDescription());

        assertEquals("Battle Royale (Building)", result.get(1).getName());
        assertEquals(30.0, result.get(1).getUsePercentage());
        assertEquals("1", result.get(1).getId());
        assertEquals("Classic Fortnite game mode with building mechanics.", result.get(1).getDescription());

        assertEquals("Battle Royale (Zero Build)", result.get(2).getName());
        assertEquals(20.0, result.get(2).getUsePercentage());
        assertEquals("3", result.get(2).getId());
        assertEquals("Battle Royale mode without building mechanics.", result.get(2).getDescription());
    }
}