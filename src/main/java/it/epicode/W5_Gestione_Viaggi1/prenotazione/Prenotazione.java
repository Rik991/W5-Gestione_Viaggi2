package it.epicode.W5_Gestione_Viaggi1.prenotazione;

import it.epicode.W5_Gestione_Viaggi1.dipendente.Dipendente;
import it.epicode.W5_Gestione_Viaggi1.viaggio.Viaggio;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "data_di_richiesta")
    private LocalDate dataDiRichiesta;

    @Column(name = "preferenze_dipendente")
    private String preferenzeDipendente;

    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

}