package com.Jobxpress.Jobxpress.Repository;

import com.Jobxpress.Jobxpress.Entity.Contratacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratacionRepository extends JpaRepository<Contratacion, Integer> {
}
