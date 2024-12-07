package ar.edu.utn.frc.tup.lciv.clients.dtos.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class eventClientDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("players")
    private BigDecimal players;

    @JsonProperty("image")
    private String image;

    @JsonProperty("id")
    private String id;
}
