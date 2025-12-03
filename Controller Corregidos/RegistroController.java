package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Rol;
import com.Jobxpress.Jobxpress.Entity.Usuario;
import com.Jobxpress.Jobxpress.Service.UsuarioService;
import com.Jobxpress.Jobxpress.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam String direccion,
            @RequestParam String contrasena,
            @RequestParam("contrasena_confirmation") String confirmar,
            @RequestParam("id_rol") Integer idRol,
            Model model
    ) {

        // ====== Validaciones Backend ======

        // CONTRASEÑA COINCIDE
        if (!contrasena.equals(confirmar)) {
            model.addAttribute("error", "Las contraseñas no coinciden.");
        }

        // CONTRASEÑA SEGURA
        if (!contrasena.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$")) {
            model.addAttribute("error",
                    "La contraseña debe tener 1 mayúscula, 1 número y 1 símbolo.");
        }

        // CORREO REPETIDO
        if (usuarioService.findByEmail(email) != null) {
            model.addAttribute("error", "El correo ya está registrado.");
        }

        // Si hay error
        if (model.getAttribute("error") != null) {
            // Mantener datos en el formulario
            model.addAttribute("nombre", nombre);
            model.addAttribute("apellido", apellido);
            model.addAttribute("email", email);
            model.addAttribute("telefono", telefono);
            model.addAttribute("direccion", direccion);

            return "registro";
        }

        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setDireccion(direccion);
        usuario.setContrasena(passwordEncoder.encode(contrasena));

        // Rol
        Rol rol = rolRepo.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.setRol(rol);

        usuarioService.save(usuario);

        return "redirect:/login?registro";
    }
}
