package com.Jobxpress.Jobxpress.Service;

import com.Jobxpress.Jobxpress.Entity.MetodoPago;
import java.util.List;

public interface MetodoPagoService {
    List<MetodoPago> findAll();
    MetodoPago findById(Integer id);
    MetodoPago save(MetodoPago metodoPago);
    MetodoPago update(Integer id, MetodoPago metodoPago);
    void delete(Integer id);
    
}

