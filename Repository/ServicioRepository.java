package com.Jobxpress.Jobxpress.Repository;

import com.Jobxpress.Jobxpress.Entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}
