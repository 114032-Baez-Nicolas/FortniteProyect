package ar.edu.utn.frc.tup.lciv.services.impl.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.MapZoneDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientInternal;
import ar.edu.utn.frc.tup.lciv.services.interfaces.internal.IMapZoneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MapZoneService implements IMapZoneService {

    private final FortniteClientInternal fortniteClient;

    @Override
    public List<MapZoneDTO> getMapZones(Integer chapter) {
        return fortniteClient.getMapZones()
                .stream()
                .filter(zone -> chapter == null || zone.getChapter().equals(chapter))
                .map(zone -> MapZoneDTO.builder()
                        .name(zone.getName())
                        .chapter(zone.getChapter())
                        .id(zone.getId())
                        .build())
                .collect(Collectors.toList());
    }
}
