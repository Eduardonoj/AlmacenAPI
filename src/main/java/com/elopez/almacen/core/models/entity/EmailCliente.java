package com.elopez.almacen.core.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "email_cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_email")
    private Long codigoEmail;

    @NotEmpty(message = "Debe agregar email")
    @Column(name = "email")
    private  String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nit")
    private  Cliente cliente;
}
