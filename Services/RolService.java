package com.Jobxpress.Jobxpress.Service;

import com.Jobxpress.Jobxpress.Entity.Rol;

import java.util.List;

public interface RolService {

    List<Rol> findAll();

    Rol findById(Integer id);

    Rol save(Rol rol);

    Rol update(Integer id, Rol rol);

    void delete(Integer id);
}
