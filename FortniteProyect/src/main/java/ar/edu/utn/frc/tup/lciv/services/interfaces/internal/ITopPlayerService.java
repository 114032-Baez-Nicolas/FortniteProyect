package ar.edu.utn.frc.tup.lciv.services.interfaces.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.TopPlayerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITopPlayerService {
    List<TopPlayerDTO> getTopPlayers(String name, Integer chapter);
}
