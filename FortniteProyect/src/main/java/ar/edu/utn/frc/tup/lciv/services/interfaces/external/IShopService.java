package ar.edu.utn.frc.tup.lciv.services.interfaces.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.ShopCommonDTO;
import org.springframework.stereotype.Service;

@Service
public interface IShopService {
    ShopCommonDTO getShop(Integer price);
}
