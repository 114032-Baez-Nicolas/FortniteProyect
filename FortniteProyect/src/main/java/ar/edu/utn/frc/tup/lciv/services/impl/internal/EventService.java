package ar.edu.utn.frc.tup.lciv.services.impl.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.EventDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientInternal;
import ar.edu.utn.frc.tup.lciv.services.interfaces.internal.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService implements IEventService {

    private final FortniteClientInternal fortniteClient;

    @Override
    public List<EventDTO> getAllEvents(String name) {
        return fortniteClient.getEvents()
                .stream()
                .filter(event -> name == null || event.getName().toLowerCase().contains(name.toLowerCase()))
                .map(event -> EventDTO.builder()
                        .name(event.getName())
                        .players(event.getPlayers().longValue())
                        .image(event.getImage())
                        .id(String.valueOf(event.getId()))
                        .build())
                .collect(Collectors.toList());
    }
}
