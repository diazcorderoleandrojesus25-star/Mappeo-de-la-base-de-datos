package com.Jobxpress.Jobxpress.Service;

import com.Jobxpress.Jobxpress.Entity.Calificacion;
import java.util.List;

public interface CalificacionService {
    List<Calificacion> findAll();
    Calificacion findById(Integer id);
    Calificacion save(Calificacion calificacion);
    Calificacion update(Integer id, Calificacion calificacion);
    void delete(Integer id);
}
