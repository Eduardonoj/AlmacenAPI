package com.elopez.almacen.core.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name = "rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "nombre")
    @NotEmpty(message ="debe agregar rol")
    private String nombre;
    @ManyToMany(mappedBy ="roles")
    private List<Usuario> usuarios;

}
