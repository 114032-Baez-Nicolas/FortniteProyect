package ar.edu.utn.frc.tup.lciv.services.interfaces.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.EventDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEventService {
    List<EventDTO> getAllEvents(String name);
}
