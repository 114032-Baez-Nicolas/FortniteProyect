package ar.edu.utn.frc.tup.lciv.Dtos.common.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeasonDTO {
    private Integer numberSeason;
    private String name;
    private String startDate;
    private String endDate;
}
