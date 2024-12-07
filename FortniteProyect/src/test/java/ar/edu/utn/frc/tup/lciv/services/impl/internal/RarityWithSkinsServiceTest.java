package ar.edu.utn.frc.tup.lciv.services.impl.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.RarityWithSkinsDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientInternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.internal.rarityWithSkinsClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RarityWithSkinsServiceTest {

    @Mock
    private FortniteClientInternal fortniteClient;

    @InjectMocks
    private RarityWithSkinsService rarityWithSkinsService;

    private List<rarityWithSkinsClientDTO> rarityWithSkinsClientDTOList;

    @BeforeEach
    void setUp() {
        rarityWithSkinsClientDTOList = createRarityWithSkinsClientDTOList();
    }

    private List<rarityWithSkinsClientDTO> createRarityWithSkinsClientDTOList() {
        rarityWithSkinsClientDTO rarity1 = new rarityWithSkinsClientDTO();
        rarity1.setName("Rare");
        rarity1.setTotalSkins(50);
        rarity1.setImage("image1.png");
        rarity1.setId("1");

        rarityWithSkinsClientDTO rarity2 = new rarityWithSkinsClientDTO();
        rarity2.setName("Epic");
        rarity2.setTotalSkins(100);
        rarity2.setImage("image2.png");
        rarity2.setId("2");

        rarityWithSkinsClientDTO rarity3 = new rarityWithSkinsClientDTO();
        rarity3.setName("Legendary");
        rarity3.setTotalSkins(150);
        rarity3.setImage("image3.png");
        rarity3.setId("3");

        return Arrays.asList(rarity1, rarity2, rarity3);
    }

    @Test
    void testGetRaritiesWithSkinsWithName() {
        when(fortniteClient.getRaritiesWithSkins()).thenReturn(rarityWithSkinsClientDTOList);

        List<RarityWithSkinsDTO> result = rarityWithSkinsService.getRaritiesWithSkins("Epic");

        assertEquals(1, result.size());
        assertEquals("Epic", result.get(0).getName());
        assertEquals(100, result.get(0).getTotalSkins());
        assertEquals("image2.png", result.get(0).getImage());
        assertEquals("2", result.get(0).getId());
    }

    @Test
    void testGetRaritiesWithSkinsWithoutName() {
        when(fortniteClient.getRaritiesWithSkins()).thenReturn(rarityWithSkinsClientDTOList);

        List<RarityWithSkinsDTO> result = rarityWithSkinsService.getRaritiesWithSkins(null);

        assertEquals(3, result.size());
        assertEquals("Legendary", result.get(0).getName());
        assertEquals(150, result.get(0).getTotalSkins());
        assertEquals("image3.png", result.get(0).getImage());
        assertEquals("3", result.get(0).getId());

        assertEquals("Epic", result.get(1).getName());
        assertEquals(100, result.get(1).getTotalSkins());
        assertEquals("image2.png", result.get(1).getImage());
        assertEquals("2", result.get(1).getId());

        assertEquals("Rare", result.get(2).getName());
        assertEquals(50, result.get(2).getTotalSkins());
        assertEquals("image1.png", result.get(2).getImage());
        assertEquals("1", result.get(2).getId());
    }
}