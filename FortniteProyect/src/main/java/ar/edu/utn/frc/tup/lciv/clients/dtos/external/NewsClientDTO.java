package ar.edu.utn.frc.tup.lciv.clients.dtos.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsClientDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("tabTitle")
    private String tabTitle;

    @JsonProperty("body")
    private String body;

    @JsonProperty("image")
    private String image;

    @JsonProperty("tileImage")
    private String tileImage;

    @JsonProperty("sortingPriority")
    private Integer sortingPriority;

    @JsonProperty("hidden")
    private Boolean hidden;
}
