package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Pago;
import com.Jobxpress.Jobxpress.Repository.PagoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoRepository pagoRepository;

    public PagoController(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @GetMapping
    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pago obtener(@PathVariable Integer id) {
        return pagoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Pago crear(@RequestBody Pago pago) {
        return pagoRepository.save(pago);
    }

    @PutMapping("/{id}")
    public Pago actualizar(@PathVariable Integer id, @RequestBody Pago pago) {
        pago.setIdPago(id);
        return pagoRepository.save(pago);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        pagoRepository.deleteById(id);
    }
}
