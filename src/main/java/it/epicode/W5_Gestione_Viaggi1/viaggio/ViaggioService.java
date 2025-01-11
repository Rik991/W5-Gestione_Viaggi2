package it.epicode.W5_Gestione_Viaggi1.viaggio;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ViaggioService {

    @Autowired
    private ViaggioRepo viaggioRepo;

    public List<Viaggio> findAll() {
        return viaggioRepo.findAll();
    }

    public Viaggio findById(Long id) {
        if (!viaggioRepo.existsById(id)) {
            throw new EntityNotFoundException("Viaggio non trovato!");
        }

        return viaggioRepo.findById(id).get();
    }

    public Viaggio createViaggio(@Valid Viaggio viaggio) {
        return viaggioRepo.save(viaggio);
    }

    public Viaggio updateViaggio(Long id,@Valid Viaggio modifiedViaggio) {
        Viaggio viaggio = findById(id);

        BeanUtils.copyProperties(modifiedViaggio,viaggio);

        return viaggioRepo.save(viaggio);
    }

    //patch per settare lo stato del viaggio
    public Viaggio patchViaggio(Long id,@Valid Viaggio modifiedViaggio) {
        Viaggio viaggio = findById(id);
        viaggio.setStato(modifiedViaggio.getStato());
        return viaggioRepo.save(viaggio);
    }

    public void deleteViaggio(Long id) {
        viaggioRepo.deleteById(id);
    }

}
