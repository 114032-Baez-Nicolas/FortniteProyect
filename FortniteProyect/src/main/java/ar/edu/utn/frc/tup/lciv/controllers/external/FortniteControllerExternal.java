package ar.edu.utn.frc.tup.lciv.controllers.external;

import ar.edu.utn.frc.tup.lciv.Dtos.common.external.*;
import ar.edu.utn.frc.tup.lciv.services.interfaces.external.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/external/fortnite")
@AllArgsConstructor
public class FortniteControllerExternal {

    private final INewsService newsService;
    private final IMapService mapService;
    private final IPlaylistService playlistService;
    private final IShopService shopService;
    private final ICosmeticService cosmeticService;

    //1) Get news
    @GetMapping("/news")
    public List<NewsDTO> getAllNews() {
        return newsService.getAllNewsOrdered();
    }

    //2) Get map
    @GetMapping("/map")
    public List<MapDTO> getAllMapData() {
        return mapService.getAllMapData();
    }

    //3) Get playlists
    @GetMapping("/playlists")
    public ResponseEntity<?> getPlaylists(@RequestParam(value = "id", required = false) String id) {
        if (id == null) {
            List<PlaylistCommonDTO> playlists = playlistService.getAllPlaylists();
            return ResponseEntity.ok(playlists);
        } else {
            PlaylistCommonDTO playlist = playlistService.getPlaylistById(id);
            if (playlist == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(playlist);
        }
    }

    //4) Get shop
    @GetMapping("/shop")
    public ShopCommonDTO getShop(@RequestParam(value = "price", required = false) Integer price) {
        return shopService.getShop(price);
    }

    //5) Get cosmetics
    @GetMapping("/cosmetics")
    public List<CosmeticCommonDTO> getCosmetics(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String rarity,
            @RequestParam(required = false) String chapter,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return cosmeticService.getCosmetics(name, rarity, chapter, limit);
    }
}
