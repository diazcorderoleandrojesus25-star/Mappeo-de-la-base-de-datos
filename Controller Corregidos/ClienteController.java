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
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;

    // HOME
    @GetMapping("/home")
    public String homeCliente() {
        return "cliente/home";
    }

    // PERFIL DEL CLIENTE
    @GetMapping("/perfil")
    public String perfilCliente(Model model, Authentication auth) {

        // Obtiene el email del usuario autenticado
        String email = auth.getName();

        // Busca el usuario mediante el servicio
        Usuario usuario = usuarioService.findByEmail(email);

        // Enviar al modelo
        model.addAttribute("usuario", usuario);

        return "cliente/perfil";
    }

    // CHAT
    @GetMapping("/chat")
    public String chatCliente() {
        return "cliente/chatcliente";
    }

    // DASHBOARD
    @GetMapping("/dashboard")
    public String dashboardCliente() {
        return "cliente/dashboard";
    }

    // SERVICIOS
    @GetMapping("/plomeria")
    public String plomeria() {
        return "cliente/plomeria";
    }

    @GetMapping("/electricidad")
    public String electricidad() {
        return "cliente/electricidad";
    }

    @GetMapping("/carpinteria")
    public String carpinteria() {
        return "cliente/carpinteria";
    }

    @GetMapping("/limpiezagen")
    public String limpiezaGeneral() {
        return "cliente/limpiezagen";
    }

    @GetMapping("/soportetec")
    public String soporteTecnico() {
        return "cliente/soportetec";
    }

    @GetMapping("/instalacionnequi")
    public String instalacionEquipos() {
        return "cliente/instalacionnequi";
    }

    @GetMapping("/fisioterapia")
    public String fisioterapia() {
        return "cliente/fisioterapia";
    }

    @GetMapping("/salud")
    public String salud() {
        return "cliente/salud";
    }

    @GetMapping("/asesorias")
    public String asesorias() {
        return "cliente/asesorias";
    }

    @GetMapping("/cuidaranimales")
    public String cuidadoresAnimales() {
        return "cliente/cuidaranimales";
    }

    @GetMapping("/redaccion")
    public String redaccionContenido() {
        return "cliente/redaccion";
    }

    @GetMapping("/marketing")
    public String marketingDigital() {
        return "cliente/marketing";
    }
}
