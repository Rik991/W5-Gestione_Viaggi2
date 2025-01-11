package it.epicode.W5_Gestione_Viaggi1.prenotazione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Long> {

    //query per gestire il fatto che un dipendente non possa avere più di una prenotazione per lo stesso giorno
    @Query("SELECT COUNT(p) FROM Prenotazione p WHERE p.dipendente.id = :dipendenteId AND p.dataDiRichiesta = :dataDiRichiesta")
    int prenotazioniStessaDataDiRichiesta(Long dipendenteId, LocalDate dataDiRichiesta);

    //query per gestire il fatto che un dipendente non possa prenotare un viaggio se ne ha già prenotato uno per la stessa data
    @Query("SELECT COUNT(p) FROM Prenotazione p WHERE p.dipendente.id = :dipendenteId AND p.viaggio.dataPartenza = :dataPartenza")
    int prenotazioniStessaDataDiPartenza(Long dipendenteId, LocalDate dataPartenza);

}
