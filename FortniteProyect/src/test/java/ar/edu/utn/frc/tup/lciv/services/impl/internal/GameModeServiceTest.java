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
        gameModeClientDTO mode1 = new gameModeClientDTO();
        mode1.setName("Mode 1");
        mode1.setUsePercentage(30.0);
        mode1.setId("1");

        gameModeClientDTO mode2 = new gameModeClientDTO();
        mode2.setName("Mode 2");
        mode2.setUsePercentage(50.0);
        mode2.setId("2");

        gameModeClientDTO mode3 = new gameModeClientDTO();
        mode3.setName("Mode 3");
        mode3.setUsePercentage(20.0);
        mode3.setId("3");

        return Arrays.asList(mode1, mode2, mode3);
    }

    @Test
    void testGetGameModesOrdered() {
        when(fortniteClient.getGameModes()).thenReturn(gameModeClientDTOList);

        List<GameModeDTO> result = gameModeService.getGameModesOrdered();

        assertEquals(3, result.size());
        assertEquals("Mode 2", result.get(0).getName());
        assertEquals(50.0, result.get(0).getUsePercentage());
        assertEquals("2", result.get(0).getId());

        assertEquals("Mode 1", result.get(1).getName());
        assertEquals(30.0, result.get(1).getUsePercentage());
        assertEquals("1", result.get(1).getId());

        assertEquals("Mode 3", result.get(2).getName());
        assertEquals(20.0, result.get(2).getUsePercentage());
        assertEquals("3", result.get(2).getId());
    }
}