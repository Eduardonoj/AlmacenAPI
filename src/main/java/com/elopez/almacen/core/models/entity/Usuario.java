package com.elopez.almacen.core.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")

public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column (name = "enable")
    private boolean enabled;
    @Column(name = "password")
    @NotEmpty(message ="Debe ingresar una contrase" )
    private String password;
    @Column(name = "username")
    @NotEmpty(message ="Debe ingresar un usuario" )
    private String username;
    @Column(name = "email")
    @NotEmpty(message ="Debe ingresar un email" )
    private String email;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name="role_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
    private List<Rol> roles;


}
