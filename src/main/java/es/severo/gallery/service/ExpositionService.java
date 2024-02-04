package es.severo.gallery.service;

import es.severo.gallery.entity.Exposition;
import es.severo.gallery.exception.util.ErrorCode;
import es.severo.gallery.exception.GalleryException;
import es.severo.gallery.repository.ExpositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpositionService {
    private final ExpositionRepository expositionRepository;

    public ExpositionService(ExpositionRepository expositionRepository){
        this.expositionRepository = expositionRepository;
    }

    public List<Exposition> findAll(){
        return expositionRepository.findAll();
    }
    public Exposition findById(Long id){
        return expositionRepository.findById(id).orElse(new Exposition());
    }
    public Exposition findByName(String name){
        return expositionRepository.findByName(name);
    }
    public Exposition save(Exposition exposition){
        return expositionRepository.save(exposition);
    }
    public void deleteById(Long id){
        expositionRepository.deleteById(id);
    }
    public void deleteByName(String name){
        expositionRepository.deleteByName(name);
    }
    public Exposition update(Exposition exposition) throws GalleryException{
        if (exposition.getId() != null){
            expositionRepository.save(exposition);
        }
        throw new GalleryException("The ID field is mandatory", ErrorCode.IdNotFound);
    }
    public List<Exposition> getAllExposByPiece(String pieceName){
        return expositionRepository.findByPieces_Name(pieceName);
    }
}