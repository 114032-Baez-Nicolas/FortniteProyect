package ar.edu.utn.frc.tup.lciv.services.impl.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.ChapterDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientInternal;
import ar.edu.utn.frc.tup.lciv.clients.dtos.internal.chapterClientDTO;
import ar.edu.utn.frc.tup.lciv.clients.dtos.internal.seasonClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChapterServiceTest {

    @Mock
    private FortniteClientInternal fortniteClient;

    @InjectMocks
    private ChapterService chapterService;

    private List<chapterClientDTO> chapterClientDTOList;

    @BeforeEach
    void setUp() {
        chapterClientDTOList = createChapterClientDTOList();
    }

    private List<chapterClientDTO> createChapterClientDTOList() {
        seasonClientDTO season1 = new seasonClientDTO();
        season1.setNumberSeason(1);
        season1.setName("Season 1");
        season1.setStartDate("2021-01-01");
        season1.setEndDate("2021-03-01");

        seasonClientDTO season2 = new seasonClientDTO();
        season2.setNumberSeason(2);
        season2.setName("Season 2");
        season2.setStartDate("2021-03-02");
        season2.setEndDate("2021-06-01");

        chapterClientDTO chapter1 = new chapterClientDTO();
        chapter1.setNumberChapter(1);
        chapter1.setName("Chapter 1");
        chapter1.setImage("image1.png");
        chapter1.setSeasons(List.of(season1, season2));

        chapterClientDTO chapter2 = new chapterClientDTO();
        chapter2.setNumberChapter(2);
        chapter2.setName("Chapter 2");
        chapter2.setImage("image2.png");
        chapter2.setSeasons(Collections.singletonList(season1));

        return List.of(chapter1, chapter2);
    }

    @Test
    void testGetChaptersWithNumberChapterAndNumberSeason() {
        when(fortniteClient.getChapters()).thenReturn(chapterClientDTOList);

        List<ChapterDTO> result = chapterService.getChapters(1, 1);

        assertEquals(1, result.size());
        assertEquals("Chapter 1", result.get(0).getName());
        assertEquals(1, result.get(0).getNumberChapter());
        assertEquals("image1.png", result.get(0).getImage());
        assertEquals(1, result.get(0).getSeasons().size());
        assertEquals("Season 1", result.get(0).getSeasons().get(0).getName());
    }

    @Test
    void testGetChaptersWithNumberChapter() {
        when(fortniteClient.getChapters()).thenReturn(chapterClientDTOList);

        List<ChapterDTO> result = chapterService.getChapters(1, null);

        assertEquals(1, result.size());
        assertEquals("Chapter 1", result.get(0).getName());
        assertEquals(1, result.get(0).getNumberChapter());
        assertEquals("image1.png", result.get(0).getImage());
        assertEquals(2, result.get(0).getSeasons().size());
    }

    @Test
    void testGetChaptersWithNumberSeason() {
        when(fortniteClient.getChapters()).thenReturn(chapterClientDTOList);

        List<ChapterDTO> result = chapterService.getChapters(null, 1);

        assertEquals(2, result.size());
        assertEquals("Chapter 1", result.get(0).getName());
        assertEquals(1, result.get(0).getNumberChapter());
        assertEquals("image1.png", result.get(0).getImage());
        assertEquals(1, result.get(0).getSeasons().size());
        assertEquals("Season 1", result.get(0).getSeasons().get(0).getName());

        assertEquals("Chapter 2", result.get(1).getName());
        assertEquals(2, result.get(1).getNumberChapter());
        assertEquals("image2.png", result.get(1).getImage());
        assertEquals(1, result.get(1).getSeasons().size());
        assertEquals("Season 1", result.get(1).getSeasons().get(0).getName());
    }

    @Test
    void testGetChaptersWithoutFilters() {
        when(fortniteClient.getChapters()).thenReturn(chapterClientDTOList);

        List<ChapterDTO> result = chapterService.getChapters(null, null);

        assertEquals(2, result.size());
        assertEquals("Chapter 1", result.get(0).getName());
        assertEquals(1, result.get(0).getNumberChapter());
        assertEquals("image1.png", result.get(0).getImage());
        assertEquals(2, result.get(0).getSeasons().size());

        assertEquals("Chapter 2", result.get(1).getName());
        assertEquals(2, result.get(1).getNumberChapter());
        assertEquals("image2.png", result.get(1).getImage());
        assertEquals(1, result.get(1).getSeasons().size());
    }
}