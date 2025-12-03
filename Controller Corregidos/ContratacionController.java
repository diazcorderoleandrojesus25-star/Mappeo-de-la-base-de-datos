package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Contratacion;
import com.Jobxpress.Jobxpress.Repository.ContratacionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contrataciones")
public class ContratacionController {

    private final ContratacionRepository contratacionRepository;

    public ContratacionController(ContratacionRepository contratacionRepository) {
        this.contratacionRepository = contratacionRepository;
    }

    @GetMapping
    public List<Contratacion> getAll() {
        return contratacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Contratacion getById(@PathVariable Integer id) {
        return contratacionRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Contratacion create(@RequestBody Contratacion contratacion) {
        return contratacionRepository.save(contratacion);
    }

    @PutMapping("/{id}")
    public Contratacion update(@PathVariable Integer id, @RequestBody Contratacion contratacion) {
        contratacion.setIdContratacion(id);
        return contratacionRepository.save(contratacion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        contratacionRepository.deleteById(id);
    }
}
