package it.epicode.W5_Gestione_Viaggi1.dipendente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class DipendenteAvatarRequest {

    @NotBlank(message = "Il campo username non può essere vuoto")
    @Valid
    private String username;

    @NotBlank(message = "Il campo nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il campo cognome non può essere vuoto")
    private String cognome;

    @Email(message = "Inserire un indirizzo email valido")
    private String email;


}
