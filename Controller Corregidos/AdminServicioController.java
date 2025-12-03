package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Servicio;
import com.Jobxpress.Jobxpress.Entity.Categoria;
import com.Jobxpress.Jobxpress.Repository.ServicioRepository;
import com.Jobxpress.Jobxpress.Repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;

@Controller
@RequestMapping("/admin/servicios")
public class AdminServicioController {

    @Autowired
    private ServicioRepository servicioRepo;

    @Autowired
    private CategoriaRepository categoriaRepo;

    // LISTA + FILTRO MULTICRITERIO
    @GetMapping("/lista")
    public String lista(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer idCategoria,
            Model model
    ) {
        var resultados = servicioRepo.findAll().stream()
                .filter(s -> id == null || s.getIdServicio().equals(id))
                .filter(s -> nombre == null || nombre.isEmpty() || s.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(s -> idCategoria == null || (s.getCategoria() != null && s.getCategoria().getIdCategoria().equals(idCategoria)))
                .toList();

        model.addAttribute("servicios", resultados);
        model.addAttribute("categorias", categoriaRepo.findAll());
        model.addAttribute("filtroId", id);
        model.addAttribute("filtroNombre", nombre);
        model.addAttribute("filtroIdCategoria", idCategoria);

        return "servicios/lista";
    }

    // EXPORTAR PDF
    @GetMapping("/exportar-pdf")
    public void exportarPDF(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer idCategoria,
            HttpServletResponse response
    ) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=servicios.pdf");

        List<Servicio> servicios = servicioRepo.findAll().stream()
                .filter(s -> id == null || s.getIdServicio().equals(id))
                .filter(s -> nombre == null || nombre.isEmpty() || s.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(s -> idCategoria == null || (s.getCategoria() != null && s.getCategoria().getIdCategoria().equals(idCategoria)))
                .toList();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // -------------------------
        //     LOGO CENTRADO
        // -------------------------
        try {
            InputStream is = getClass().getResourceAsStream("/static/images/logo.jpg");
            if (is != null) {
                Image logo = Image.getInstance(is.readAllBytes());
                logo.scaleAbsolute(180, 180); // MÁS GRANDE
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
        Paragraph titulo = new Paragraph("Reporte de Servicios");
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);
        document.add(new Paragraph(" "));

        // -------------------------
        // TABLA CON ESTILO
        // -------------------------
        PdfPTable table = new PdfPTable(3); // ID, Nombre, Categoría
        table.setWidthPercentage(100);

        java.awt.Color headerColor = new java.awt.Color(187, 222, 251); // #BBDEFB

        // Encabezados estilizados
        var h1 = new com.lowagie.text.pdf.PdfPCell(new Paragraph("ID"));
        var h2 = new com.lowagie.text.pdf.PdfPCell(new Paragraph("Nombre"));
        var h3 = new com.lowagie.text.pdf.PdfPCell(new Paragraph("Categoría"));

        h1.setBackgroundColor(headerColor);
        h2.setBackgroundColor(headerColor);
        h3.setBackgroundColor(headerColor);

        table.addCell(h1);
        table.addCell(h2);
        table.addCell(h3);

        // Datos
        for (Servicio s : servicios) {
            table.addCell(String.valueOf(s.getIdServicio()));
            table.addCell(s.getNombre());
            table.addCell(s.getCategoria() != null ? s.getCategoria().getNombre() : "—");
        }

        document.add(table);
        document.close();
    }

    // CREAR FORM
    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("categorias", categoriaRepo.findAll());
        return "servicios/crear";
    }

    // CREAR POST
    @PostMapping("/crear")
    public String crear(
            @ModelAttribute Servicio servicio,
            @RequestParam("idCategoria") Integer idCategoria
    ) {
        Categoria categoria = categoriaRepo.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        servicio.setCategoria(categoria);
        servicioRepo.save(servicio);

        return "redirect:/admin/servicios/lista";
    }

    // EDITAR FORM
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Servicio servicio = servicioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        model.addAttribute("servicio", servicio);
        model.addAttribute("categorias", categoriaRepo.findAll());

        return "servicios/editar";
    }

    // ACTUALIZAR
    @PostMapping("/editar/{id}")
    public String actualizar(
            @PathVariable Integer id,
            @ModelAttribute Servicio servicio,
            @RequestParam("idCategoria") Integer idCategoria
    ) {
        Categoria categoria = categoriaRepo.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        servicio.setIdServicio(id);
        servicio.setCategoria(categoria);
        servicioRepo.save(servicio);

        return "redirect:/admin/servicios/lista";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        servicioRepo.deleteById(id);
        return "redirect:/admin/servicios/lista";
    }
}
