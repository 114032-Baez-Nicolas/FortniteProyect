package ar.edu.utn.frc.tup.lciv.Dtos.common.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCommonDTO {
    private String date;
    private String vbuckIconUrl;
    private List<Item> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private String name;
        private int price;
        private String offerId;
        private boolean giftable;
        private boolean refundable;
        private String artist;
        private String albumArt;
    }
}
