package ar.edu.utn.frc.tup.lciv.services.interfaces.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.ChapterDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IChapterService {
    List<ChapterDTO> getChapters(Integer numberChapter, Integer numberSeason);
}
