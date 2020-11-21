//
//package PdfResources;
//
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.draw.LineSeparator;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.time.Instant;
//
//import java.util.List;
//import java.util.Map;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.view.document.AbstractPdfView;
//public class PdfView extends AbstractPdfView{
//    List<Map<String, Object>> facturaParteUno;
//    List<Map<String, Object>> facturaParteDos;
//    List<Map<String, Object>> ​facturaParteTres;
//    List<Map<String, Object>> ​facturaParteCuatro;
//    List<Map<String, Object>> numeroConceptos;
//  final String fontsPath = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/fonts";
//        final String logo = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\img/logo-lubriagsa.png";
//        final String dest = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/";
////        final String dest = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/pdf.pdf";
//        
//        float afterSpacing = 5f;
//        float beforeSpacing = 5f;
//        BaseFont brandonBold;
//        BaseFont brandonMedium;
//        BaseFont brandonBlack;
//        BaseFont brandonLight;
//        BaseFont brandonRegular;
//        BaseFont brandonThin;
//        
//        
//        BaseColor azul = new BaseColor(0, 57, 88);
//        BaseColor gris = new BaseColor(159, 159, 159);
//        
//        Font fTitleTable = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.BOLD);
//        Font fContentTable = new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL);
//        int fontSize = 9;
//        Font fBold = FontFactory.getFont(fontsPath+"/Brandon_bld.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, fontSize, Font.NORMAL, azul);
//        Font fMedium = FontFactory.getFont(fontsPath+"/Brandon_med.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, fontSize, Font.NORMAL, azul);
//        Font fRegularGris = FontFactory.getFont(fontsPath+"/Brandon_reg.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, fontSize, Font.NORMAL, gris);
//        Font fBlack = FontFactory.getFont(fontsPath+"/Brandon_blk.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, fontSize, Font.NORMAL, azul);
//        Font fLight = FontFactory.getFont(fontsPath+"/Brandon_light.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, fontSize, Font.NORMAL, azul);
//        Font fRegular = FontFactory.getFont(fontsPath+"/Brandon_reg.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, fontSize, Font.NORMAL, azul);
//        Font fThin = FontFactory.getFont(fontsPath+"/Brandon_thin.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, fontSize, Font.NORMAL, azul);
//        Font fRegularGrisSmall = FontFactory.getFont(fontsPath+"/Brandon_reg.otf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 6, Font.NORMAL, gris);
//
//    public PdfView(List<Map<String, Object>> facturaParteUno, List<Map<String, Object>> facturaParteDos , List<Map<String, Object>> facturaParteTres , List<Map<String, Object>> facturaParteCuatro , List<Map<String, Object>> numeroConceptos) {
//        this.facturaParteUno = facturaParteUno;
//        this.facturaParteDos = facturaParteDos;
//        this.facturaParteTres = facturaParteTres;
//        this.facturaParteCuatro= facturaParteCuatro;
//        this.numeroConceptos = numeroConceptos;
//    }
//    @Override
//    protected void buildPdfDocument(Map<String, Object> model,
//            Document document,
//            PdfWriter writer,
//            HttpServletRequest request,
//            HttpServletResponse response) throws DocumentException, BadElementException, IOException {
////VARIABLES PARA LLENAR LA FACTURA           
//        String emRazonSocial = String.valueOf(facturaParteUno.get(0).get("Em_Razon_Social")),
//                emRFC = String.valueOf(facturaParteUno.get(0).get("Em_R_F_C")),
//                emCveRegimenFiscal = String.valueOf(facturaParteUno.get(0).get("Em_Cve_Regimen_Fiscal")),
//                sucursal = String.valueOf(facturaParteUno.get(0).get("Sc_Descripcion")),
//                scDireccion1 = String.valueOf(facturaParteUno.get(0).get("Sc_Direccion_1")),
//                scDireccion2 = String.valueOf(facturaParteUno.get(0).get("Sc_Direccion_2")),
//                scDireccion3 = String.valueOf(facturaParteUno.get(0).get("Sc_Direccion_3")),
//                telefono1 = String.valueOf(facturaParteUno.get(0).get("Sc_Telefono_1")),
//                telefono2 = String.valueOf(facturaParteUno.get(0).get("Sc_Telefono_2")),
//                telefono3 = String.valueOf(facturaParteUno.get(0).get("sc_telefono_3")),
//                folioFiscal = String.valueOf(facturaParteUno.get(0).get("Cd_Documento")),
//                nombre = String.valueOf(facturaParteUno.get(0).get("Cl_Razon_Social")),
//                RFC = String.valueOf(facturaParteUno.get(0).get("Cl_R_F_C")),
//                calle = String.valueOf(facturaParteUno.get(0).get("Cl_Direccion_1")),
//                colonia = String.valueOf(facturaParteUno.get(0).get("Cl_Direccion_2")),
//                municipio = String.valueOf(facturaParteUno.get(0).get("cl_ciudad")),
//                estado = String.valueOf(facturaParteUno.get(0).get("cl_estado")),
//                pais = String.valueOf(facturaParteUno.get(0).get("Cl_Pais")),
//                cp = String.valueOf(facturaParteUno.get(0).get("cl_direccion_3")),
//                vendedor = String.valueOf(facturaParteUno.get(0).get("Vn_Descripcion")),
//                noDeCertificadoDelCSD = String.valueOf(facturaParteUno.get(0).get("Cd_Numero_Serie_Certificado")),
//                lugarHoraFechaEmision = String.valueOf(facturaParteUno.get(0).get("Cd_Timbre_Fecha")),
//                cFactura = String.valueOf(facturaParteUno.get(0).get("Fc_Factura")),
//                moneda = String.valueOf(facturaParteUno.get(0).get("Mn_Cve_Moneda")),
//                usoCFDI = String.valueOf(facturaParteUno.get(0).get("Fc_Condicion_Venta")),
//                folio = String.valueOf(facturaParteUno.get(0).get("Fc_Folio")),
//                tipoCambio = String.valueOf(facturaParteUno.get(0).get("Fc_Tipo_Cambio")),
//                fecha = String.valueOf(facturaParteUno.get(0).get("Fc_Fecha"));
//
//        System.out.println("emRazon--> " + emRazonSocial);
//        //Crear imagen
//        Image image1 = Image.getInstance(logo);
//
//        Paragraph lubriagsa = new Paragraph();
//        lubriagsa.add(new Chunk(emRazonSocial + "\n", fBlack));
//        lubriagsa.add(new Chunk(emRFC, fBlack));
//        lubriagsa.setExtraParagraphSpace(0f);
//
//        PdfPTable tLubriagsa = new PdfPTable(1);
//        tLubriagsa.getDefaultCell().setBorderWidth(0f);
//        tLubriagsa.setWidthPercentage(100);
//        PdfPCell cLubriagsa = new PdfPCell(lubriagsa);
//        cLubriagsa.setBorderColor(BaseColor.WHITE);
//        tLubriagsa.addCell(cLubriagsa);
//        tLubriagsa.setSpacingAfter(afterSpacing);
//
//        Paragraph enter = new Paragraph();
//        enter.add("\n");
//
//        Paragraph factura = new Paragraph();
//        factura.add(new Chunk("FACTURA", fRegular));
//        factura.setAlignment(Element.ALIGN_RIGHT);
//        factura.setSpacingAfter(5f);
//        factura.setSpacingBefore(beforeSpacing);
//
//        Paragraph pFolioFiscal = new Paragraph();
//        pFolioFiscal.add(new Chunk("FOLIO FISCAL\n", fRegular));
//        pFolioFiscal.add(new Chunk(folioFiscal, fRegularGris));
//        pFolioFiscal.setSpacingAfter(afterSpacing);
//        pFolioFiscal.setSpacingBefore(beforeSpacing);
//        pFolioFiscal.setAlignment(Element.ALIGN_LEFT);
//
//        Paragraph pCuentas = new Paragraph();
//        pCuentas.add(new Chunk("CUENTAS BANCARIAS LUBRIAGSA\n", fRegular));
//        pCuentas.add(new Chunk("BBVA: CTA: 0452754177 CLAVE: 012320004527541774\n", fRegularGris));
//        pCuentas.add(new Chunk("BANAMEX: CTA: 3155487 CLAVE: 002320031554870530\n", fRegularGris));
//        pCuentas.add(new Chunk("BANORTE: CTA: 0602682747 CLAVE: 072320006026827474\n", fRegularGris));
//        pCuentas.setSpacingAfter(afterSpacing);
//        pCuentas.setSpacingBefore(beforeSpacing);
//        pCuentas.setAlignment(Element.ALIGN_RIGHT);
//
//        Paragraph pDatosCliente = new Paragraph();
//        pDatosCliente.add(new Chunk("DATOS CLIENTE", fRegular));
//        pDatosCliente.setSpacingAfter(afterSpacing);
//        pDatosCliente.setSpacingBefore(beforeSpacing);
//        pDatosCliente.setAlignment(Element.ALIGN_LEFT);
//
//        Paragraph observaciones = new Paragraph();
//        observaciones.add(new Chunk("OBSERVACIONES", fRegular));
//        observaciones.setSpacingAfter(afterSpacing * 2);
//        observaciones.setSpacingBefore(beforeSpacing);
//        observaciones.setAlignment(Element.ALIGN_LEFT);
//
//        Paragraph direccionLubriagsa = new Paragraph();
//        direccionLubriagsa.add(new Chunk(emCveRegimenFiscal + "\n"
//                + "Sucursal    " + sucursal + "\n"
//                + scDireccion1 + "\n"
//                + scDireccion2 + "\n"
//                + scDireccion3 + "\n"
//                + "Tel.    " + telefono1 + " / " + telefono2 + " / " + telefono3, fRegular));
//
//        long unixDate = 0;
//        unixDate = Instant.now().getEpochSecond();
//        System.out.println("HORA UNIX: " + unixDate);
//
////        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream(dest + unixDate + ".pdf"));
////        PdfWriter.getInstance(document, new FileOutputStream(dest));
//        document.open();
//        // Create a Simple table
//        PdfPTable table = new PdfPTable(3);
//        table.getDefaultCell().setBorderWidth(0f);
//        table.setWidthPercentage(100);
//        table.setHeaderRows(0);
//        PdfPCell cSucursal = new PdfPCell(direccionLubriagsa);
//        cSucursal.setBorderColor(BaseColor.WHITE);
//        cSucursal.setColspan(2);
////        cSucursal.setHorizontalAlignment(2);
//        table.addCell(cSucursal);
//        table.addCell(image1);
//
////        SEPARADOR
//        LineSeparator line = new LineSeparator();
//        line.setLineColor(azul);
//        line.setLineWidth(1.75f);
//
//        LineSeparator line2 = new LineSeparator();
//        line2.setLineColor(azul);
//        line2.setLineWidth(.5f);
//
//        PdfPTable tDatosCliente = new PdfPTable(2);
//        tDatosCliente.getDefaultCell().setBorderWidth(0f);
//        tDatosCliente.setWidthPercentage(100);
//
//        // Set First row as header
//        tDatosCliente.setHeaderRows(7);
//
//        tDatosCliente.addCell(generatePhrases("Nombre", nombre));
//        tDatosCliente.addCell(generatePhrases("No. de certificado del CSD", noDeCertificadoDelCSD));
//        tDatosCliente.addCell(generatePhrases("R.F.C.", RFC));
//        tDatosCliente.addCell(generatePhrases("Lugar, hora y fecha de emisión", lugarHoraFechaEmision));
//        tDatosCliente.addCell(generatePhrases("Calle", calle));
//        tDatosCliente.addCell(generatePhrases("Factura", cFactura));
//        tDatosCliente.addCell(generatePhrases("Colonia", colonia));
//        tDatosCliente.addCell(generatePhrases("Moneda", moneda));
//        tDatosCliente.addCell(generatePhrases("Municipio", municipio));
//        tDatosCliente.addCell(generatePhrases("Uso CFDI", usoCFDI));
//
//        tDatosCliente.addCell(paisEstadoCell(estado, pais));
//
//        tDatosCliente.addCell(generatePhrases("Folio", folio));
//        tDatosCliente.addCell(generatePhrases("C.P.", cp));
//        tDatosCliente.addCell(generatePhrases("Tipo de cambio", tipoCambio));
//        tDatosCliente.addCell(generatePhrases("Vendedor", vendedor));
//        tDatosCliente.addCell(generatePhrases("Fecha", fecha));
//
////            OBSERVACIONES TABLA 1
//        PdfPTable obsTabla1 = new PdfPTable(9);
//        obsTabla1.getDefaultCell().setBorderWidth(0f);
//        obsTabla1.setWidthPercentage(100);
//        // Set First row as header
//        obsTabla1.setHeaderRows(0);//Este numero es el numero de filas que agregaras -1 OJO DEBES AGREGAR TODAS LAS FILAS Y COLUMNAS PARA QUE JALE
//        // Add the data
//        obsTabla1.addCell(new Paragraph("Clave Sat", fRegular));
//        obsTabla1.addCell(new Paragraph("Clave", fRegular));
//        obsTabla1.addCell(new Paragraph("Cantidad", fRegular));
//        obsTabla1.addCell(new Paragraph("Clave Unidad", fRegular));
//        obsTabla1.addCell(new Paragraph("Unidad", fRegular));
//        obsTabla1.addCell(new Paragraph("Descripción", fRegular));
//        obsTabla1.addCell(new Paragraph("Precio unitario", fRegular));
//        obsTabla1.addCell(new Paragraph("Descuento", fRegular));
//        obsTabla1.addCell(new Paragraph("Importe", fRegular));
//
////        OBSERVACIONES TABLA2
//        int n = Integer.parseInt(String.valueOf(numeroConceptos.get(0).get("n")));
//        PdfPTable obsTabla2 = new PdfPTable(9);
//        obsTabla2.getDefaultCell().setBorderWidth(0f);
//        obsTabla2.setWidthPercentage(100);
//        // Set First row as header
//        obsTabla2.setHeaderRows(n - 1);//Este numero es el numero de filas que agregaras -1 OJO DEBES AGREGAR TODAS LAS FILAS Y COLUMNAS PARA QUE JALE
//        // Add the data
//        System.out.println("NUMERO DE CONCEPTOS: " + String.valueOf(numeroConceptos.get(0).get("n")));
//        for (int i = 0; i < n; i++) {
//            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Pr_Codigo_SAT")), fRegularGris));
//            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Pr_Descripcion_Corta")), fRegularGris));
//            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Fc_Cantidad_Control_1")), fRegularGris));
//            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Un_Cve_Unidad")), fRegularGris));
//            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Un_Descripcion")), fRegularGris));
//            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Pr_Descripcion")), fRegularGris));
//            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Fc_Precio_Lista")), fRegularGris));
//            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Fc_Descuento_Importe")), fRegularGris));
//            obsTabla2.addCell(new Paragraph(String.valueOf(facturaParteDos.get(i).get("Fc_Precio_Lista_Importe")), fRegularGris));
//        }
//
//        PdfPTable tFolioFiscalCuentas = new PdfPTable(2);
//        tFolioFiscalCuentas.getDefaultCell().setBorderWidth(0f);
//        tFolioFiscalCuentas.setWidthPercentage(100);
//        tFolioFiscalCuentas.addCell(pFolioFiscal);
//        tFolioFiscalCuentas.addCell(pCuentas);
//
//        PdfPTable tablaImportes = new PdfPTable(5);
//        tablaImportes.getDefaultCell().setBorderWidth(0f);
//        tablaImportes.setWidthPercentage(100);
//        tablaImportes.setHeaderRows(0);
//
////        VARIABLES INFORMACION PAGO Y SUMAS
//        String totalConLetra = String.valueOf(facturaParteTres.get(0).get("totalConLetra")),
//                metodo_pago = String.valueOf(facturaParteTres.get(0).get("metodo_pago")),
//                forma_pago = String.valueOf(facturaParteTres.get(0).get("formaPago")),
//                Cd_Numero_Cuenta_Pago = String.valueOf(facturaParteTres.get(0).get("Cd_Numero_Cuenta_Pago")),
//                suma = String.valueOf(facturaParteTres.get(0).get("suma")),
//                descuento = String.valueOf(facturaParteTres.get(0).get("descuento")),
//                subtotal = String.valueOf(facturaParteTres.get(0).get("subtotal")),
//                iva = String.valueOf(facturaParteTres.get(0).get("iva")),
//                total = String.valueOf(facturaParteTres.get(0).get("total"));
//
//        Paragraph p = new Paragraph(infoPago(totalConLetra, metodo_pago, forma_pago, Cd_Numero_Cuenta_Pago));
////        PdfPCell infoPago = new PdfPCell(infoPago(totalConLetra, metodo_pago, forma_pago, Cd_Numero_Cuenta_Pago));
//        PdfPCell infoPago = new PdfPCell(p);
//        infoPago.setBorderColor(BaseColor.WHITE);
//        infoPago.setColspan(3);
//        infoPago.setExtraParagraphSpace(3f);
//        tablaImportes.addCell(infoPago);
//
//        Phrase sum = sumas(suma, descuento, subtotal, iva, total);
//        PdfPCell sumas = new PdfPCell(sum);
//        sumas.setBorderColor(BaseColor.WHITE);
//        sumas.setColspan(2);
//        sumas.setHorizontalAlignment(2);
//        sumas.setExtraParagraphSpace(3f);
//        tablaImportes.addCell(sumas);
//
//        Paragraph aviso = new Paragraph("No se aceptan devoluciones en efectivo\n", fRegular);
//        aviso.setAlignment(1);
//
////        VARIABLES ULTIMA PARTE
//        String selloDigital = String.valueOf(facturaParteCuatro.get(0).get("selloDigital")),
//                selloSAT = String.valueOf(facturaParteCuatro.get(0).get("selloSAT")),
//                cadenaOriginal = String.valueOf(facturaParteCuatro.get(0).get("cadenaOriginal")),
//                certificado = String.valueOf(facturaParteCuatro.get(0).get("certificado")),
//                fFecha = String.valueOf(facturaParteCuatro.get(0).get("fecha")),
//                fDate = String.valueOf(facturaParteCuatro.get(0).get("date"));
//
//        Paragraph pSelloDigital = new Paragraph("Sello Digital del CFDI: \n", fRegular);
//        pSelloDigital.add(new Chunk(selloDigital, fRegularGrisSmall));
//        pSelloDigital.add(new Chunk("\nSello del SAT:\n"));
//        pSelloDigital.add(new Chunk(selloSAT, fRegularGrisSmall));
//
//        PdfPTable tSellos = new PdfPTable(1);
//        tSellos.getDefaultCell().setBorderWidth(0f);
//        tSellos.setWidthPercentage(100);
//        tSellos.setHeaderRows(0);
//        PdfPCell cSellos = new PdfPCell(pSelloDigital);
//        cSellos.setExtraParagraphSpace(afterSpacing);
//        cSellos.setBorder(0);
//        tSellos.addCell(cSellos);
//
//        Paragraph pCadenaOriginal = new Paragraph();
//        pCadenaOriginal.add(new Chunk("Cadena original del complemento de certificación del SAT:\n", fRegular));
//        pCadenaOriginal.add(new Chunk(cadenaOriginal, fRegularGrisSmall));
//
//        Paragraph pFirma = new Paragraph();
//        pFirma.add(new Chunk("_____________________________________\n", fRegularGrisSmall));
//        pFirma.add(new Chunk("FIRMA DE CONFORMIDAD\n", fRegularGrisSmall));
//
//        PdfPTable tQr = new PdfPTable(10);
//        tQr.getDefaultCell().setBorderWidth(0f);
//        tQr.setWidthPercentage(100);
//        tQr.setHeaderRows(0);
//        PdfPCell cQr = new PdfPCell(new Paragraph(""));
//        cQr.setColspan(1);
//        PdfPCell cCadenaOriginal = new PdfPCell(pCadenaOriginal);
//        cCadenaOriginal.setColspan(7);
//        cCadenaOriginal.setBorder(0);
//        PdfPCell cFirma = new PdfPCell(pFirma);
//        cFirma.setHorizontalAlignment(1);
//        cFirma.setPaddingTop(28f);
//        cFirma.setColspan(2);
//        cFirma.setBorder(0);
//        tQr.addCell(cQr);
//        tQr.addCell(cCadenaOriginal);
//        tQr.addCell(cFirma);
//
//        Paragraph pCertificado = new Paragraph();
//        pCertificado.add(new Chunk("\nNo de serie del certificado del SAT:    ", fRegular));
//        pCertificado.add(new Chunk(certificado + "\n", fRegularGris));
//        pCertificado.add(new Chunk("\"Este documento es una representación impresa de un CFDI\"\n", fRegular));
//
//        Paragraph pFechaHora = new Paragraph();
//        pFechaHora.add(new Chunk("\nFecha y hora de certificación:    ", fRegular));
//        pFechaHora.add(new Chunk(fFecha + "\n", fRegularGris));
//        pFechaHora.add(new Chunk("En caso de cheque devuelto se cargará el 20%", fRegular));
//
//        Paragraph pL1 = new Paragraph();
//        pL1.add(new Chunk("POR ESTE PAGARE RECONOZCO Y ME OBLIGO INCONDICIONALMENTE EL DIA:    ", fRegularGrisSmall));
//        pL1.add(new Chunk(fDate, fRegularGrisSmall));
//        pL1.add(new Chunk("\nA LA ORDEN DE " + emRazonSocial + " EN ESTA CIUDAD LA CANTIDAD DE    $" + total + "    " + totalConLetra, fRegularGrisSmall));
//        pL1.add(new Chunk("\nVALOR DE LA MERCANCIA QUE HE RECIBIDO A MI ENTERA SATISFACCION EN CASO DE MORA PAGARE(MOS) INTERESES A RAZON DE 5% MENSUAL ESTE PAGARE ES MERCANTIL Y ESTA REGIDO POR LA LEY GENERAL DE TITULOS Y OPERACIONES DE CREDITO EN SU ARTICULO 173 PARTE FINAL Y ARTICULOS CORRELATIVOS, POR NO SER PAGARE.", fRegularGrisSmall));
//
//        PdfPTable tLeyenda = new PdfPTable(1);
//        tLeyenda.getDefaultCell().setBorderWidth(0f);
//        tLeyenda.setWidthPercentage(100);
//        tLeyenda.setHeaderRows(0);
//        tLeyenda.addCell(pL1);
//
//        PdfPTable tFin = new PdfPTable(2);
//        tFin.getDefaultCell().setBorderWidth(0f);
//        tFin.setWidthPercentage(100);
//        tFin.setHeaderRows(0);
//        PdfPCell cNumeroCertificado = new PdfPCell(pCertificado);
//        cNumeroCertificado.setBorder(0);
//        cNumeroCertificado.setExtraParagraphSpace(2f);
//        PdfPCell cFechaHora = new PdfPCell(pFechaHora);
//        cFechaHora.setBorder(0);
//        cFechaHora.setExtraParagraphSpace(2f);
//        tFin.addCell(cNumeroCertificado);
//        tFin.addCell(cFechaHora);
//
//        PdfPTable tFooter = new PdfPTable(1);
//        tFooter.getDefaultCell().setBorderWidth(0f);
//        tFooter.setWidthPercentage(100);
//        tFooter.setHeaderRows(3);
//        tFooter.addCell(tSellos);
//        tFooter.addCell(tQr);
//        tFooter.addCell(tFin);
//        tFooter.addCell(tLeyenda);
//
//        document.add(tLubriagsa);
//        document.add(table);
//        document.add(factura);
//        document.add(line);
//        document.add(tFolioFiscalCuentas);
//        document.add(line2);
//        document.add(pDatosCliente);
//        document.add(tDatosCliente);
//        document.add(observaciones);
//        document.add(obsTabla1);
//        document.add(line2);
//        document.add(obsTabla2);
//        document.add(enter);
//        document.add(tablaImportes);
//        document.add(aviso);
//        document.add(tFooter);
//        document.add(enter);
//        document.close();
//        return unixDate + "";
//    }  
//    public PdfPCell generatePhrases(String title, String value){
//        PdfPCell cell = new PdfPCell();
//        Phrase ph = new Phrase();
//        ph.add(new Chunk(title+"    ",fRegular));
//        ph.add(new Chunk(value,fRegularGris));
//        cell.addElement(new Phrase(ph));
//        cell.setUseVariableBorders(true);
//        cell.setBorderColor(BaseColor.WHITE);
//        cell.setBorderColorBottom(azul);
//        return cell;        
//    }
//
//    public Phrase infoPago(String totalConLetra, String metodo_pago, String forma_pago, String Cd_Numero_Cuenta_Pago){
//        Phrase ph = new Phrase();
//        
//        ph.add(new Chunk(totalConLetra + "\n",fRegularGris));
//        
//        ph.add(new Chunk("Método de pago:    ",fRegular));
//        ph.add(new Chunk(metodo_pago + "\n", fRegularGris));
//
//        ph.add(new Chunk("Forma de pago:    ",fRegular));
//        ph.add(new Chunk(forma_pago + "\n", fRegularGris));
//
//        ph.add(new Chunk("Cuenta de pago:    ",fRegular));
//        ph.add(new Chunk(Cd_Numero_Cuenta_Pago + "\n", fRegularGris));
//
//        ph.add(new Chunk("Toda devolución y/o cambios de producto tienen una penalización del 20%.",fRegular));
//        return ph;        
//    }
//
//    public Phrase sumas(String suma, String descuento, String subtotal, String iva, String total) {
//        Phrase ph = new Phrase();
//        ph.add(new Chunk("Suma:    $",fRegular));
//        ph.add(new Chunk(suma + "\n", fRegularGris));
//
//        ph.add(new Chunk("Descuento:    $",fRegular));
//        ph.add(new Chunk(descuento + "\n", fRegularGris));
//
//        ph.add(new Chunk("Subtotal:    $",fRegular));
//        ph.add(new Chunk(subtotal + "\n\n", fRegularGris));
//
//        ph.add(new Chunk("IVA:    $",fRegular));
//        ph.add(new Chunk(iva + "\n", fRegularGris));
//
//        ph.add(new Chunk("Total:    $",fRegular));
//        ph.add(new Chunk(total + "\n", fRegularGris));
//        return ph;        
//    }
//    
//    public PdfPCell paisEstadoCell(String estado, String pais){
//        Phrase ph = new Phrase();
//        ph.add(new Chunk("Estado"+"    ",fRegular));
//        ph.add(new Chunk(estado,fRegularGris));
//        ph.add(new Chunk("    "+"País"+"    ",fRegular));
//        ph.add(new Chunk(pais ,fRegularGris));
//        PdfPCell cell = new PdfPCell();        
//        cell.addElement(new Phrase(ph));
//        cell.setUseVariableBorders(true);
//        cell.setBorderColor(BaseColor.WHITE);
//        cell.setBorderColorBottom(azul);
//        return cell;        
//    }
//    
//}
