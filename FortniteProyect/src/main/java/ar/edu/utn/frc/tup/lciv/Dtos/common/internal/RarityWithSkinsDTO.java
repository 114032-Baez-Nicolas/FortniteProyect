package ar.edu.utn.frc.tup.lciv.Dtos.common.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RarityWithSkinsDTO {
    private String name;
    private Integer totalSkins;
    private String image;
    private String id;
}
