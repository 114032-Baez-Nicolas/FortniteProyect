package ar.edu.utn.frc.tup.lciv.services.interfaces.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.CosmeticCommonDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICosmeticService {
    List<CosmeticCommonDTO> getCosmetics(String name, String rarity, String chapter, int limit);
}
