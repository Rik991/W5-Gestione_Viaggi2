package it.epicode.W5_Gestione_Viaggi1.dipendente;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.W5_Gestione_Viaggi1.cloudinary.CloudinaryService;
import it.epicode.W5_Gestione_Viaggi1.exceptions.UploadException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Validated
@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepo dipendenteRepo;

    @Autowired
    private CloudinaryService cloudinaryService;


    public Dipendente uploadAvatar(Long dipendenteId, MultipartFile file) {
        Dipendente dipendente = findById(dipendenteId);
            Map uploadResult = cloudinaryService.uploader(file, "dipendenti");
            dipendente.setAvatar(uploadResult.get("url").toString());
            return dipendenteRepo.save(dipendente);
    }


    public Dipendente createDipendente(@Valid Dipendente dipendente) {
      return   dipendenteRepo.save(dipendente);
    }


    public Dipendente createDipendenteWithAvatar(@Valid Dipendente dipendente, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            Map result = cloudinaryService.uploader(file, "dipendenti");
            dipendente.setAvatar(result.get("url").toString());
        }
        return dipendenteRepo.save(dipendente);
    }


    public List<Dipendente> findAll() {
        return dipendenteRepo.findAll();
    }

    public Dipendente findById(Long id) {
        if (!dipendenteRepo.existsById(id)) {
            throw new EntityNotFoundException("Dipendente non trovato!");
        }

        return dipendenteRepo.findById(id).get();
    }


    public Dipendente updateDipendente(Long id,@Valid Dipendente modifiedDipendente) {
        Dipendente dipendente = findById(id);

        BeanUtils.copyProperties(modifiedDipendente,dipendente);

        return dipendenteRepo.save(dipendente);
    }

    public Dipendente updateDipendenteWithAvatar(Long id, @Valid Dipendente dipendenteModificato,MultipartFile file) {
        Dipendente dipendente = findById(id);
        BeanUtils.copyProperties(dipendenteModificato, dipendente,"id");
        if (file != null && !file.isEmpty()) {
            Map result = cloudinaryService.uploader(file, "dipendenti");
            dipendente.setAvatar(result.get("url").toString());
        }
        return dipendenteRepo.save(dipendenteModificato);
    }

    public void deleteDipendente(Long id) {
        dipendenteRepo.deleteById(id);
    }


}
