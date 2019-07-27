package com.elopez.almacen.core.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "telefono_cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_telefono")
    private Long codigoTelefono;

    @NotEmpty(message = "Debe agregar numero de telefono")
    @Column(name = "numero")
    private  String numero;

    @Column(name = "descripcion")
    private  Double descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nit")
    private  Cliente cliente;
}
