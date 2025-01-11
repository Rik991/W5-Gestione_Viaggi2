package it.epicode.W5_Gestione_Viaggi1.prenotazione;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public ResponseEntity<List<Prenotazione>> getAllPrenotazioni(){
        List<Prenotazione> prenotazioni = prenotazioneService.findAll();
        return ResponseEntity.ok(prenotazioni);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prenotazione> findPrenotazioneById(Long id){
        return ResponseEntity.ok(prenotazioneService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Prenotazione> createPrenotazione(@RequestBody PrenotazioneDTO request){
        return new ResponseEntity<>(prenotazioneService.createPrenotazione(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prenotazione> updatePrenotazione(Long id,Prenotazione modifiedPrenotazione){
        return ResponseEntity.ok(prenotazioneService.updatePrenotazione(id,modifiedPrenotazione));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrenotazione(Long id){
        prenotazioneService.deletePrenotazione(id);
        return ResponseEntity.ok("Prenotazione eliminata correttamente!");
    }


}
