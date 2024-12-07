package ar.edu.utn.frc.tup.lciv.Dtos.common.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MapDTO {
    private String id;
    private String name;
    private Double x;
    private Double y;
    private Double z;
}
