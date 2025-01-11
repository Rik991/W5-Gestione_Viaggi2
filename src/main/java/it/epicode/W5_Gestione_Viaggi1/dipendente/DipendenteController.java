package it.epicode.W5_Gestione_Viaggi1.dipendente;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public ResponseEntity<List<Dipendente>> getAllDipendenti(){

        List<Dipendente> dipendenti = dipendenteService.findAll();

        return ResponseEntity.ok(dipendenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> findDipendenteById(@PathVariable Long id){
        return ResponseEntity.ok(dipendenteService.findById(id));
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Dipendente> createDipendente(@RequestParam("username") String username,
                                                       @RequestParam("nome") String nome,
                                                       @RequestParam("cognome") String cognome,
                                                       @RequestParam("email") String email,
                                                       @RequestParam("avatar") MultipartFile avatar) {
        Dipendente nuovoDipendente = new Dipendente();
        nuovoDipendente.setUsername(username);
        nuovoDipendente.setNome(nome);
        nuovoDipendente.setCognome(cognome);
        nuovoDipendente.setEmail(email);
        Dipendente dipendenteCreato = dipendenteService.createDipendenteWithAvatar(nuovoDipendente, avatar);
        return ResponseEntity.ok(dipendenteCreato);

    }

    @PatchMapping(path="/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Dipendente> uploadAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Dipendente dipendente = dipendenteService.uploadAvatar(id, file);
        return ResponseEntity.ok(dipendente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> updateDipendente(@PathVariable Long id, @RequestBody Dipendente modifiedDipendente) {
        return ResponseEntity.ok(dipendenteService.updateDipendente(id, modifiedDipendente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteDipendente(id);
        return new ResponseEntity<>("Dipendente eliminato correttamente!",HttpStatus.NO_CONTENT);
    }

}
