package com.Jobxpress.Jobxpress.Service;

import com.Jobxpress.Jobxpress.Entity.Servicio;
import java.util.List;

public interface ServicioService {
    List<Servicio> findAll();
    Servicio findById(Integer id);
    Servicio save(Servicio servicio);
    Servicio update(Integer id, Servicio servicio);
    void delete(Integer id);
}
