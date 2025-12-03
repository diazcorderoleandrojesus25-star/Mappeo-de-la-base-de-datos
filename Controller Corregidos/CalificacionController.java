package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Calificacion;
import com.Jobxpress.Jobxpress.Repository.CalificacionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {

    private final CalificacionRepository calificacionRepository;

    public CalificacionController(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }

    @GetMapping
    public List<Calificacion> getAll() {
        return calificacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Calificacion getById(@PathVariable Integer id) {
        return calificacionRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Calificacion create(@RequestBody Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @PutMapping("/{id}")
    public Calificacion update(@PathVariable Integer id, @RequestBody Calificacion calificacion) {
        calificacion.setIdCalificacion(id);
        return calificacionRepository.save(calificacion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        calificacionRepository.deleteById(id);
    }
}
