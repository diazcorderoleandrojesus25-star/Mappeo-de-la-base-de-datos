package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.MetodoPago;
import com.Jobxpress.Jobxpress.Service.MetodoPagoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metodos_pago")
@CrossOrigin("*")
public class MetodoPagoApiController {

    private final MetodoPagoService metodoPagoService;

    public MetodoPagoApiController(MetodoPagoService metodoPagoService) {
        this.metodoPagoService = metodoPagoService;
    }

    @GetMapping
    public List<MetodoPago> listar() {
        return metodoPagoService.findAll();
    }

    @GetMapping("/{id}")
    public MetodoPago obtener(@PathVariable Integer id) {
        return metodoPagoService.findById(id);
    }

    @PostMapping
    public MetodoPago crear(@RequestBody MetodoPago metodoPago) {
        return metodoPagoService.save(metodoPago);
    }

    @PutMapping("/{id}")
    public MetodoPago actualizar(@PathVariable Integer id, @RequestBody MetodoPago metodoPago) {
        return metodoPagoService.update(id, metodoPago);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        metodoPagoService.delete(id);
    }
}
