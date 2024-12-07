package ar.edu.utn.frc.tup.lciv.Dtos.common.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CosmeticCommonDTO {
    private String id;
    private String name;
    private String description;
    private String rarity;
    private String chapter;
    private String season;
    private String icon;
}
