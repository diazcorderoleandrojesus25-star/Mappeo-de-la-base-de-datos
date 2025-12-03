package com.Jobxpress.Jobxpress.Service.impl;

import com.Jobxpress.Jobxpress.Entity.Rol;
import com.Jobxpress.Jobxpress.Repository.RolRepository;
import com.Jobxpress.Jobxpress.Service.RolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol findById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Rol update(Integer id, Rol rol) {
        Rol existing = rolRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setRol(rol.getRol());
        return rolRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        rolRepository.deleteById(id);
    }
}
