package com.company.automatizacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.automatizacion.model.Incidencia;
import com.company.automatizacion.service.IncidenciaService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {

    @Autowired
    private IncidenciaService service;

    @PostMapping("/crear")
    public Incidencia crearIncidencia(@RequestBody Incidencia incidencia) {
        return service.crearIncidencia(incidencia);
    }

    @GetMapping
    public List<Incidencia> obtenerIncidenciasPorFecha(
            @RequestParam String start, 
            @RequestParam String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
        return service.obtenerIncidenciasPorFecha(startDateTime, endDateTime);
    }

    @GetMapping("/pendientes")
    public List<Incidencia> obtenerIncidenciasPendientes() {
        return service.obtenerIncidenciasPendientes();
    }
    
    @GetMapping("/activadas")
    public List<Incidencia> obtenerIncidenciasActivas() {
        return service.obtenerIncidenciasActivas();
    }

//    @PutMapping("/{id}")
//    public Incidencia actualizarIncidencia(
//            @PathVariable Long id, 
//            @RequestParam String observacion) {
//        return service.actualizarIncidencia(id, observacion);
//    }
    
    @PutMapping("/{id}")
    public Incidencia actualizarIncidencia(
            @PathVariable Long id, 
            @RequestBody Map<String, String> request) {
        String observacion = request.get("observacion");
        return service.actualizarIncidencia(id, observacion);
    }
    
    @GetMapping("/listar")
    public List<Incidencia> getAllIncidencias() {
        return service.getAllIncidencias();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> obtenerIncidenciaPorId(@PathVariable Long id) {
        Incidencia incidencia = service.obtenerIncidenciaPorId(id);
        if (incidencia != null) {
            return ResponseEntity.ok().body(incidencia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

