package com.Jobxpress.Jobxpress.Service.impl;

import com.Jobxpress.Jobxpress.Entity.Contratacion;
import com.Jobxpress.Jobxpress.Repository.ContratacionRepository;
import com.Jobxpress.Jobxpress.Service.ContratacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratacionServiceImpl implements ContratacionService {

    private final ContratacionRepository contratacionRepository;

    public ContratacionServiceImpl(ContratacionRepository contratacionRepository) {
        this.contratacionRepository = contratacionRepository;
    }

    @Override
    public List<Contratacion> findAll() {
        return contratacionRepository.findAll();
    }

    @Override
    public Contratacion findById(Integer id) {
        return contratacionRepository.findById(id).orElse(null);
    }

    @Override
    public Contratacion save(Contratacion contratacion) {
        return contratacionRepository.save(contratacion);
    }

    @Override
    public Contratacion update(Integer id, Contratacion contratacion) {
        Contratacion existing = contratacionRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setFecha(contratacion.getFecha());
        existing.setEstado(contratacion.getEstado());
        existing.setServicio(contratacion.getServicio());
      

        return contratacionRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        contratacionRepository.deleteById(id);
    }
}
