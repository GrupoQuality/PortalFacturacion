/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PdfResources;
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
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class PdfGenerator {
        final String fontsPath = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/fonts";
        final String logo = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\img/logo-lubriagsa.png";
        final String dest = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/";
//        final String dest = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/pdf.pdf";
        
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
    
    public String buildPdf(List<Map<String,Object>> facturaParteUno, List<Map<String,Object>> numeroConceptos, List<Map<String,Object>> facturaParteDos) throws DocumentException, BadElementException, IOException{System.out.println("entro a buildpdf");
        //VARIABLES PARA LLENAR LA FACTURA           
        String emRazonSocial = String.valueOf(facturaParteUno.get(0).get("Em_Razon_Social")),
               emRFC= String.valueOf(facturaParteUno.get(0).get("Em_R_F_C")),
               emCveRegimenFiscal = String.valueOf(facturaParteUno.get(0).get("Em_Cve_Regimen_Fiscal")),
               sucursal = String.valueOf(facturaParteUno.get(0).get("Sc_Descripcion")),
               scDireccion1 = String.valueOf(facturaParteUno.get(0).get("Sc_Direccion_1")),
               scDireccion2 = String.valueOf(facturaParteUno.get(0).get("Sc_Direccion_2")),
               scDireccion3 = String.valueOf(facturaParteUno.get(0).get("Sc_Direccion_3")),
               telefono1 = String.valueOf(facturaParteUno.get(0).get("Sc_Telefono_1")),
               telefono2 = String.valueOf(facturaParteUno.get(0).get("Sc_Telefono_2")),
               telefono3 = String.valueOf(facturaParteUno.get(0).get("sc_telefono_3")),
               folioFiscal = String.valueOf(facturaParteUno.get(0).get("Cd_Documento")),
               nombre = String.valueOf(facturaParteUno.get(0).get("Cl_Razon_Social")),
               RFC = String.valueOf(facturaParteUno.get(0).get("Cl_R_F_C")),
               calle = String.valueOf(facturaParteUno.get(0).get("Cl_Direccion_1")),
               colonia = String.valueOf(facturaParteUno.get(0).get("Cl_Direccion_2")),
               municipio = String.valueOf(facturaParteUno.get(0).get("cl_ciudad")),
               estado = String.valueOf(facturaParteUno.get(0).get("cl_estado")),
               pais = String.valueOf(facturaParteUno.get(0).get("Cl_Pais")),
               cp = String.valueOf(facturaParteUno.get(0).get("cl_direccion_3")),
               vendedor = String.valueOf(facturaParteUno.get(0).get("Vn_Descripcion")),
               noDeCertificadoDelCSD = String.valueOf(facturaParteUno.get(0).get("Cd_Numero_Serie_Certificado")),
               lugarHoraFechaEmision = String.valueOf(facturaParteUno.get(0).get("Cd_Timbre_Fecha")),
               cFactura = String.valueOf(facturaParteUno.get(0).get("Fc_Factura")),
               moneda = String.valueOf(facturaParteUno.get(0).get("Mn_Cve_Moneda")),
               usoCFDI = String.valueOf(facturaParteUno.get(0).get("Fc_Condicion_Venta")),
               folio = String.valueOf(facturaParteUno.get(0).get("Fc_Folio")),
               tipoCambio = String.valueOf(facturaParteUno.get(0).get("Fc_Tipo_Cambio")),
               fecha = String.valueOf(facturaParteUno.get(0).get("Fc_Fecha"));
        
        System.out.println("emRazon--> " + emRazonSocial);
             
               
    
//        String folioFiscal = "1345324";//prueba
//        int sucursal = 001;
        float afterSpacing = 10f;
        float beforeSpacing = 10f;
        
//        CONSULTAR LA BASE DE DATOS        
        
        //Crear imagen
        Image image1 = Image.getInstance(logo);
        
            Paragraph lubriagsa = new Paragraph();
            lubriagsa.add(new Chunk(emRazonSocial+"\n" +
            emRFC, fBlack));
        
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
        
        
        Paragraph pCuentas = new Paragraph();
        pCuentas.add(new Chunk("CUENTAS BANCARIAS LUBRIAGSA\n",fRegular));
        pCuentas.add(new Chunk("BBVA: CTA: 0452754177 CLAVE: 012320004527541774\n",fRegularGris));
        pCuentas.add(new Chunk("BANAMEX: CTA: 3155487 CLAVE: 002320031554870530\n",fRegularGris));
        pCuentas.add(new Chunk("BANORTE: CTA: 0602682747 CLAVE: 072320006026827474\n",fRegularGris));
        pCuentas.setSpacingAfter(afterSpacing);
        pCuentas.setSpacingBefore(beforeSpacing);
        pCuentas.setAlignment(Element.ALIGN_RIGHT);
        
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
        direccionLubriagsa.add(new Chunk(emCveRegimenFiscal+"\n" +
        "Sucursal    "+sucursal+"\n" +
        scDireccion1 + "\n" +
        scDireccion2 + "\n" +
        scDireccion3 + "\n" +
        "Tel.    "+telefono1+"/"+telefono2+"/"+telefono3+"/",fRegular));
        
        long unixDate = 0;
        unixDate = Instant.now().getEpochSecond();
        System.out.println("HORA UNIX: " + unixDate);
         
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest+unixDate+".pdf"));
//        PdfWriter.getInstance(document, new FileOutputStream(dest));
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
        
        tDatosCliente.addCell(generatePhrases("Nombre", nombre));
        tDatosCliente.addCell(generatePhrases("No. de certificado del CSD", noDeCertificadoDelCSD ));
        tDatosCliente.addCell(generatePhrases("R.F.C.", RFC));
        tDatosCliente.addCell(generatePhrases("Lugar, hora y fecha de emisión", lugarHoraFechaEmision));
        tDatosCliente.addCell(generatePhrases("Calle", calle));
        tDatosCliente.addCell(generatePhrases("Factura", cFactura));
        tDatosCliente.addCell(generatePhrases("Colonia", colonia));
        tDatosCliente.addCell(generatePhrases("Moneda", moneda));
        tDatosCliente.addCell(generatePhrases("Municipio", municipio));
        tDatosCliente.addCell(generatePhrases("Uso CFDI", usoCFDI));
        
        tDatosCliente.addCell(paisEstadoCell(estado, pais));
        
        tDatosCliente.addCell(generatePhrases("Folio", folio));
        tDatosCliente.addCell(generatePhrases("C.P.", cp));
        tDatosCliente.addCell(generatePhrases("Tipo de cambio", tipoCambio));
        tDatosCliente.addCell(generatePhrases("Vendedor", vendedor));
        tDatosCliente.addCell(generatePhrases("Fecha", fecha));
        
        
        
//            OBSERVACIONES TABLA 1
        PdfPTable obsTabla1 = new PdfPTable(9);
        obsTabla1.getDefaultCell().setBorderWidth(0f);        
        obsTabla1.setWidthPercentage(100);
        // Set First row as header
        obsTabla1.setHeaderRows(0);//Este numero es el numero de filas que agregaras -1 OJO DEBES AGREGAR TODAS LAS FILAS Y COLUMNAS PARA QUE JALE
        // Add the data
        obsTabla1.addCell(new Paragraph("Clave Sat",fRegular));
        obsTabla1.addCell(new Paragraph("Clave",fRegular));
        obsTabla1.addCell(new Paragraph("Cantidad",fRegular));
        obsTabla1.addCell(new Paragraph("Clave Unidad",fRegular));
        obsTabla1.addCell(new Paragraph("Unidad",fRegular));
        obsTabla1.addCell(new Paragraph("Descripción",fRegular));
        obsTabla1.addCell(new Paragraph("Precio unitario",fRegular));
        obsTabla1.addCell(new Paragraph("Descuento",fRegular));
        obsTabla1.addCell(new Paragraph("Importe",fRegular));
        
        
//        OBSERVACIONES TABLA2
        int n = Integer.parseInt(String.valueOf(numeroConceptos.get(0).get("n")));
        PdfPTable obsTabla2 = new PdfPTable(9);
        obsTabla2.getDefaultCell().setBorderWidth(0f);        
        obsTabla2.setWidthPercentage(100);
        // Set First row as header
        obsTabla2.setHeaderRows(n-1);//Este numero es el numero de filas que agregaras -1 OJO DEBES AGREGAR TODAS LAS FILAS Y COLUMNAS PARA QUE JALE
        // Add the data
        System.out.println("NUMERO DE CONCEPTOS: " + String.valueOf(numeroConceptos.get(0).get("n")));
        for (int i = 0; i < n; i++) {            
            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Pr_Codigo_SAT")),fRegularGris));
            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Pr_Descripcion_Corta")),fRegularGris));
            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Fc_Cantidad_Control_1")),fRegularGris));
            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Un_Cve_Unidad")),fRegularGris));
            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Un_Descripcion")),fRegularGris));
            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Pr_Descripcion")),fRegularGris));
            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Fc_Precio_Lista")),fRegularGris));
            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Fc_Descuento_Importe")),fRegularGris));
            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Fc_Precio_Lista_Importe")),fRegularGris));
        }
        
        PdfPTable tFolioFiscalCuentas = new PdfPTable(2);
        tFolioFiscalCuentas.getDefaultCell().setBorderWidth(0f);        
        tFolioFiscalCuentas.setWidthPercentage(100);
        tFolioFiscalCuentas.addCell(pFolioFiscal);
        tFolioFiscalCuentas.addCell(pCuentas);
        document.add(lubriagsa);
        document.add(enter);
        document.add(table);
        document.add(enter);
        document.add(factura);
        document.add(line);
        
        document.add(tFolioFiscalCuentas);
        
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
        return unixDate + "";
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
    
    public PdfPCell paisEstadoCell(String estado, String pais){
        Phrase ph = new Phrase();
        ph.add(new Chunk("Estado"+"    ",fRegular));
        ph.add(new Chunk(estado,fRegularGris));
        ph.add(new Chunk("    "+"País"+"    ",fRegular));
        ph.add(new Chunk(pais ,fRegularGris));
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
