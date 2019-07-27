package com.elopez.almacen.core.models.dao;


import com.elopez.almacen.core.models.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ICategoriaDao extends JpaRepository<Categoria, Long> {
}
