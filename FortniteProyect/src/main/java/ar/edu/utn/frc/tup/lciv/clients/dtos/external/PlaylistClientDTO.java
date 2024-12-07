package ar.edu.utn.frc.tup.lciv.clients.dtos.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistClientDTO {
    private String id;
    private String name;
    private String description;
    @JsonProperty("gameType")
    private String gameType;
    @JsonProperty("minPlayers")
    private int minPlayers;
    @JsonProperty("maxPlayers")
    private int maxPlayers;
}
