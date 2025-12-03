package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Rol;
import com.Jobxpress.Jobxpress.Repository.RolRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin("*") 
public class RolController {

    private final RolRepository rolRepository;

    public RolController(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    // LISTAR ROLES
    @GetMapping
    public ResponseEntity<List<Rol>> listar() {
        return ResponseEntity.ok(rolRepository.findAll());
    }

    // BUSCAR ROLE POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Rol> buscar(@PathVariable Integer id) {
        return rolRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREAR NUEVO ROL
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Rol rol) {

        // Validar que no exista ya un rol igual
        if (rolRepository.findAll()
                .stream()
                .anyMatch(r -> r.getRol().equalsIgnoreCase(rol.getRol()))) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("⚠ El rol ya existe");
        }

        Rol nuevo = rolRepository.save(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // ACTUALIZAR ROL
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Rol rol) {

        if (!rolRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Validar duplicado al actualizar
        boolean existeOtro = rolRepository.findAll()
                .stream()
                .anyMatch(r -> !r.getIdRol().equals(id) &&
                               r.getRol().equalsIgnoreCase(rol.getRol()));

        if (existeOtro) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("⚠ Ya existe otro rol con ese nombre");
        }

        rol.setIdRol(id);
        return ResponseEntity.ok(rolRepository.save(rol));
    }

    // ELIMINAR ROL
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        if (!rolRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        rolRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
