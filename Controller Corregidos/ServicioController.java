package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Servicio;
import com.Jobxpress.Jobxpress.Repository.ServicioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioRepository servicioRepository;

    public ServicioController(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @GetMapping
    public List<Servicio> listar() {
        return servicioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Servicio obtener(@PathVariable Integer id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Servicio crear(@RequestBody Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @PutMapping("/{id}")
    public Servicio actualizar(@PathVariable Integer id, @RequestBody Servicio servicio) {
        servicio.setIdServicio(id);
        return servicioRepository.save(servicio);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        servicioRepository.deleteById(id);
    }
}
