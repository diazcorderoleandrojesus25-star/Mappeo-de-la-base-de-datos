package com.Jobxpress.Jobxpress.Repository;

import com.Jobxpress.Jobxpress.Entity.ClienteCalificacion;
import com.Jobxpress.Jobxpress.Entity.pk.ClienteCalificacionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteCalificacionRepository extends JpaRepository<ClienteCalificacion, ClienteCalificacionPK> {
}
