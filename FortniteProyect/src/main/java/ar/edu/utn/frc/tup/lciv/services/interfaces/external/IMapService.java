package ar.edu.utn.frc.tup.lciv.services.interfaces.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.MapDTO;

import java.util.List;

public interface IMapService {
    List<MapDTO> getAllMapData();
}
