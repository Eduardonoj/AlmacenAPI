package com.elopez.almacen.core.models.dao;

import com.elopez.almacen.core.models.entity.EmailCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmailClienteDao extends JpaRepository<EmailCliente, Long> {
}
