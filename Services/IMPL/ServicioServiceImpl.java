package com.Jobxpress.Jobxpress.Service.impl;

import com.Jobxpress.Jobxpress.Entity.Servicio;
import com.Jobxpress.Jobxpress.Repository.ServicioRepository;
import com.Jobxpress.Jobxpress.Service.ServicioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio findById(Integer id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio update(Integer id, Servicio servicio) {
        Servicio existing = servicioRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setNombre(servicio.getNombre());
        existing.setDescripcion(servicio.getDescripcion());
        existing.setPrecio(servicio.getPrecio());
        existing.setCategoria(servicio.getCategoria());

        return servicioRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        servicioRepository.deleteById(id);
    }
}
