package com.Jobxpress.Jobxpress.Repository;

import com.Jobxpress.Jobxpress.Entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
}
