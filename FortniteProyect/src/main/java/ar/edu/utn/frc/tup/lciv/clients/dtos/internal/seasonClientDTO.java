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
public class seasonClientDTO {
    @JsonProperty("numberSeason")
    private Integer numberSeason;

    @JsonProperty("name")
    private String name;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;
}
