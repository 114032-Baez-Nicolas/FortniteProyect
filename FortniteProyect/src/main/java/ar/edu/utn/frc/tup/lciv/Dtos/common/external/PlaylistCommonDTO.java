package ar.edu.utn.frc.tup.lciv.Dtos.common.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistCommonDTO {

    private String id;
    private String name;
    private String gameType;
    private String playerRange;
}
