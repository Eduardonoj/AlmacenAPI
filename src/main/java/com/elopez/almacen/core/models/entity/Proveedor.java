package com.elopez.almacen.core.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_proveedor")
    private Long codigoProveedor;

    @NotEmpty(message = "Debe agregar un nit")
    @Column(name = "nit")
    private  String nit;

    @NotEmpty(message = "Debe ingresar una razon social")
    @Column(name = "razon_social")
    private  String razonSocial;

    @NotEmpty(message = "Debe ingresar una direccion")
    @Column(name = "direccion")
    private  String direccion;

    @NotEmpty(message = "Debe ingresar una pagina web")
    @Column(name = "pagina_web")
    private  String paginaWeb;

    @NotEmpty(message = "Debe ingresar un contacto principal")
    @Column(name = "contacto_principal")
    private  String contactoPrincipal;

    @JsonIgnore
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<TelefonoProveedor> telefonoProveedores;

    @JsonIgnore
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<EmailProveedor> emailProveedores;

    @JsonIgnore
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<Compra> compraProveedores;
}