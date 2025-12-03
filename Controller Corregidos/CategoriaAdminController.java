package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Categoria;
import com.Jobxpress.Jobxpress.Repository.CategoriaRepository;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin/categorias")
public class CategoriaAdminController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // LISTA
    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "categorias/lista";
    }

    // FILTRO MULTICRITERIO
    @GetMapping("/lista/filtrar")
    public String filtrar(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nombre,
            Model model
    ) {
        List<Categoria> resultados = categoriaRepository.findAll().stream()
                .filter(c -> id == null || c.getIdCategoria().equals(id))
                .filter(c -> nombre == null || nombre.isEmpty() ||
                        c.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();

        model.addAttribute("categorias", resultados);
        model.addAttribute("filtroId", id);
        model.addAttribute("filtroNombre", nombre);

        return "categorias/lista";
    }

    // FORM CREAR
    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/crear";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Categoria categoria) {
        categoriaRepository.save(categoria);
        return "redirect:/admin/categorias/lista";
    }

    // FORM EDITAR
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria == null) return "redirect:/admin/categorias/lista";
        model.addAttribute("categoria", categoria);
        return "categorias/editar";
    }

    // ACTUALIZAR
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, @ModelAttribute Categoria categoria) {
        categoria.setIdCategoria(id);
        categoriaRepository.save(categoria);
        return "redirect:/admin/categorias/lista";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        categoriaRepository.deleteById(id);
        return "redirect:/admin/categorias/lista";
    }

    // DETALLE
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria == null) return "redirect:/admin/categorias/lista";
        model.addAttribute("categoria", categoria);
        return "categorias/detalle";
    }

    // EXPORTAR PDF
    @GetMapping("/exportar-pdf")
    public void exportarPDF(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nombre,
            HttpServletResponse response
    ) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=categorias.pdf");

        List<Categoria> categorias = categoriaRepository.findAll().stream()
                .filter(c -> id == null || c.getIdCategoria().equals(id))
                .filter(c -> nombre == null || nombre.isEmpty() || c.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // ========== LOGO CENTRADO Y MÁS GRANDE ==========
        try {
            URL logoURL = getClass().getResource("/static/images/logo.jpg");

            if (logoURL != null) {
                Image logo = Image.getInstance(logoURL);
                logo.scaleToFit(180, 180); // Más grande
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);
            }

        } catch (Exception e) {
            System.out.println("⚠ Error cargando logo: " + e.getMessage());
        }

        // ========== FECHA ==========
        String fecha = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        );
        Paragraph fechaTexto = new Paragraph("Fecha de generación: " + fecha);
        fechaTexto.setAlignment(Element.ALIGN_CENTER);
        fechaTexto.setSpacingAfter(10);
        document.add(fechaTexto);

        // ========== TÍTULO ==========
        Paragraph titulo = new Paragraph("Reporte de Categorías", new Font(Font.HELVETICA, 18, Font.BOLD));
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);

        // ========== TABLA ==========
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        // Encabezado con color #BBDEFB
        Color azulClarito = new Color(0xBB, 0xDE, 0xFB);

        PdfPCell h1 = new PdfPCell(new Phrase("ID"));
        h1.setBackgroundColor(azulClarito);
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPadding(8);

        PdfPCell h2 = new PdfPCell(new Phrase("Nombre"));
        h2.setBackgroundColor(azulClarito);
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPadding(8);

        table.addCell(h1);
        table.addCell(h2);

        // Filas
        for (Categoria c : categorias) {
            PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(c.getIdCategoria())));
            idCell.setPadding(6);

            PdfPCell nombreCell = new PdfPCell(new Phrase(c.getNombre()));
            nombreCell.setPadding(6);

            table.addCell(idCell);
            table.addCell(nombreCell);
        }

        document.add(table);
        document.close();
    }
}
