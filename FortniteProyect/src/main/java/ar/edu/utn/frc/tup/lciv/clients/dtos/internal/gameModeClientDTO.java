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
public class gameModeClientDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("use_percentage")
    private Double usePercentage;

    @JsonProperty("id")
    private String id;
}
