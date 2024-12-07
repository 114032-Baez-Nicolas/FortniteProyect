package ar.edu.utn.frc.tup.lciv.services.impl.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.ChapterDTO;
import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.SeasonDTO;
import ar.edu.utn.frc.tup.lciv.clients.dtos.internal.chapterClientDTO;
import ar.edu.utn.frc.tup.lciv.clients.dtos.internal.seasonClientDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientInternal;
import ar.edu.utn.frc.tup.lciv.services.interfaces.internal.IChapterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChapterService implements IChapterService {

    private final FortniteClientInternal fortniteClient;

    @Override
    public List<ChapterDTO> getChapters(Integer numberChapter, Integer numberSeason) {
        List<chapterClientDTO> chapters = fortniteClient.getChapters();

        if (numberChapter != null && numberSeason != null) {
            return chapters.stream()
                    .filter(chapter -> chapter.getNumberChapter().equals(numberChapter))
                    .map(chapter -> ChapterDTO.builder()
                            .name(chapter.getName())
                            .numberChapter(chapter.getNumberChapter())
                            .image(chapter.getImage())
                            .seasons(chapter.getSeasons().stream()
                                    .filter(season -> season.getNumberSeason().equals(numberSeason))
                                    .map(this::mapToSeasonDTO)
                                    .collect(Collectors.toList()))
                            .build())
                    .filter(chapter -> !chapter.getSeasons().isEmpty())
                    .collect(Collectors.toList());
        }

        if (numberChapter != null) {
            return chapters.stream()
                    .filter(chapter -> chapter.getNumberChapter().equals(numberChapter))
                    .map(this::mapToChapterDTO)
                    .collect(Collectors.toList());
        }

        if (numberSeason != null) {
            return chapters.stream()
                    .flatMap(chapter -> chapter.getSeasons().stream()
                            .filter(season -> season.getNumberSeason().equals(numberSeason))
                            .map(season -> ChapterDTO.builder()
                                    .name(chapter.getName())
                                    .numberChapter(chapter.getNumberChapter())
                                    .image(chapter.getImage())
                                    .seasons(List.of(mapToSeasonDTO(season)))
                                    .build()))
                    .collect(Collectors.toList());
        }

        return chapters.stream()
                .map(this::mapToChapterDTO)
                .collect(Collectors.toList());
    }

    private ChapterDTO mapToChapterDTO(chapterClientDTO chapter) {
        return ChapterDTO.builder()
                .name(chapter.getName())
                .numberChapter(chapter.getNumberChapter())
                .image(chapter.getImage())
                .seasons(chapter.getSeasons().stream()
                        .map(this::mapToSeasonDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private SeasonDTO mapToSeasonDTO(seasonClientDTO season) {
        return SeasonDTO.builder()
                .numberSeason(season.getNumberSeason())
                .name(season.getName())
                .startDate(season.getStartDate())
                .endDate(season.getEndDate())
                .build();
    }
}
