package ar.edu.utn.frc.tup.lciv.clients.dtos.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopClientDTO {

    private ShopData data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ShopData {
        private String date;
        private String vbuckIcon;
        private List<Entry> entries;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Entry {
        private String offerId;
        private String devName;
        private int regularPrice;
        private int finalPrice;
        private boolean giftable;
        private boolean refundable;
        private Bundle bundle;
        private List<BrItem> brItems;
        private List<Track> tracks;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Bundle {
            private String name;
            private String info;
            private String image;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class BrItem {
            private String id;
            private String name;
            private String description;
            private Rarity rarity;
            private Introduction introduction;
            private Images images;
            private Type type;

            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Rarity {
                private String displayValue;
            }

            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Introduction {
                private String chapter;
                private String season;
                private String text;
            }

            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Images {
                private String featured;
            }

            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Type {
                private String value;
                private String displayValue;
            }
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Track {
            private String id;
            private String devName;
            private String title;
            private String artist;
            private String albumArt;
        }
    }
}