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
        private String id;
        private String name;
        private String description;
        private int price;
        private String rarity;
        private String introductionText;
        private String chapter;
        private String season;
        private String featuredImage;
        private String artist;
        private String albumArt;
        private String bundleImage;
        private String type;
    }
}

