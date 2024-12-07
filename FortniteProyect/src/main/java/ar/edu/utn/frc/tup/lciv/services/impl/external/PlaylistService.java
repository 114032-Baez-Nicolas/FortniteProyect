package ar.edu.utn.frc.tup.lciv.services.impl.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.PlaylistCommonDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientExternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.PlaylistClientDTO;
import ar.edu.utn.frc.tup.lciv.services.interfaces.external.IPlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaylistService implements IPlaylistService {

    private final FortniteClientExternal fortniteClientExternal;

    @Override
    public List<PlaylistCommonDTO> getAllPlaylists() {
        List<PlaylistClientDTO> clientPlaylists = fortniteClientExternal.getPlaylists();
        return clientPlaylists.stream()
                .map(this::mapToCommonDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlaylistCommonDTO getPlaylistById(String id) {
        return fortniteClientExternal.getPlaylists().stream()
                .filter(playlist -> playlist.getId().equals(id))
                .findFirst()
                .map(this::mapToCommonDTO)
                .orElse(null);
    }

    private PlaylistCommonDTO mapToCommonDTO(PlaylistClientDTO clientDTO) {
        PlaylistCommonDTO commonDTO = new PlaylistCommonDTO();
        commonDTO.setId(clientDTO.getId());
        commonDTO.setName(clientDTO.getName());
        commonDTO.setGameType(clientDTO.getGameType());
        commonDTO.setPlayerRange(clientDTO.getMinPlayers() + "-" + clientDTO.getMaxPlayers());
        return commonDTO;
    }
}
