package ar.edu.utn.frc.tup.lciv.Dtos.common.internal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameModeDTO {
    private String name;
    private double usePercentage;
    private String id;
    private String image;
    private String description;
}