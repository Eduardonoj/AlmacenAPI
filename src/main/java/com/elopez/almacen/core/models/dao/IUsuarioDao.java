package com.elopez.almacen.core.models.dao;

import com.elopez.almacen.core.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
   // public Usuario findByUsername(String username);

    public Usuario findByUsername(String username);

    @Query("from Usuario u where u.username=?1")
    public Usuario findByUsername2 (String username);


}
