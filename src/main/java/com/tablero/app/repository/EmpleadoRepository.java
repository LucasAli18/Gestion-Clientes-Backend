package com.tablero.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tablero.app.model.Cliente;

public interface EmpleadoRepository extends JpaRepository<Cliente, Long>{

}
