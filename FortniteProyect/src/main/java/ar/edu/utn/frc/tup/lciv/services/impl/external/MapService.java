package ar.edu.utn.frc.tup.lciv.services.impl.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.MapDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientExternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.MapClientDTO;
import ar.edu.utn.frc.tup.lciv.services.interfaces.external.IMapService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MapService implements IMapService {

    private final FortniteClientExternal fortniteClientExternal;

    @Override
    public List<MapDTO> getAllMapData() {
        List<MapClientDTO> clientData = fortniteClientExternal.getAllMapDataFromApi();
        return clientData.stream()
                .map(mapClientDTO -> MapDTO.builder()
                        .id(mapClientDTO.getId())
                        .name(mapClientDTO.getName())
                        .x(mapClientDTO.getLocation().getX())
                        .y(mapClientDTO.getLocation().getY())
                        .z(mapClientDTO.getLocation().getZ())
                        .build())
                .collect(Collectors.toList());
    }
}
