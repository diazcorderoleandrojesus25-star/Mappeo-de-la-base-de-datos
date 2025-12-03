package com.Jobxpress.Jobxpress.Service;

import com.Jobxpress.Jobxpress.Entity.ClienteContratacion;
import com.Jobxpress.Jobxpress.Entity.pk.ClienteContratacionPK;
import java.util.List;

public interface ClienteContratacionService {
    List<ClienteContratacion> findAll();
    ClienteContratacion findById(ClienteContratacionPK id);
    ClienteContratacion save(ClienteContratacion cc);
    void delete(ClienteContratacionPK id);
}
