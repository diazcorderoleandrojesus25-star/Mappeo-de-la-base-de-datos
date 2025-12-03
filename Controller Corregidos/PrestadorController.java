package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Usuario;
import com.Jobxpress.Jobxpress.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prestador")
public class PrestadorController {

    @Autowired
    private UsuarioService usuarioService;

    // HOME
    @GetMapping("/home")
    public String homePrestador() {
        return "prestador/home";
    }

    // PERFIL DEL PRESTADOR
    @GetMapping("/perfil")
    public String perfilPrestador(Model model, Authentication auth) {

        String email = auth.getName();
        Usuario usuario = usuarioService.findByEmail(email);
        model.addAttribute("usuario", usuario);

        return "prestador/perfilprestador";
    }

    // CHAT
    @GetMapping("/chat")
    public String chatPrestador() {
        return "prestador/chat";
    }

    // DASHBOARD
    @GetMapping("/dashboard")
    public String dashboardPrestador(Model model, Authentication auth) {
        String email = auth.getName();
        Usuario usuario = usuarioService.findByEmail(email);
        model.addAttribute("usuario", usuario);

        return "prestador/dashboard";
    }

    // AGENDA
    @GetMapping("/agenda")
    public String agenda() {
        return "prestador/agenda";
    }

    // GANANCIAS
    @GetMapping("/ganancias")
    public String ganancias() {
        return "prestador/ganancias";
    }

    // SERVICIO PRESTADOR
    @GetMapping("/servicioPrestador")
    public String servicioPrestador() {
        return "prestador/servicioprestador";
    }

    // VALORACIONES
    @GetMapping("/valoraciones")
    public String valoraciones() {
        return "prestador/valoraciones";
    }
}
