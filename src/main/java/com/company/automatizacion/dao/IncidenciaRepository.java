package com.company.automatizacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.automatizacion.model.Incidencia;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    
    List<Incidencia> findAllByFechaCreacionBetween(LocalDateTime start, LocalDateTime end);
    
    List<Incidencia> findAllByEstado(String estado);
}