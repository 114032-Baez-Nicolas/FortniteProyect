package ar.edu.utn.frc.tup.lciv.services.impl.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.ShopCommonDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientExternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.ShopClientDTO;
import ar.edu.utn.frc.tup.lciv.services.interfaces.external.IShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService implements IShopService {

    private final FortniteClientExternal fortniteClientExternal;

    @Override
    public ShopCommonDTO getShop(Integer price, String type) {
        ShopClientDTO shopClientDTO = fortniteClientExternal.getShopData();

        List<ShopClientDTO.Entry> entries = shopClientDTO.getData().getEntries();
        Set<String> uniqueIds = new HashSet<>();
        List<ShopCommonDTO.Item> items = entries.stream()
                .flatMap(entry -> {
                    if (entry.getTracks() != null && !entry.getTracks().isEmpty()) {
                        return entry.getTracks().stream().map(track -> new ShopCommonDTO.Item(
                                track.getId(),
                                track.getDevName(),
                                null,
                                entry.getFinalPrice(),
                                null,
                                null,
                                null,
                                null,
                                null,
                                track.getArtist(),
                                track.getAlbumArt(),
                                entry.getBundle() != null ? entry.getBundle().getImage() : null,
                                null
                        ));
                    } else if (entry.getBrItems() != null && !entry.getBrItems().isEmpty()) {
                        return entry.getBrItems().stream().map(item -> new ShopCommonDTO.Item(
                                item.getId(),
                                item.getName(),
                                item.getDescription(),
                                entry.getFinalPrice(),
                                item.getRarity() != null ? item.getRarity().getDisplayValue() : null,
                                item.getIntroduction() != null ? item.getIntroduction().getText() : null,
                                item.getIntroduction() != null ? item.getIntroduction().getChapter() : null,
                                item.getIntroduction() != null ? item.getIntroduction().getSeason() : null,
                                item.getImages() != null ? item.getImages().getFeatured() : null,
                                null,
                                null,
                                entry.getBundle() != null ? entry.getBundle().getImage() : null,
                                item.getType() != null ? item.getType().getDisplayValue() : null
                        ));
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(item -> uniqueIds.add(item.getId()))
                .filter(item -> price == null || item.getPrice() == price)
                .filter(item -> type == null || (item.getType() != null && item.getType().equalsIgnoreCase(type)))
                .collect(Collectors.toList());

        return new ShopCommonDTO(
                shopClientDTO.getData().getDate(),
                shopClientDTO.getData().getVbuckIcon(),
                items
        );
    }
}