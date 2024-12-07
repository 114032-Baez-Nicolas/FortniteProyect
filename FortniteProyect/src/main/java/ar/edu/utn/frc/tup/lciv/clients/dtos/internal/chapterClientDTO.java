package ar.edu.utn.frc.tup.lciv.clients.dtos.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class chapterClientDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("numberChapter")
    private Integer numberChapter;

    @JsonProperty("image")
    private String image;

    @JsonProperty("seasons")
    private List<seasonClientDTO> seasons;

    @JsonProperty("id")
    private String id;
}
