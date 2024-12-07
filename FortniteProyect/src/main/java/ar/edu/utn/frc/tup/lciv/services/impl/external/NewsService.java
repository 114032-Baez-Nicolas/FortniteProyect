package ar.edu.utn.frc.tup.lciv.services.impl.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.NewsDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientExternal;
import ar.edu.utn.frc.tup.lciv.services.interfaces.external.INewsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsService implements INewsService {

    private final FortniteClientExternal fortniteClientExternal;

    @Override
    public List<NewsDTO> getAllNewsOrdered() {
        return fortniteClientExternal.getAllNewsFromApi().stream()
                .filter(news -> !news.getHidden())
                .map(news -> NewsDTO.builder()
                        .id(news.getId())
                        .title(news.getTitle())
                        .tabTitle(news.getTabTitle())
                        .body(news.getBody())
                        .image(news.getImage())
                        .tileImage(news.getTileImage())
                        .sortingPriority(news.getSortingPriority())
                        .build())
                .sorted(Comparator.comparing(NewsDTO::getSortingPriority).reversed())
                .collect(Collectors.toList());
    }
}
