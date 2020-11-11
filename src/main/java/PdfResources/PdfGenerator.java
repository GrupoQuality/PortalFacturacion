/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PdfResources;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.IOException;

public class PdfGenerator {
        final String fontsPath = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/fonts";
        final String logo = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\img/logo-lubriagsa.png";
        final String dest = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/pdf.pdf";
        
        BaseFont brandonBold;
        BaseFont brandonMedium;
        BaseFont brandonBlack;
        BaseFont brandonLight;
        BaseFont brandonRegular;
        BaseFont brandonThin;
        
        
        BaseColor azul = new BaseColor(0, 57, 88);
        BaseColor gris = new BaseColor(159, 159, 159);
        
        Font fTitleTable = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.BOLD);
        Font fContentTable = new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL);
        
        Font fBold = FontFactory.getFont(fontsPath+"/Brandon_bld.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.NORMAL, azul);
        Font fMedium = FontFactory.getFont(fontsPath+"/Brandon_med.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.NORMAL, azul);
        Font fRegularGris = FontFactory.getFont(fontsPath+"/Brandon_reg.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.NORMAL, gris);
        Font fBlack = FontFactory.getFont(fontsPath+"/Brandon_blk.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.NORMAL, azul);
        Font fLight = FontFactory.getFont(fontsPath+"/Brandon_light.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.NORMAL, azul);
        Font fRegular = FontFactory.getFont(fontsPath+"/Brandon_reg.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.NORMAL, azul);
        Font fThin = FontFactory.getFont(fontsPath+"/Brandon_thin.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.NORMAL, azul);
    
    public Paragraph probandoFonts(){
        Paragraph f = new Paragraph();
        f.add(new Chunk("Hola mundo (bold)\n", fBold));
        f.add(new Chunk("Hola mundo (medium)\n",fMedium));
        f.add(new Chunk("Hola mundo (black)\n",fBlack));
        f.add(new Chunk("Hola mundo (light)\n",fLight));
        f.add(new Chunk("Hola mundo (regular)\n",fRegular));
        f.add(new Chunk("Hola mundo (thin)\n",fThin));    
        f.add(new Chunk("HOLA MUNDO (bold)\n", fBold));
        f.add(new Chunk("HOLA MUNDO (medium)\n",fMedium));
        f.add(new Chunk("HOLA MUNDO (black)\n",fBlack));
        f.add(new Chunk("HOLA MUNDO (light)\n",fLight));
        f.add(new Chunk("HOLA MUNDO (regular)\n",fRegular));
        f.add(new Chunk("HOLA MUNDO (thin)\n",fThin));    
        return f;
    }
    
    public void buildPdf(String folioFiscal) throws DocumentException, BadElementException, IOException{
        folioFiscal = "1345324";//prueba
        float afterSpacing = 10f;
        float beforeSpacing = 10f;
//        FONTS

        //Crear imagen
        Image image1 = Image.getInstance(logo);
        
            Paragraph lubriagsa = new Paragraph();
            lubriagsa.add(new Chunk("LUBRIAGSA SA DE CV\n" +
            "LUB0004283C4", fBlack));
        
        Paragraph enter = new Paragraph();
        enter.add("\n");
        
        
        Paragraph factura = new Paragraph();
        factura.add(new Chunk("FACTURA",fRegular));
        factura.setAlignment(Element.ALIGN_RIGHT);
        factura.setSpacingAfter(5f);
        
        
        Paragraph pFolioFiscal = new Paragraph();
        pFolioFiscal.add(new Chunk("FOLIO FISCAL\n",fRegular));
        pFolioFiscal.add(new Chunk(folioFiscal,fRegularGris));
        pFolioFiscal.setSpacingAfter(afterSpacing);
        pFolioFiscal.setSpacingBefore(beforeSpacing);
        pFolioFiscal.setAlignment(Element.ALIGN_LEFT);
        
        Paragraph pDatosCliente = new Paragraph();
        pDatosCliente.add(new Chunk("DATOS CLIENTE",fRegular));
        pDatosCliente.setSpacingAfter(afterSpacing);
        pDatosCliente.setSpacingBefore(beforeSpacing);
        pDatosCliente.setAlignment(Element.ALIGN_LEFT);
        
        Paragraph observaciones = new Paragraph();
        observaciones.add(new Chunk("OBSERVACIONES",fRegular));
        observaciones.setSpacingAfter(afterSpacing);
        observaciones.setSpacingBefore(beforeSpacing);
        observaciones.setAlignment(Element.ALIGN_LEFT);
        
        
        Paragraph direccionLubriagsa = new Paragraph();
        direccionLubriagsa.add(new Chunk("601 – General de Ley Personas Morales\n" +
        "Avenida Lázaro Cárdenas\n" +
        "Col. Las Torres\n" +
        "Guadalajara C.P. 44920, Jalisco",fRegular));
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        // Create a Simple table
        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setBorderWidth(0f);        
        table.setWidthPercentage(100);
        // Set First row as header
        table.setHeaderRows(0);//Este numero es el numero de filas que agregaras -1 OJO DEBES AGREGAR TODAS LAS FILAS Y COLUMNAS PARA QUE JALE
        // Add the data
        table.addCell(direccionLubriagsa);
        table.addCell("");
        table.addCell(image1);
        
//        SEPARADOR
        LineSeparator line = new LineSeparator();
        line.setLineColor(azul);
        line.setLineWidth(1.75f);
        
        LineSeparator line2 = new LineSeparator();
        line2.setLineColor(azul);
        line2.setLineWidth(.5f);
        
        PdfPTable tDatosCliente = new PdfPTable(2);
        tDatosCliente.getDefaultCell().setBorderWidth(0f);        
        tDatosCliente.setWidthPercentage(100);
        
        // Set First row as header
        tDatosCliente.setHeaderRows(7);
        
        tDatosCliente.addCell(generatePhrases("Nombre", "TRACTO PARTES SA DE CV"));
        tDatosCliente.addCell(generatePhrases("No. de certificado del CSD", "0000AAABBBCCCDD1111"));
        tDatosCliente.addCell(generatePhrases("R.F.C.", "XAXX010101000"));
        tDatosCliente.addCell(generatePhrases("Lugar, hora y fecha de emisión", "XAXX010101000"));
        tDatosCliente.addCell(generatePhrases("Calle", "Nombre Calle"));
        tDatosCliente.addCell(generatePhrases("Factura", "Numerofactura"));
        tDatosCliente.addCell(generatePhrases("Colonia", "Centro"));
        tDatosCliente.addCell(generatePhrases("Moneda", "MXN"));
        tDatosCliente.addCell(generatePhrases("Municipio", "Zapopan"));
        tDatosCliente.addCell(generatePhrases("Uso CFDI", "Zapopan"));
        
        tDatosCliente.addCell(paisEstadoCell());
        
        tDatosCliente.addCell(generatePhrases("Folio", "1365461258"));
        tDatosCliente.addCell(generatePhrases("C.P.", "45100"));
        tDatosCliente.addCell(generatePhrases("Tipo de cambio", "45100"));
        tDatosCliente.addCell(generatePhrases("Vendedor", "Sin vendedor"));
        tDatosCliente.addCell(generatePhrases("Fecha", "09/11/2020"));
        
        
        
//            OBSERVACIONES TABLA 1
        PdfPTable obsTabla1 = new PdfPTable(8);
        obsTabla1.getDefaultCell().setBorderWidth(0f);        
        obsTabla1.setWidthPercentage(100);
        // Set First row as header
        obsTabla1.setHeaderRows(0);//Este numero es el numero de filas que agregaras -1 OJO DEBES AGREGAR TODAS LAS FILAS Y COLUMNAS PARA QUE JALE
        // Add the data
        obsTabla1.addCell(new Paragraph("Clave",fRegular));
        obsTabla1.addCell(new Paragraph("Cantidad",fRegular));
        obsTabla1.addCell(new Paragraph("Clave Unidad",fRegular));
        obsTabla1.addCell(new Paragraph("Unidad",fRegular));
        obsTabla1.addCell(new Paragraph("Descripción",fRegular));
        obsTabla1.addCell(new Paragraph("Precio unitario",fRegular));
        obsTabla1.addCell(new Paragraph("Descuento",fRegular));
        obsTabla1.addCell(new Paragraph("Importe",fRegular));
        
        
//        OBSERVACIONES TABLA2
        int n=3;
        PdfPTable obsTabla2 = new PdfPTable(8);
        obsTabla2.getDefaultCell().setBorderWidth(0f);        
        obsTabla2.setWidthPercentage(100);
        // Set First row as header
        obsTabla2.setHeaderRows(n-1);//Este numero es el numero de filas que agregaras -1 OJO DEBES AGREGAR TODAS LAS FILAS Y COLUMNAS PARA QUE JALE
        // Add the data
        for (int i = 0; i < n; i++) {            
            obsTabla2.addCell(new Paragraph("000000000000",fRegularGris));
            obsTabla2.addCell(new Paragraph("1.00",fRegularGris));
            obsTabla2.addCell(new Paragraph("H87",fRegularGris));
            obsTabla2.addCell(new Paragraph("Pieza",fRegularGris));
            obsTabla2.addCell(new Paragraph("Pequeña descripción del producto",fRegularGris));
            obsTabla2.addCell(new Paragraph("$500.00 ",fRegularGris));
            obsTabla2.addCell(new Paragraph("$0.00",fRegularGris));
            obsTabla2.addCell(new Paragraph("$500.00",fRegularGris));
        }
        
                
        document.add(lubriagsa);
        document.add(enter);
        document.add(table);
        document.add(enter);
        document.add(factura);
        document.add(line);
        document.add(pFolioFiscal);
        document.add(line2);
        document.add(pDatosCliente);
        document.add(tDatosCliente);
        document.add(enter);
        document.add(enter);
        document.add(line2);
        document.add(observaciones);
        
        document.add(obsTabla1);
        document.add(line2);
        document.add(obsTabla2);
        document.close();
        
    }    
    public PdfPCell generatePhrases(String title, String value){
        PdfPCell cell = new PdfPCell();
        Phrase ph = new Phrase();
        ph.add(new Chunk(title+"    ",fRegular));
        ph.add(new Chunk(value,fRegularGris));
        cell.addElement(new Phrase(ph));
        cell.setUseVariableBorders(true);
        cell.setBorderColor(BaseColor.WHITE);
        cell.setBorderColorBottom(azul);
        return cell;        
    }
    
    public PdfPCell paisEstadoCell(){
        Phrase ph = new Phrase();
        ph.add(new Chunk("Estado"+"    ",fRegular));
        ph.add(new Chunk("Jalisco",fRegularGris));
        ph.add(new Chunk("    "+"País"+"    ",fRegular));
        ph.add(new Chunk("México",fRegularGris));
        PdfPCell cell = new PdfPCell();        
        cell.addElement(new Phrase(ph));
        cell.setUseVariableBorders(true);
        cell.setBorderColor(BaseColor.WHITE);
        cell.setBorderColorBottom(azul);
        return cell;        
    }
    
    public void bordersBottom(Cell cell){
    }
}
