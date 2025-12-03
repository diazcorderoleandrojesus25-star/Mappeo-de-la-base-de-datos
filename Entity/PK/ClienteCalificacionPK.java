package com.Jobxpress.Jobxpress.Entity.pk;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ClienteCalificacionPK implements Serializable {

    private Integer idCliente;
    private Integer idCalificacion;
}
