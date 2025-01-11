package it.epicode.W5_Gestione_Viaggi1.prenotazione;

import it.epicode.W5_Gestione_Viaggi1.dipendente.Dipendente;
import it.epicode.W5_Gestione_Viaggi1.dipendente.DipendenteService;
import it.epicode.W5_Gestione_Viaggi1.exceptions.PrenotationException;
import it.epicode.W5_Gestione_Viaggi1.viaggio.Viaggio;
import it.epicode.W5_Gestione_Viaggi1.viaggio.ViaggioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepo prenotazioneRepo;
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private ViaggioService viaggioService;

    public List<Prenotazione> findAll() {
        return prenotazioneRepo.findAll();
    }

    public Prenotazione findById(Long id) {
        if (!prenotazioneRepo.existsById(id)) {
            throw new EntityNotFoundException("Prenotazione non trovata!");
        }
        return prenotazioneRepo.findById(id).get();
    }

    public Prenotazione createPrenotazione(@Valid PrenotazioneDTO prenotazioneDto) {
        if (prenotazioneRepo.prenotazioniStessaDataDiRichiesta(prenotazioneDto.getDipendenteId(), prenotazioneDto.getDataDiRichiesta()) > 0) {
            throw new PrenotationException("Il dipendente ha già prenotato un viaggio oggi!");
        } else if (prenotazioneRepo.prenotazioniStessaDataDiPartenza(prenotazioneDto.getDipendenteId(), viaggioService.findById(prenotazioneDto.getViaggioId()).getDataPartenza()) > 0) {
            throw new PrenotationException("Il dipendente ha già una prenotazione per questa data!");
        }
        Prenotazione prenotazione = new Prenotazione();
        BeanUtils.copyProperties(prenotazioneDto, prenotazione);
        Viaggio viaggio = viaggioService.findById(prenotazioneDto.getViaggioId());
        Dipendente dipendente = dipendenteService.findById(prenotazioneDto.getDipendenteId());
        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);
        return prenotazioneRepo.save(prenotazione);
    }

    public Prenotazione updatePrenotazione(Long id,@Valid Prenotazione modifiedPrenotazione) {
        Prenotazione dipendente = findById(id);

        BeanUtils.copyProperties(modifiedPrenotazione, dipendente);

        return prenotazioneRepo.save(dipendente);
    }

    public void deletePrenotazione(Long id) {
        prenotazioneRepo.deleteById(id);
    }


}
