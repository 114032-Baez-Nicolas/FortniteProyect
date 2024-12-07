package ar.edu.utn.frc.tup.lciv.services.impl.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.ShopCommonDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientExternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.ShopClientDTO;
import ar.edu.utn.frc.tup.lciv.services.interfaces.external.IShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService implements IShopService {

    private final FortniteClientExternal fortniteClientExternal;

    @Override
    public ShopCommonDTO getShop(Integer price) {
        ShopClientDTO shopClientDTO = fortniteClientExternal.getShopData();

        List<ShopClientDTO.Entry> entries = shopClientDTO.getData().getEntries();

        List<ShopClientDTO.Entry> filteredEntries = entries.stream()
                .filter(entry -> price == null || entry.getFinalPrice() == price)
                .sorted((e1, e2) -> Integer.compare(e2.getFinalPrice(), e1.getFinalPrice()))
                .collect(Collectors.toList());

        return new ShopCommonDTO(
                shopClientDTO.getData().getDate(),
                shopClientDTO.getData().getVbuckIcon(),
                filteredEntries.stream()
                        .map(entry -> new ShopCommonDTO.Item(
                                entry.getDevName(),
                                entry.getFinalPrice(),
                                entry.getOfferId(),
                                entry.isGiftable(),
                                entry.isRefundable(),
                                entry.getTracks() != null && !entry.getTracks().isEmpty()
                                        ? entry.getTracks().get(0).getArtist()
                                        : null,
                                entry.getTracks() != null && !entry.getTracks().isEmpty()
                                        ? entry.getTracks().get(0).getAlbumArt()
                                        : null
                        ))
                        .collect(Collectors.toList())
        );
    }
}
