package com.Jobxpress.Jobxpress.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // HOME DEL ADMIN
    @GetMapping("/dashboard")
    public String dashboardAdmin() {
        return "admin/dashboard";
    }

    // REPORTES (opcional)
    @GetMapping("/reportes")
    public String reportesAdmin() {
        return "admin/reportes";
    }

    // CONFIGURACIÓN (opcional)
    @GetMapping("/config")
    public String configAdmin() {
        return "admin/config";
    }

    // REDIRECCIÓN PRINCIPAL DEL ADMIN (/admin → /admin/dashboard)
    @GetMapping("/")
    public String indexAdmin() {
        return "redirect:/admin/dashboard";
    }
}
