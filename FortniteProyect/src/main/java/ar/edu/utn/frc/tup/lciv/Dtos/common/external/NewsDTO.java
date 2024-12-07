package ar.edu.utn.frc.tup.lciv.Dtos.common.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {
    private String id;
    private String title;
    private String tabTitle;
    private String body;
    private String image;
    private String tileImage;
    private Integer sortingPriority;
}
