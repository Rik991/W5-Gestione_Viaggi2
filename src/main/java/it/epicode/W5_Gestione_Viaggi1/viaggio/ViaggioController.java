package it.epicode.W5_Gestione_Viaggi1.viaggio;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public ResponseEntity<List<Viaggio>> getAllViaggi(){

        List<Viaggio> viaggi = viaggioService.findAll();

        return ResponseEntity.ok(viaggi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaggio> findViaggioById(@PathVariable Long id){
        return ResponseEntity.ok(viaggioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Viaggio> createViaggio(@RequestBody Viaggio viaggio) {
        return new ResponseEntity<>(viaggioService.createViaggio(viaggio), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaggio> updateViaggio(@PathVariable Long id, @RequestBody Viaggio modifiedViaggio) {
        return ResponseEntity.ok(viaggioService.updateViaggio(id, modifiedViaggio));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Viaggio> patchViaggio(@PathVariable Long id, @RequestBody Viaggio modifiedViaggio) {
        return ResponseEntity.ok(viaggioService.patchViaggio(id, modifiedViaggio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteViaggio(@PathVariable Long id) {
        viaggioService.deleteViaggio(id);
        return new ResponseEntity<>("Viaggio eliminato correttamente!",HttpStatus.NO_CONTENT);
    }

}
