package com.Jobxpress.Jobxpress.Service.impl;

import com.Jobxpress.Jobxpress.Entity.ClienteContratacion;
import com.Jobxpress.Jobxpress.Entity.pk.ClienteContratacionPK;
import com.Jobxpress.Jobxpress.Repository.ClienteContratacionRepository;
import com.Jobxpress.Jobxpress.Service.ClienteContratacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteContratacionServiceImpl implements ClienteContratacionService {

    private final ClienteContratacionRepository repository;

    public ClienteContratacionServiceImpl(ClienteContratacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClienteContratacion> findAll() {
        return repository.findAll();
    }

    @Override
    public ClienteContratacion findById(ClienteContratacionPK id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ClienteContratacion save(ClienteContratacion cc) {
        return repository.save(cc);
    }

    @Override
    public void delete(ClienteContratacionPK id) {
        repository.deleteById(id);
    }
}
