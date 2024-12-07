package ar.edu.utn.frc.tup.lciv.services.interfaces.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.GameModeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IGameModeService {
    List<GameModeDTO> getGameModesOrdered();
}
