package it.epicode.W5_Gestione_Viaggi1.prenotazione;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDTO {

    @NotNull(message = "La data di richiesta non può essere vuota")
    private LocalDate dataDiRichiesta;

    private String preferenzeDipendente;

    @Min(value = 1, message = "L'id del viaggio non può essere minore di 1")
    private Long viaggioId;

    @Min(value = 1, message = "L'id del dipendente non può essere minore di 1")
    private Long dipendenteId;



}
