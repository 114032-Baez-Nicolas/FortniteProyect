package ar.edu.utn.frc.tup.lciv.clients;

import ar.edu.utn.frc.tup.lciv.clients.dtos.external.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class FortniteClientExternal {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://fortnite-api.com";

    // 1) Get news method
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class NewsResponse {
        @JsonProperty("data")
        private DataContent data;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        private static class DataContent {
            @JsonProperty("br")
            private BrContent br;

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            private static class BrContent {
                @JsonProperty("motds")
                private List<NewsClientDTO> motds;
            }
        }
    }

    // A) News
    public List<NewsClientDTO> getAllNewsFromApi() {
        String endpoint = "/v2/news";
        String url = BASE_URL + endpoint;
        NewsResponse response = restTemplate.getForObject(url, NewsResponse.class);
        return response.getData().getBr().getMotds();
    }

    // 2) Get map method
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class MapResponse {
        @JsonProperty("data")
        private DataContent data;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        private static class DataContent {
            @JsonProperty("pois")
            private List<MapClientDTO> pois;
        }
    }

    //A) Map
    public List<MapClientDTO> getAllMapDataFromApi() {
        String endpoint = "/v1/map";
        String url = BASE_URL + endpoint;
        MapResponse response = restTemplate.getForObject(url, MapResponse.class);
        return response.getData().getPois();
    }

    //3) Get playlists method
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class PlaylistResponse {
        @JsonProperty("data")
        private List<PlaylistClientDTO> data;
    }

    //A) Playlists
    public List<PlaylistClientDTO> getPlaylists() {
        String url = BASE_URL + "/v1/playlists";
        PlaylistResponse response = restTemplate.getForObject(url, PlaylistResponse.class);
        return response.getData();
    }

    //4) Item Shop
    public ShopClientDTO getShopData() {
        String url = BASE_URL + "/v2/shop";
        return restTemplate.getForObject(url, ShopClientDTO.class);
    }

    //5) Cosmetics
    public CosmeticClientDTO getCosmeticData() {
        String url = BASE_URL + "/v2/cosmetics";
        return restTemplate.getForObject(url, CosmeticClientDTO.class);
    }
}
