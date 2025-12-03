package com.Jobxpress.Jobxpress.Repository;

import com.Jobxpress.Jobxpress.Entity.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
}
