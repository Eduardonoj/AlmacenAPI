package com.elopez.almacen.core.models.dao;

import com.elopez.almacen.core.models.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacturaDao extends JpaRepository<Factura, Long> {
}
