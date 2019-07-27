package com.elopez.almacen.core.models.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Table(name = "categoria")
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class TipoEmpaque implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_empaque")
    private Long codigoEmpaque;
    //@NotEmpty(message = "La descripción no puede ser vacia")
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "tipoEmpaque",fetch = FetchType.LAZY)
    private List<Producto> productos;


}
