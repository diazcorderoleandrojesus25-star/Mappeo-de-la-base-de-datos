package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.MetodoPago;
import com.Jobxpress.Jobxpress.Service.MetodoPagoService;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/metodos_pago")
public class MetodoPagoAdminController {

    private final MetodoPagoService metodoPagoService;

    public MetodoPagoAdminController(MetodoPagoService metodoPagoService) {
        this.metodoPagoService = metodoPagoService;
    }

    @GetMapping("/lista")
    public String listar(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String formaPago,
            Model model
    ) {

        List<MetodoPago> resultados = metodoPagoService.findAll().stream()
                .filter(m -> id == null || m.getId().equals(id))
                .filter(m -> formaPago == null || formaPago.isEmpty() || m.getFormaPago().toLowerCase().contains(formaPago.toLowerCase()))
                .toList();

        model.addAttribute("metodos", resultados);
        model.addAttribute("filtroId", id);
        model.addAttribute("filtroFormaPago", formaPago);

        return "metodos_pago/lista";
    }

    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("metodo", new MetodoPago());
        return "metodos_pago/crear";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute MetodoPago metodo) {
        metodoPagoService.save(metodo);
        return "redirect:/admin/metodos_pago/lista";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        MetodoPago metodo = metodoPagoService.findById(id);

        if (metodo == null) {
            return "redirect:/admin/metodos_pago/lista?error=NoExiste";
        }

        model.addAttribute("metodo", metodo);
        return "metodos_pago/editar";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, @ModelAttribute MetodoPago metodo) {
        metodoPagoService.update(id, metodo);
        return "redirect:/admin/metodos_pago/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        metodoPagoService.delete(id);
        return "redirect:/admin/metodos_pago/lista";
    }

    // -------------------------
    // EXPORTAR PDF CON DISEÑO
    // -------------------------
    @GetMapping("/exportar-pdf")
    public void exportarPDF(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String formaPago,
            HttpServletResponse response
    ) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=metodos_pago.pdf");

        List<MetodoPago> metodos = metodoPagoService.findAll().stream()
                .filter(m -> id == null || m.getId().equals(id))
                .filter(m -> formaPago == null || formaPago.isEmpty() || m.getFormaPago().toLowerCase().contains(formaPago.toLowerCase()))
                .toList();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

       try {
    // Cargar el logo desde el classpath
    var logoURL = getClass().getResource("/static/images/logo.jpg");

    if (logoURL != null) {
        Image logo = Image.getInstance(logoURL);
        logo.scaleToFit(180, 180);
        logo.setAlignment(Image.ALIGN_CENTER);
        document.add(logo);
    } else {
        System.out.println("Logo no encontrado en classpath!");
    }
} catch (Exception e) {
    System.out.println("Error cargando el logo: " + e.getMessage());
}


        // ---------- TÍTULO ----------
        Font tituloFont = new Font(Font.HELVETICA, 22, Font.BOLD, new Color(33, 33, 33));
        Paragraph titulo = new Paragraph("Reporte de Métodos de Pago", tituloFont);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        titulo.setSpacingAfter(15);
        document.add(titulo);

        // ---------- FECHA ----------
        Font fechaFont = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.GRAY);
        Paragraph fecha = new Paragraph("Fecha: " + new Date(), fechaFont);
        fecha.setAlignment(Paragraph.ALIGN_RIGHT);
        fecha.setSpacingAfter(20);
        document.add(fecha);

        // ---------- TABLA ----------
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        // Colores
        Color azulSuave = new Color(0xBB, 0xDE, 0xFB);

        // Encabezado
        PdfPCell header1 = new PdfPCell(new Phrase("ID"));
        PdfPCell header2 = new PdfPCell(new Phrase("Forma de Pago"));

        header1.setBackgroundColor(azulSuave);
        header2.setBackgroundColor(azulSuave);

        header1.setPadding(8);
        header2.setPadding(8);

        table.addCell(header1);
        table.addCell(header2);

        // Celdas normales
        for (MetodoPago m : metodos) {
            PdfPCell c1 = new PdfPCell(new Phrase(String.valueOf(m.getId())));
            PdfPCell c2 = new PdfPCell(new Phrase(m.getFormaPago()));

            c1.setPadding(6);
            c2.setPadding(6);

            table.addCell(c1);
            table.addCell(c2);
        }

        document.add(table);
        document.close();
    }
}
