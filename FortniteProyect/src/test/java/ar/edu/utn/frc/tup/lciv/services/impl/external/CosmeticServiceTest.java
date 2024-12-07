package ar.edu.utn.frc.tup.lciv.services.impl.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.CosmeticCommonDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientExternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.CosmeticClientDTO;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.CosmeticClientDTO.Cosmetic;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.CosmeticClientDTO.CosmeticData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CosmeticServiceTest {

    @Mock
    private FortniteClientExternal fortniteClientExternal;

    @InjectMocks
    private CosmeticService cosmeticService;

    private CosmeticClientDTO cosmeticClientDTO;

    @BeforeEach
    void setUp() {
        Cosmetic cosmetic = new Cosmetic();
        cosmetic.setId("1");
        cosmetic.setName("Test Cosmetic");
        cosmetic.setDescription("Test Description");
        Cosmetic.Rarity rarity = new Cosmetic.Rarity();
        rarity.setValue("rare");
        rarity.setDisplayValue("Rare");
        cosmetic.setRarity(rarity);
        Cosmetic.Introduction introduction = new Cosmetic.Introduction();
        introduction.setChapter("2");
        introduction.setSeason("3");
        cosmetic.setIntroduction(introduction);
        CosmeticData cosmeticData = new CosmeticData();
        cosmeticData.setBr(Collections.singletonList(cosmetic));
        cosmeticClientDTO = new CosmeticClientDTO();
        cosmeticClientDTO.setData(cosmeticData);
    }

    @Test
    void testGetCosmetics() {
        when(fortniteClientExternal.getCosmeticData()).thenReturn(cosmeticClientDTO);

        List<CosmeticCommonDTO> result = cosmeticService.getCosmetics("Test", "rare", "2", 10);

        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("Test Cosmetic", result.get(0).getName());
        assertEquals("Test Description", result.get(0).getDescription());
        assertEquals("Rare", result.get(0).getRarity());
        assertEquals("2", result.get(0).getChapter());
        assertEquals("3", result.get(0).getSeason());
    }

    @Test
    void testGetCosmeticsLimitExceeded() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cosmeticService.getCosmetics("Test", "rare", "2", 51);
        });

        assertEquals("El límite no puede ser mayor a 50.", exception.getMessage());
    }
}