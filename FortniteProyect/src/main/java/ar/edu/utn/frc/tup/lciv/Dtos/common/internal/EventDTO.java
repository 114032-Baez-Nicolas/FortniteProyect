package ar.edu.utn.frc.tup.lciv.Dtos.common.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {
    private String name;
    private Long players;
    private String image;
    private String id;
}
