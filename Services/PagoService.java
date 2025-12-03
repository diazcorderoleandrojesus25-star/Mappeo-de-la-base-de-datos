package com.Jobxpress.Jobxpress.Service;

import com.Jobxpress.Jobxpress.Entity.Pago;
import java.util.List;

public interface PagoService {
    List<Pago> findAll();
    Pago findById(Integer id);
    Pago save(Pago pago);
    Pago update(Integer id, Pago pago);
    void delete(Integer id);
}
