package it.epicode.W5_Gestione_Viaggi1.cloudinary;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@RequiredArgsConstructor
public class CloudinaryUploadController {

    private final CloudinaryService cloudinaryService;

    @PostMapping(path="/upload", consumes = "multipart/form-data")
    public ResponseEntity<Map> uploadFile( @RequestPart("file") MultipartFile file) {
        String folder = "test";

        Map result = cloudinaryService.uploader(file, folder);
        // se si vuole restituire solo url usare il get della mappa
        // result.get("url");

        return ResponseEntity.ok(result);
    }


}
