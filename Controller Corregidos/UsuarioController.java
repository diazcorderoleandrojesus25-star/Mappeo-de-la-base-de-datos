package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Usuario;
import com.Jobxpress.Jobxpress.Repository.RolRepository;
import com.Jobxpress.Jobxpress.Service.UsuarioService;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepository;

    // LISTAR + FILTRO MULTICRITERIO
    @GetMapping("/lista")
    public String listarUsuarios(
            @RequestParam(required = false) Integer idUsuario,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer idRol,
            Model model
    ) {

        List<Usuario> resultados = usuarioService.findAll().stream()
                .filter(u -> idUsuario == null || u.getIdUsuario().equals(idUsuario))
                .filter(u -> nombre == null || nombre.isEmpty() || u.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(u -> idRol == null || (u.getRol() != null && u.getRol().getIdRol().equals(idRol)))
                .toList();

        model.addAttribute("usuarios", resultados);
        model.addAttribute("roles", rolRepository.findAll());

        model.addAttribute("filtroId", idUsuario);
        model.addAttribute("filtroNombre", nombre);
        model.addAttribute("filtroRol", idRol);

        return "usuario/lista";
    }

    // EXPORTAR PDF (CON LOGO, FECHA, COLORES)
    @GetMapping("/exportar-pdf")
    public void exportarPDF(
            @RequestParam(required = false) Integer idUsuario,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer idRol,
            HttpServletResponse response
    ) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=usuarios.pdf");

        List<Usuario> usuarios = usuarioService.findAll().stream()
                .filter(u -> idUsuario == null || u.getIdUsuario().equals(idUsuario))
                .filter(u -> nombre == null || nombre.isEmpty() || u.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(u -> idRol == null || (u.getRol() != null && u.getRol().getIdRol().equals(idRol)))
                .toList();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // -------------------------
        // LOGO CENTRADO
        // -------------------------
        try {
            InputStream is = getClass().getResourceAsStream("/static/images/logo.jpg");
            if (is != null) {
                Image logo = Image.getInstance(is.readAllBytes());
                logo.scaleAbsolute(150, 150); // tamaño más grande
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);
            } else {
                document.add(new Paragraph("(Logo no encontrado)"));
            }
        } catch (Exception e) {
            document.add(new Paragraph("(Error cargando logo)"));
        }

        // FECHA
        document.add(new Paragraph("Fecha: " + java.time.LocalDate.now()));
        document.add(new Paragraph(" "));

        // TÍTULO
        Paragraph titulo = new Paragraph("Reporte de Usuarios");
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);
        document.add(new Paragraph(" "));

        // -------------------------
        // TABLA ESTILIZADA
        // -------------------------
        PdfPTable table = new PdfPTable(4); // ID, Nombre, Email, Rol
        table.setWidthPercentage(100);

        java.awt.Color headerColor = new java.awt.Color(187, 222, 251); // #BBDEFB

        var h1 = new com.lowagie.text.pdf.PdfPCell(new Paragraph("ID"));
        var h2 = new com.lowagie.text.pdf.PdfPCell(new Paragraph("Nombre"));
        var h3 = new com.lowagie.text.pdf.PdfPCell(new Paragraph("Email"));
        var h4 = new com.lowagie.text.pdf.PdfPCell(new Paragraph("Rol"));

        h1.setBackgroundColor(headerColor);
        h2.setBackgroundColor(headerColor);
        h3.setBackgroundColor(headerColor);
        h4.setBackgroundColor(headerColor);

        table.addCell(h1);
        table.addCell(h2);
        table.addCell(h3);
        table.addCell(h4);

        // FILAS
        for (Usuario u : usuarios) {
            table.addCell(String.valueOf(u.getIdUsuario()));
            table.addCell(u.getNombre() + " " + u.getApellido());
            table.addCell(u.getEmail());
            table.addCell(u.getRol() != null ? u.getRol().getRol() : "—");
        }

        document.add(table);
        document.close();
    }

    // CREAR FORMULARIO
    @GetMapping("/crear")
    public String crearFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolRepository.findAll());
        return "usuario/crear";
    }

    // GUARDAR NUEVO
    @PostMapping("/crear")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/admin/usuarios/lista";
    }

    // EDITAR FORMULARIO
    @GetMapping("/editar/{id}")
    public String editarFormulario(@PathVariable Integer id, Model model) {

        Usuario usuario = usuarioService.findById(id);

        if (usuario == null) {
            return "redirect:/admin/usuarios/lista?error=NoExiste";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolRepository.findAll());

        return "usuario/editar";
    }

    // ACTUALIZAR
    @PostMapping("/editar/{id}")
    public String actualizarUsuario(@PathVariable Integer id, @ModelAttribute Usuario usuario) {
        usuario.setIdUsuario(id);
        usuarioService.save(usuario);
        return "redirect:/admin/usuarios/lista";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        usuarioService.delete(id);
        return "redirect:/admin/usuarios/lista";
    }
}
