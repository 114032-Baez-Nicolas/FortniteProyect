package ar.edu.utn.frc.tup.lciv.services.impl.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.GameModeDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientInternal;
import ar.edu.utn.frc.tup.lciv.services.interfaces.internal.IGameModeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameModeService implements IGameModeService {

    private final FortniteClientInternal fortniteClient;

    @Override
    public List<GameModeDTO> getGameModesOrdered() {
        return fortniteClient.getGameModes()
                .stream()
                .map(mode -> GameModeDTO.builder()
                        .name(mode.getName())
                        .usePercentage(mode.getUsePercentage())
                        .id(mode.getId())
                        .build())
                .sorted(Comparator.comparingDouble(GameModeDTO::getUsePercentage).reversed())
                .collect(Collectors.toList());
    }
}
