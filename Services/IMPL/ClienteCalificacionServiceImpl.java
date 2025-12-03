package com.Jobxpress.Jobxpress.Service.impl;

import com.Jobxpress.Jobxpress.Entity.ClienteCalificacion;
import com.Jobxpress.Jobxpress.Entity.pk.ClienteCalificacionPK;
import com.Jobxpress.Jobxpress.Repository.ClienteCalificacionRepository;
import com.Jobxpress.Jobxpress.Service.ClienteCalificacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteCalificacionServiceImpl implements ClienteCalificacionService {

    private final ClienteCalificacionRepository repository;

    public ClienteCalificacionServiceImpl(ClienteCalificacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClienteCalificacion> findAll() {
        return repository.findAll();
    }

    @Override
    public ClienteCalificacion findById(ClienteCalificacionPK id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ClienteCalificacion save(ClienteCalificacion cc) {
        return repository.save(cc);
    }

    @Override
    public void delete(ClienteCalificacionPK id) {
        repository.deleteById(id);
    }
}
