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
        private String hash;
        private String date;
        private String vbuckIcon;
        private List<Entry> entries;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Entry {
        private int regularPrice;
        private int finalPrice;
        private String devName;
        private String offerId;
        private String inDate;
        private String outDate;
        private boolean giftable;
        private boolean refundable;
        private Layout layout;
        private List<Track> tracks;
        private OfferTag offerTag;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Layout {
            private String id;
            private String name;
            private String category;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Track {
            private String id;
            private String title;
            private String artist;
            private String albumArt;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class OfferTag {
            private String id;
            private String text;
        }
    }
}
