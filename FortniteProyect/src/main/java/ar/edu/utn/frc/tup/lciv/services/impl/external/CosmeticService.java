package ar.edu.utn.frc.tup.lciv.services.impl.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.CosmeticCommonDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientExternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.CosmeticClientDTO;
import ar.edu.utn.frc.tup.lciv.services.interfaces.external.ICosmeticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CosmeticService implements ICosmeticService {

    private final FortniteClientExternal fortniteClientExternal;

    @Override
    public List<CosmeticCommonDTO> getCosmetics(String name, String rarity, String chapter, String type, int limit) {
        if (limit > 2500) {
            throw new IllegalArgumentException("El límite no puede ser mayor a 2500.");
        }

        CosmeticClientDTO cosmeticClientDTO = fortniteClientExternal.getCosmeticData();

        return cosmeticClientDTO.getData().getBr().stream()
                .peek(cosmetic -> {
                    if (cosmetic.getIntroduction() != null
                            && "2".equals(cosmetic.getIntroduction().getChapter())
                            && "remix".equalsIgnoreCase(cosmetic.getIntroduction().getSeason())) {
                        cosmetic.getIntroduction().setChapter("5");
                    }
                })
                .filter(cosmetic -> name == null || cosmetic.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(cosmetic -> rarity == null || rarity.equalsIgnoreCase(cosmetic.getRarity().getValue()))
                .filter(cosmetic -> chapter == null || (cosmetic.getIntroduction() != null
                        && chapter.equalsIgnoreCase(cosmetic.getIntroduction().getChapter())))
                .filter(cosmetic -> cosmetic.getType() == null
                        || (!"emoji".equalsIgnoreCase(cosmetic.getType().getValue())
                        && !"pet".equalsIgnoreCase(cosmetic.getType().getValue())))
                .filter(cosmetic -> type == null || (cosmetic.getType() != null && type.equalsIgnoreCase(cosmetic.getType().getValue())))
                .sorted((c1, c2) -> {
                    String chapter1 = c1.getIntroduction() != null ? c1.getIntroduction().getChapter() : "0";
                    String chapter2 = c2.getIntroduction() != null ? c2.getIntroduction().getChapter() : "0";
                    return chapter2.compareTo(chapter1);
                })
                .limit(limit)
                .map(cosmetic -> new CosmeticCommonDTO(
                        cosmetic.getId(),
                        cosmetic.getName(),
                        cosmetic.getDescription() != null ? cosmetic.getDescription() : "No Description",
                        cosmetic.getRarity().getDisplayValue(),
                        cosmetic.getIntroduction() != null ? cosmetic.getIntroduction().getChapter() : "Unknown",
                        cosmetic.getIntroduction() != null ? cosmetic.getIntroduction().getSeason() : "Unknown",
                        cosmetic.getImages() != null ? cosmetic.getImages().getIcon() : null
                ))
                .collect(Collectors.toList());
    }
}
