package ar.edu.utn.frc.tup.lciv.services.interfaces.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.PlaylistCommonDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPlaylistService {
    List<PlaylistCommonDTO> getAllPlaylists();
    PlaylistCommonDTO getPlaylistById(String id);
}
