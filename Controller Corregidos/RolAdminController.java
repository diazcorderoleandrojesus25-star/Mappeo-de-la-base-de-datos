package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Rol;
import com.Jobxpress.Jobxpress.Repository.RolRepository;

import java.io.IOException;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/roles")
public class RolAdminController {

    @Autowired
    private RolRepository rolRepository;

    // LISTA
    @GetMapping("/lista")
    public String listarRoles(Model model) {
        model.addAttribute("roles", rolRepository.findAll());
        return "roles/lista";
    }

    // FILTRO MULTICRITERIO
    @GetMapping("/lista/filtrar")
    public String filtrar(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nombre,
            Model model
    ) {

        List<Rol> resultados = rolRepository.findAll().stream()
                .filter(r -> id == null || r.getIdRol().equals(id))
                .filter(r -> nombre == null || nombre.isEmpty() ||
                        r.getRol().toLowerCase().contains(nombre.toLowerCase()))
                .toList();

        model.addAttribute("roles", resultados);

        // Para mantener los valores en los inputs
        model.addAttribute("filtroId", id);
        model.addAttribute("filtroNombre", nombre);

        return "roles/lista";
    }

    // FORM CREAR
    @GetMapping("/crear")
    public String crearFormulario(Model model) {
        model.addAttribute("rol", new Rol());
        return "roles/crear";
    }

    // GUARDAR NUEVO
    @PostMapping("/crear")
    public String guardarRol(@RequestParam String rol) {

        Rol nuevo = new Rol();
        nuevo.setRol(rol);

        rolRepository.save(nuevo);
        return "redirect:/admin/roles/lista";
    }

    // FORM EDITAR
    @GetMapping("/editar/{id}")
    public String editarFormulario(@PathVariable Integer id, Model model) {
        Rol rol = rolRepository.findById(id).orElse(null);

        if (rol == null)
            return "redirect:/admin/roles/lista?error=NoExiste";

        model.addAttribute("rol", rol);
        return "roles/editar";
    }

    // ACTUALIZAR
    @PostMapping("/editar/{id}")
    public String actualizarRol(
            @PathVariable Integer id,
            @RequestParam String rol
    ) {

        Rol existente = rolRepository.findById(id).orElse(null);
        if (existente == null)
            return "redirect:/admin/roles/lista?error=NoExiste";

        existente.setRol(rol);

        rolRepository.save(existente);

        return "redirect:/admin/roles/lista";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarRol(@PathVariable Integer id) {

        rolRepository.deleteById(id);

        return "redirect:/admin/roles/lista";
    }

    // EXPORTAR PDF
    @GetMapping("/exportar-pdf")
    public void exportarPDF(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nombre,
            HttpServletResponse response
    ) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=roles.pdf");

        List<Rol> roles = rolRepository.findAll().stream()
                .filter(r -> id == null || r.getIdRol().equals(id))
                .filter(r -> nombre == null || nombre.isEmpty() ||
                        r.getRol().toLowerCase().contains(nombre.toLowerCase()))
                .toList();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        // LOGO
        try {
            String logoPath = "src/main/resources/static/images/logo.jpg";
            com.lowagie.text.Image logo = com.lowagie.text.Image.getInstance(logoPath);
            logo.scaleAbsolute(180, 180);
            logo.setAlignment(com.lowagie.text.Image.ALIGN_CENTER);
            document.add(logo);
        } catch (Exception e) {
            document.add(new Paragraph("(No se encontró el logo)"));
        }

        // FECHA
        String fechaActual = java.time.LocalDate.now().toString();
        document.add(new Paragraph("Fecha: " + fechaActual));
        document.add(new Paragraph(" "));

        // TITULO
        Paragraph titulo = new Paragraph("Reporte de Roles");
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);
        document.add(new Paragraph(" "));

        // TABLA
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        // Encabezados con color #BBDEFB
        com.lowagie.text.pdf.PdfPCell h1 = new com.lowagie.text.pdf.PdfPCell(new Paragraph("ID"));
        com.lowagie.text.pdf.PdfPCell h2 = new com.lowagie.text.pdf.PdfPCell(new Paragraph("Rol"));

        h1.setBackgroundColor(new java.awt.Color(187, 222, 251)); // #BBDEFB
        h2.setBackgroundColor(new java.awt.Color(187, 222, 251));

        table.addCell(h1);
        table.addCell(h2);

        // Datos
        for (Rol r : roles) {
            table.addCell(String.valueOf(r.getIdRol()));
            table.addCell(r.getRol());
        }

        document.add(table);
        document.close();
    }
}
