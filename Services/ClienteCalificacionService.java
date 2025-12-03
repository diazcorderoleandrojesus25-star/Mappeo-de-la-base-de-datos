package com.Jobxpress.Jobxpress.Service;

import com.Jobxpress.Jobxpress.Entity.ClienteCalificacion;
import com.Jobxpress.Jobxpress.Entity.pk.ClienteCalificacionPK;
import java.util.List;

public interface ClienteCalificacionService {
    List<ClienteCalificacion> findAll();
    ClienteCalificacion findById(ClienteCalificacionPK id);
    ClienteCalificacion save(ClienteCalificacion cc);
    void delete(ClienteCalificacionPK id);
}
