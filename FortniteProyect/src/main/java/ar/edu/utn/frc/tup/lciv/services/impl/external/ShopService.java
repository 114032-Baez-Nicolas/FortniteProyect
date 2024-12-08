package ar.edu.utn.frc.tup.lciv.services.impl.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.ShopCommonDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientExternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.ShopClientDTO;
import ar.edu.utn.frc.tup.lciv.services.interfaces.external.IShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService implements IShopService {

    private final FortniteClientExternal fortniteClientExternal;

    private static final List<String> RARITY_ORDER = List.of(
            "Common", "Uncommon", "Rare", "Epic", "Legendary", "MARVEL SERIES", "Icon Series", "Gaming Legends Series", "DC SERIES", "Star Wars Series"
    );

    @Override
    public ShopCommonDTO getShop(Integer price, String type) {
        ShopClientDTO shopClientDTO = fortniteClientExternal.getShopData();

        if (shopClientDTO == null || shopClientDTO.getData() == null || shopClientDTO.getData().getEntries() == null) {
            return new ShopCommonDTO(null, null, List.of());
        }

        List<ShopClientDTO.Entry> entries = shopClientDTO.getData().getEntries();

        Map<String, ShopCommonDTO.Item> itemMap = new HashMap<>();

        entries.stream()
                .flatMap(entry -> {
                    if (entry.getBrItems() != null) {
                        return entry.getBrItems().stream()
                                .filter(brItem -> brItem.getType() != null &&
                                        ("outfit".equalsIgnoreCase(brItem.getType().getValue()) ||
                                                "emote".equalsIgnoreCase(brItem.getType().getValue())))
                                .map(brItem -> new ShopCommonDTO.Item(
                                        brItem.getId(),
                                        brItem.getName(),
                                        brItem.getDescription(),
                                        entry.getFinalPrice(),
                                        brItem.getRarity() != null ? brItem.getRarity().getDisplayValue() : null,
                                        brItem.getIntroduction() != null ? brItem.getIntroduction().getText() : null,
                                        brItem.getIntroduction() != null ? brItem.getIntroduction().getChapter() : null,
                                        brItem.getIntroduction() != null ? brItem.getIntroduction().getSeason() : null,
                                        getBestImage(brItem, entry),
                                        getOfferImage(entry),
                                        brItem.getType() != null ? brItem.getType().getDisplayValue() : null
                                ));
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .forEach(item -> {
                    itemMap.merge(item.getId(), item, (existing, newItem) ->
                            newItem.getPrice() < existing.getPrice() ? newItem : existing);
                });

        List<ShopCommonDTO.Item> items = itemMap.values().stream()
                .filter(item -> price == null || item.getPrice() == price)
                .filter(item -> type == null || (item.getType() != null && item.getType().equalsIgnoreCase(type)))
                .sorted(Comparator.comparing(item -> RARITY_ORDER.indexOf(item.getRarity()), Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());

        return new ShopCommonDTO(
                shopClientDTO.getData().getDate(),
                shopClientDTO.getData().getVbuckIcon(),
                items
        );
    }

    private String getBestImage(ShopClientDTO.BrItem brItem, ShopClientDTO.Entry entry) {
        if (brItem.getImages() != null && brItem.getImages().getFeatured() != null) {
            return brItem.getImages().getFeatured();
        }
        if (entry.getBundle() != null && entry.getBundle().getImage() != null) {
            return entry.getBundle().getImage();
        }
        if (entry.getNewDisplayAsset() != null &&
                entry.getNewDisplayAsset().getRenderImages() != null &&
                !entry.getNewDisplayAsset().getRenderImages().isEmpty()) {
            return entry.getNewDisplayAsset().getRenderImages().get(0).getImage();
        }
        return getOfferImage(entry);
    }

    private String getOfferImage(ShopClientDTO.Entry entry) {
        if (entry.getNewDisplayAsset() != null &&
                entry.getNewDisplayAsset().getMaterialInstances() != null &&
                !entry.getNewDisplayAsset().getMaterialInstances().isEmpty()) {
            return entry.getNewDisplayAsset().getMaterialInstances().get(0).getImages().getOfferImage();
        }
        return null;
    }
}
