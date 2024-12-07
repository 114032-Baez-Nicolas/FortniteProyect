package ar.edu.utn.frc.tup.lciv.services.impl.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.EventDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientInternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.internal.eventClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private FortniteClientInternal fortniteClient;

    @InjectMocks
    private EventService eventService;

    private List<eventClientDTO> eventClientDTOList;

    @BeforeEach
    void setUp() {
        eventClientDTOList = createEventClientDTOList();
    }

    private List<eventClientDTO> createEventClientDTOList() {
        eventClientDTO eventClientDTO = new eventClientDTO();
        eventClientDTO.setName("Test Event");

        int intValue = 100;
        BigDecimal bigDecimalValue = BigDecimal.valueOf(intValue);
        eventClientDTO.setPlayers(bigDecimalValue);
        eventClientDTO.setImage("Test Image");
        eventClientDTO.setId(String.valueOf(1));

        return Collections.singletonList(eventClientDTO);
    }

    @Test
    void testGetAllEvents() {
        when(fortniteClient.getEvents()).thenReturn(eventClientDTOList);

        List<EventDTO> result = eventService.getAllEvents("Test");

        assertEquals(1, result.size());
        assertEquals("Test Event", result.get(0).getName());
        assertEquals(100, result.get(0).getPlayers());
        assertEquals("Test Image", result.get(0).getImage());
        assertEquals("1", result.get(0).getId());
    }
}