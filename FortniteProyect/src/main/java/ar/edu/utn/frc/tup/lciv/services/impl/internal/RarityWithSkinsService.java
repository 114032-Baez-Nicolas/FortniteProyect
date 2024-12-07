package ar.edu.utn.frc.tup.lciv.services.impl.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.RarityWithSkinsDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientInternal;
import ar.edu.utn.frc.tup.lciv.services.interfaces.internal.IRarityWithSkinsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RarityWithSkinsService implements IRarityWithSkinsService {

    private final FortniteClientInternal fortniteClient;

    @Override
    public List<RarityWithSkinsDTO> getRaritiesWithSkins(String name) {
        return fortniteClient.getRaritiesWithSkins()
                .stream()
                .filter(rarity -> name == null || rarity.getName().equalsIgnoreCase(name))
                .sorted((a, b) -> b.getTotalSkins().compareTo(a.getTotalSkins()))
                .map(rarity -> RarityWithSkinsDTO.builder()
                        .name(rarity.getName())
                        .totalSkins(rarity.getTotalSkins())
                        .image(rarity.getImage())
                        .id(rarity.getId())
                        .build())
                .collect(Collectors.toList());
    }
}
