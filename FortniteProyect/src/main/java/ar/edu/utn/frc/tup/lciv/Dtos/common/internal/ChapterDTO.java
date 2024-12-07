package ar.edu.utn.frc.tup.lciv.Dtos.common.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChapterDTO {
    private String name;
    private Integer numberChapter;
    private String image;
    private List<SeasonDTO> seasons;
}
