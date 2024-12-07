package ar.edu.utn.frc.tup.lciv.services.impl.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.MapDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientExternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.MapClientDTO;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.LocationClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MapServiceTest {

    @Mock
    private FortniteClientExternal fortniteClientExternal;

    @InjectMocks
    private MapService mapService;

    private MapClientDTO mapClientDTO;

    @BeforeEach
    void setUp() {
        LocationClientDTO location = new LocationClientDTO();
        location.setX(100.0);
        location.setY(200.0);
        location.setZ(300.0);

        mapClientDTO = new MapClientDTO();
        mapClientDTO.setId("1");
        mapClientDTO.setName("Test Map");
        mapClientDTO.setLocation(location);
    }

    @Test
    void testGetAllMapData() {
        when(fortniteClientExternal.getAllMapDataFromApi()).thenReturn(Collections.singletonList(mapClientDTO));

        List<MapDTO> result = mapService.getAllMapData();

        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("Test Map", result.get(0).getName());
        assertEquals(100.0, result.get(0).getX());
        assertEquals(200.0, result.get(0).getY());
        assertEquals(300.0, result.get(0).getZ());
    }
}