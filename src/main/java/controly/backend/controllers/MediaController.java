package controly.backend.controllers;


import controly.backend.entities.MediaDataEntity;
import controly.backend.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "*")
public class MediaController {
    @Autowired
    private MediaService mediaService;

    // TO FIX: retornar a imagem
    @GetMapping("/{name}")
    public ResponseEntity<?>  getImageByName(@PathVariable("name") String name) throws SQLException {
        Blob image = mediaService.getImage(name);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<?> getImageInfoByName(@PathVariable("name") String name) throws SQLException {
        MediaDataEntity image = mediaService.getInfoByImageByName(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @PostMapping()
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException, SQLException {
        mediaService.uploadMedia(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Media uploaded successfully.");
    }
}
