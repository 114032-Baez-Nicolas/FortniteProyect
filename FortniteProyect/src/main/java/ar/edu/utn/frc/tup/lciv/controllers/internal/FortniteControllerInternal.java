package ar.edu.utn.frc.tup.lciv.controllers.internal;

import ar.edu.utn.frc.tup.lciv.Dtos.common.internal.*;
import ar.edu.utn.frc.tup.lciv.services.interfaces.internal.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fortnite")
@AllArgsConstructor
public class FortniteControllerInternal {

    private final IChapterService chapterService;
    private final IEventService eventService;
    private final IGameModeService gameModeService;
    private final IMapZoneService mapZoneService;
    private final IRarityWithSkinsService rarityWithSkinsService;
    private final ITopPlayerService topPlayerService;

    //1) Get Chapters
    @GetMapping("/chapters")
    public List<ChapterDTO> getChapters(
            @RequestParam(required = false) Integer numberChapter,
            @RequestParam(required = false) Integer numberSeason) {
        return chapterService.getChapters(numberChapter, numberSeason);
    }

    //2) Get Events
    @GetMapping("/events")
    public List<EventDTO> getAllEvents(@RequestParam(value = "name", required = false) String name) {
        return eventService.getAllEvents(name);
    }

    //3) Get Game Modes
    @GetMapping("/game-modes")
    public List<GameModeDTO> getGameModesOrdered() {
        return gameModeService.getGameModesOrdered();
    }

    //4) Get Map Zones
    @GetMapping("/maps-zones")
    public List<MapZoneDTO> getMapZones(@RequestParam(required = false) Integer chapter) {
        return mapZoneService.getMapZones(chapter);
    }

    //5) Get Rarities with Skins
    @GetMapping("/rarities-with-skins")
    public List<RarityWithSkinsDTO> getRaritiesWithSkins(@RequestParam(required = false) String name) {
        return rarityWithSkinsService.getRaritiesWithSkins(name);
    }

    //6) Get Top Players
    @GetMapping("/top-players")
    public List<TopPlayerDTO> getTopPlayers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer chapter) {
        return topPlayerService.getTopPlayers(name, chapter);
    }
}
