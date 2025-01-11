package it.epicode.W5_Gestione_Viaggi1.viaggio;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "La destinazione non può essere vuota")
    private String destinazione;

    @FutureOrPresent(message = "La data di partenza non può essere passata")
    @Column(name = "data_partenza")
    private LocalDate dataPartenza;


    @Column(name = "stato_viaggio")
    @Enumerated(EnumType.STRING)
    private ViaggioStato stato;


}