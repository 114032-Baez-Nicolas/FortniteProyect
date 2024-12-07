package ar.edu.utn.frc.tup.lciv.services.impl.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.NewsDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientExternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.external.NewsClientDTO;
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
public class NewsServiceTest {

    @Mock
    private FortniteClientExternal fortniteClientExternal;

    @InjectMocks
    private NewsService newsService;

    private NewsClientDTO news1;
    private NewsClientDTO news2;
    private NewsClientDTO news3;

    @BeforeEach
    void setUp() {
        news1 = new NewsClientDTO();
        news1.setId("1");
        news1.setTitle("News 1");
        news1.setTabTitle("Tab 1");
        news1.setBody("Body 1");
        news1.setImage("Image 1");
        news1.setTileImage("Tile Image 1");
        news1.setSortingPriority(1);
        news1.setHidden(false);

        news2 = new NewsClientDTO();
        news2.setId("2");
        news2.setTitle("News 2");
        news2.setTabTitle("Tab 2");
        news2.setBody("Body 2");
        news2.setImage("Image 2");
        news2.setTileImage("Tile Image 2");
        news2.setSortingPriority(2);
        news2.setHidden(false);

        news3 = new NewsClientDTO();
        news3.setId("3");
        news3.setTitle("News 3");
        news3.setTabTitle("Tab 3");
        news3.setBody("Body 3");
        news3.setImage("Image 3");
        news3.setTileImage("Tile Image 3");
        news3.setSortingPriority(3);
        news3.setHidden(true);
    }

    @Test
    void testGetAllNewsOrdered() {
        when(fortniteClientExternal.getAllNewsFromApi()).thenReturn(Arrays.asList(news1, news2, news3));

        List<NewsDTO> result = newsService.getAllNewsOrdered();

        assertEquals(2, result.size());
        assertEquals("2", result.get(0).getId());
        assertEquals("1", result.get(1).getId());
    }
}