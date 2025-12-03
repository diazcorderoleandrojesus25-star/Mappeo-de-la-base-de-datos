package com.Jobxpress.Jobxpress.Service;

import com.Jobxpress.Jobxpress.Entity.Contratacion;
import java.util.List;

public interface ContratacionService {
    List<Contratacion> findAll();
    Contratacion findById(Integer id);
    Contratacion save(Contratacion contratacion);
    Contratacion update(Integer id, Contratacion contratacion);
    void delete(Integer id);
}
