package com.Jobxpress.Jobxpress.Service.impl;

import com.Jobxpress.Jobxpress.Entity.Calificacion;
import com.Jobxpress.Jobxpress.Repository.CalificacionRepository;
import com.Jobxpress.Jobxpress.Service.CalificacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionServiceImpl implements CalificacionService {

    private final CalificacionRepository repository;

    public CalificacionServiceImpl(CalificacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Calificacion> findAll() {
        return repository.findAll();
    }

    @Override
    public Calificacion findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Calificacion save(Calificacion calificacion) {
        return repository.save(calificacion);
    }

    @Override
    public Calificacion update(Integer id, Calificacion calificacion) {
        Calificacion existing = repository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setComentario(calificacion.getComentario());
        existing.setPuntuacion(calificacion.getPuntuacion());
       

        return repository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
