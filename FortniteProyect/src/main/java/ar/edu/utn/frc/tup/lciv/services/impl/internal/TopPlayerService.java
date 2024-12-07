package ar.edu.utn.frc.tup.lciv.services.impl.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.TopPlayerDTO;
import ar.edu.utn.frc.tup.lciv.clients.dtos.internal.topPlayerClientDTO;
import ar.edu.utn.frc.tup.lciv.clients.FortniteClientInternal;
import ar.edu.utn.frc.tup.lciv.services.interfaces.internal.ITopPlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TopPlayerService implements ITopPlayerService {

    private final FortniteClientInternal fortniteClient;

    @Override
    public List<TopPlayerDTO> getTopPlayers(String name, Integer chapter) {
        Map<String, List<topPlayerClientDTO>> topPlayersByChapter = fortniteClient.getTopPlayers();
        List<TopPlayerDTO> allPlayers = new ArrayList<>();
        topPlayersByChapter.forEach((chapterKey, players) -> {
            Integer chapterNumber = Integer.parseInt(chapterKey.split("_")[1]);
            players.forEach(player -> allPlayers.add(TopPlayerDTO.builder()
                    .name(player.getName())
                    .description(player.getDescription())
                    .image(player.getImage())
                    .chapter(chapterNumber)
                    .build()));
        });
        if (name != null) {
            return allPlayers.stream()
                    .filter(player -> player.getName().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
        }
        if (chapter != null) {
            return allPlayers.stream()
                    .filter(player -> player.getChapter().equals(chapter))
                    .collect(Collectors.toList());
        }
        return allPlayers;
    }
}