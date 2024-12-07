package ar.edu.utn.frc.tup.lciv.clients.dtos.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class rarityWithSkinsClientDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("total_skins")
    private Integer totalSkins;

    @JsonProperty("image")
    private String image;

    @JsonProperty("id")
    private String id;
}
