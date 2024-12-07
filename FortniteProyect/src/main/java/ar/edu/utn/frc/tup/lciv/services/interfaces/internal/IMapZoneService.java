package ar.edu.utn.frc.tup.lciv.services.interfaces.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.MapZoneDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMapZoneService {
    List<MapZoneDTO> getMapZones(Integer chapter);
}
