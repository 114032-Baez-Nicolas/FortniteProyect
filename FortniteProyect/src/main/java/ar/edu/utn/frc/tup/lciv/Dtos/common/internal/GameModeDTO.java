package ar.edu.utn.frc.tup.lciv.Dtos.common.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameModeDTO {
    private String name;
    private Double usePercentage;
    private String id;
}
