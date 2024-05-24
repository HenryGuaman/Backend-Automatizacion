package com.company.automatizacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.automatizacion.dao.IncidenciaRepository;
import com.company.automatizacion.model.Incidencia;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository repository;
    
    public Incidencia crearIncidencia(Incidencia incidencia) {
        incidencia.setEstado("Pendiente");
        incidencia.setFechaCreacion(LocalDateTime.now());
        return repository.save(incidencia);
    }
    
    public List<Incidencia> obtenerIncidenciasPorFecha(LocalDateTime start, LocalDateTime end) {
        return repository.findAllByFechaCreacionBetween(start, end);
    }
    
    public List<Incidencia> obtenerIncidenciasPendientes() {
        return repository.findAllByEstado("Pendiente");
    }
    
    public List<Incidencia> obtenerIncidenciasActivas() {
        return repository.findAllByEstado("Atendida");
    }
    
    public Incidencia actualizarIncidencia(Long id, String observacion) {
        Incidencia incidencia = repository.findById(id).orElseThrow();
        incidencia.setEstado("Atendida");
        incidencia.setObservacion(observacion);
        incidencia.setFechaAtencion(LocalDateTime.now());
        return repository.save(incidencia);
    }
    
    public Incidencia obtenerIncidenciaPorId(Long id) {
        Optional<Incidencia> optionalIncidencia = repository.findById(id);
        return optionalIncidencia.orElse(null);
    }
    
    public List<Incidencia> getAllIncidencias() {
        return repository.findAll();
    }
}

