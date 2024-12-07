package ar.edu.utn.frc.tup.lciv.services.interfaces.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.RarityWithSkinsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRarityWithSkinsService {
    List<RarityWithSkinsDTO> getRaritiesWithSkins(String name);
}
