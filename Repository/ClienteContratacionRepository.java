package com.Jobxpress.Jobxpress.Repository;

import com.Jobxpress.Jobxpress.Entity.ClienteContratacion;
import com.Jobxpress.Jobxpress.Entity.pk.ClienteContratacionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteContratacionRepository extends JpaRepository<ClienteContratacion, ClienteContratacionPK> {
}
