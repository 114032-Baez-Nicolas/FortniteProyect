package ar.edu.utn.frc.tup.lciv.clients;

import ar.edu.utn.frc.tup.lciv.clients.dtos.internal.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FortniteClientInternal {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String url = "http://localhost:3000";

    //1) Get events method
    public List<eventClientDTO> getEvents() {
        String endpoint = url + "/events";
        eventClientDTO[] events = restTemplate.getForObject(endpoint, eventClientDTO[].class);
        return Arrays.asList(events);
    }

    //2) Get game modes method
    public List<gameModeClientDTO> getGameModes() {
        String endpoint = url + "/game_modes";
        gameModeClientDTO[] gameModes = restTemplate.getForObject(endpoint, gameModeClientDTO[].class);
        return Arrays.asList(gameModes);
    }

    //3) Get map zones method
    public List<mapZoneClientDTO> getMapZones() {
        String endpoint = url + "/maps_zones";
        mapZoneClientDTO[] mapZones = restTemplate.getForObject(endpoint, mapZoneClientDTO[].class);
        return Arrays.asList(mapZones);
    }

    //4) Get rarities with skins method
    public List<rarityWithSkinsClientDTO> getRaritiesWithSkins() {
        String endpoint = url + "/rarities_with_skins";
        rarityWithSkinsClientDTO[] rarities = restTemplate.getForObject(endpoint, rarityWithSkinsClientDTO[].class);
        return Arrays.asList(rarities);
    }

    //5) Get top players method
    public Map<String, List<topPlayerClientDTO>> getTopPlayers() {
        String endpoint = url + "/top_players";

        Map<String, Object> response = restTemplate.getForObject(endpoint, Map.class);

        return response.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> objectMapper.convertValue(
                                entry.getValue(),
                                new TypeReference<List<topPlayerClientDTO>>() {}
                        )
                ));
    }

    //6) Get chapters and seasons method
    public List<chapterClientDTO> getChapters() {
        String endpoint = url + "/chapters";
        return List.of(restTemplate.getForObject(endpoint, chapterClientDTO[].class));
    }
}
