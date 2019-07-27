package com.elopez.almacen.core.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "inventario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_inventario")
    private Long codigoInventario;

    @NotEmpty(message = "Debe agregar codigo producto")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_producto")
    private  Producto producto;

    @NotEmpty(message = "Debe agregar fecha")
    @Column(name = "fecha")
    private Date fecha;

    @NotEmpty(message = "Debe agregar tipo de registro")
    @Column(name = "tipo_registro")
    private  String tipoRegistro;

    @NotEmpty(message = "Debe agregar precio")
    @Column(name = "precio")
    private  Double precio;

    @NotEmpty(message = "Debe agregar cantidad de entrada")
    @Column(name = "entrada")
    private  Long entrada;

    @NotEmpty(message = "Debe agregar cantidad de salida")
    @Column(name = "salida")
    private  Long salida;
}
