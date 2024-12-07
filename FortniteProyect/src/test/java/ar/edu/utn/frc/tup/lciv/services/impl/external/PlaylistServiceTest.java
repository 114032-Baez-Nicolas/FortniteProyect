package ar.edu.utn.frc.tup.lciv.services.impl.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.PlaylistCommonDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientExternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.PlaylistClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {

    @Mock
    private FortniteClientExternal fortniteClientExternal;

    @InjectMocks
    private PlaylistService playlistService;

    private PlaylistClientDTO playlist1;
    private PlaylistClientDTO playlist2;

    @BeforeEach
    void setUp() {
        playlist1 = new PlaylistClientDTO();
        playlist1.setId("1");
        playlist1.setName("Playlist 1");
        playlist1.setGameType("Type 1");
        playlist1.setMinPlayers(1);
        playlist1.setMaxPlayers(4);

        playlist2 = new PlaylistClientDTO();
        playlist2.setId("2");
        playlist2.setName("Playlist 2");
        playlist2.setGameType("Type 2");
        playlist2.setMinPlayers(2);
        playlist2.setMaxPlayers(8);
    }

    @Test
    void testGetAllPlaylists() {
        when(fortniteClientExternal.getPlaylists()).thenReturn(Arrays.asList(playlist1, playlist2));

        List<PlaylistCommonDTO> result = playlistService.getAllPlaylists();

        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("Playlist 1", result.get(0).getName());
        assertEquals("Type 1", result.get(0).getGameType());
        assertEquals("1-4", result.get(0).getPlayerRange());

        assertEquals("2", result.get(1).getId());
        assertEquals("Playlist 2", result.get(1).getName());
        assertEquals("Type 2", result.get(1).getGameType());
        assertEquals("2-8", result.get(1).getPlayerRange());
    }

    @Test
    void testGetPlaylistById() {
        when(fortniteClientExternal.getPlaylists()).thenReturn(Arrays.asList(playlist1, playlist2));

        PlaylistCommonDTO result = playlistService.getPlaylistById("1");

        assertEquals("1", result.getId());
        assertEquals("Playlist 1", result.getName());
        assertEquals("Type 1", result.getGameType());
        assertEquals("1-4", result.getPlayerRange());

        result = playlistService.getPlaylistById("2");

        assertEquals("2", result.getId());
        assertEquals("Playlist 2", result.getName());
        assertEquals("Type 2", result.getGameType());
        assertEquals("2-8", result.getPlayerRange());

        result = playlistService.getPlaylistById("3");

        assertNull(result);
    }
}