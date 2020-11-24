package com.mx.proyecto.controller;

import PdfResources.PdfGenerator;
//import PdfResources.PdfView2;
import com.itextpdf.text.DocumentException;
import com.mx.proyecto.dao.ConsultaFactura;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FacturasController {   
    @RequestMapping (value = "consultarFacturas.do")
    @ResponseBody
    public String consultarFacturas(@RequestParam(required = true) String anio, String mes, String dia, String horaInicio, String horaFin){System.out.println("entro al controlador");
        System.out.println("anio: " + anio);
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ConsultaFactura obj = (ConsultaFactura) ctx.getBean("ConsultaFactura");
        JSONObject retorno = new JSONObject();
        retorno.put("data", obj.selectInfoFacturas(anio, mes, dia, horaInicio, horaFin));
        System.out.println("va a salir del controlador");
        return retorno.toString();
    }
    
    
    @RequestMapping (value = "generarPdf.do")
    @ResponseBody    
    public String generarPdf(@RequestParam(required = false) String fc_factura) throws DocumentException, IOException{
        System.out.println("LLEGA: " + fc_factura);
        String nombreDelArchivo = null;
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ConsultaFactura obj = (ConsultaFactura) ctx.getBean("ConsultaFactura");
        //CONSULTAS PARA LLENAR FACTURA
        List<Map<String,Object>>consultaParteUno = obj.selectConsultaUnoFactura(fc_factura);
        List<Map<String,Object>>consultaParteDos = obj.selectConsultaDosFactura(fc_factura);
        List<Map<String,Object>> numeroConceptos = obj.nConceptos(fc_factura);
        List<Map<String,Object>>consultaParteTres = obj.selectConsultaTresFactura(fc_factura);
        List<Map<String,Object>>consultaParteCuatro = obj.selectConsultaCuatroFactura(fc_factura);
        PdfGenerator pdf = new PdfGenerator();
        try {
            nombreDelArchivo = pdf.buildPdf(consultaParteUno, numeroConceptos, consultaParteDos, consultaParteTres, consultaParteCuatro);
        } catch (DocumentException ex) {
            Logger.getLogger(FacturasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacturasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombreDelArchivo;
    }
    
    @RequestMapping(value = "generarPdf2.do", method = RequestMethod.GET)
    @ResponseBody    
    public ResponseEntity<byte[]> generarPdf2() throws IOException, DocumentException {
//    public ResponseEntity<byte[]> generarPdf2(HttpServletRequest request) throws IOException, DocumentException {
//    public ResponseEntity<byte[]> generarPdf2(@RequestParam(required = true) String fc_factura) throws IOException, DocumentException {
//        System.out.println(request.getContextPath());
        String fc_factura = "B000025424";
        System.out.println("LLEGO FC_FACTURA: " + fc_factura);
//        String pdfPath = "/PortalFacturasLubriagsa/src/main/webapp/pdfs/";  
        String pdfPath = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/";  
        String nombreDelArchivo = "";
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ConsultaFactura obj = (ConsultaFactura) ctx.getBean("ConsultaFactura");
        //CONSULTAS PARA LLENAR FACTURA
        List<Map<String,Object>>consultaParteUno = obj.selectConsultaUnoFactura(fc_factura);
        List<Map<String,Object>>consultaParteDos = obj.selectConsultaDosFactura(fc_factura);
        List<Map<String,Object>> numeroConceptos = obj.nConceptos(fc_factura);
        List<Map<String,Object>>consultaParteTres = obj.selectConsultaTresFactura(fc_factura);
        List<Map<String,Object>>consultaParteCuatro = obj.selectConsultaCuatroFactura(fc_factura);   
        PdfGenerator pdf = new PdfGenerator();        
        nombreDelArchivo = pdf.buildPdf(consultaParteUno, numeroConceptos, consultaParteDos, consultaParteTres, consultaParteCuatro);
        String rutaArchivo = pdfPath + nombreDelArchivo + ".pdf";
        File file = new File(rutaArchivo);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
//        headers.setContentDispositionFormData(nombreDelArchivo, nombreDelArchivo);
        headers.add("content-disposition", "inline;filename=" + nombreDelArchivo + ".pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
       
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(fileContent, headers, HttpStatus.OK);
        return response;
    }
//    @RequestMapping(value = "generarPdf2.do", method = RequestMethod.GET)
//    @ResponseBody    
//    public ResponseEntity<InputStreamResource>  generarPdf2() throws IOException, DocumentException {
//        String fc_factura = "B000025424";
//        String pdfPath = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/";  
//        String nombreDelArchivo = "";
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
//        ConsultaFactura obj = (ConsultaFactura) ctx.getBean("ConsultaFactura");
//        //CONSULTAS PARA LLENAR FACTURA
//        List<Map<String,Object>>consultaParteUno = obj.selectConsultaUnoFactura(fc_factura);
//        List<Map<String,Object>>consultaParteDos = obj.selectConsultaDosFactura(fc_factura);
//        List<Map<String,Object>> numeroConceptos = obj.nConceptos(fc_factura);
//        List<Map<String,Object>>consultaParteTres = obj.selectConsultaTresFactura(fc_factura);
//        List<Map<String,Object>>consultaParteCuatro = obj.selectConsultaCuatroFactura(fc_factura);   
//        PdfGenerator pdf = new PdfGenerator();        
//        nombreDelArchivo = pdf.buildPdf(consultaParteUno, numeroConceptos, consultaParteDos, consultaParteTres, consultaParteCuatro);
//        String rutaArchivo = pdfPath + nombreDelArchivo + ".pdf";
//        File file = new File(rutaArchivo);
//        
//        HttpHeaders respHeaders = new HttpHeaders();
//       respHeaders.setContentType(MediaType.APPLICATION_PDF);
//       respHeaders.setContentLength(file.length());
//       respHeaders.setContentDispositionFormData("attachment", nombreDelArchivo + ".pdf");
//
//       InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
//       return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
//    }
//    @RequestMapping(value = "generarPdf2.do", method = RequestMethod.GET)
//    @ResponseBody    
//    public HttpEntity<byte[]>  generarPdf2(@RequestParam(required = true) String fc_factura) throws IOException, DocumentException {
//        String pdfPath = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/";  
//        String nombreDelArchivo = "";
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
//        ConsultaFactura obj = (ConsultaFactura) ctx.getBean("ConsultaFactura");
//        //CONSULTAS PARA LLENAR FACTURA
//        List<Map<String,Object>>consultaParteUno = obj.selectConsultaUnoFactura(fc_factura);
//        List<Map<String,Object>>consultaParteDos = obj.selectConsultaDosFactura(fc_factura);
//        List<Map<String,Object>> numeroConceptos = obj.nConceptos(fc_factura);
//        List<Map<String,Object>>consultaParteTres = obj.selectConsultaTresFactura(fc_factura);
//        List<Map<String,Object>>consultaParteCuatro = obj.selectConsultaCuatroFactura(fc_factura);   
//        PdfGenerator pdf = new PdfGenerator();        
//        nombreDelArchivo = pdf.buildPdf(consultaParteUno, numeroConceptos, consultaParteDos, consultaParteTres, consultaParteCuatro);
//        String rutaArchivo = pdfPath + nombreDelArchivo + ".pdf";
//        File file = new File(rutaArchivo);
//        
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_PDF);
//        header.set(HttpHeaders.CONTENT_DISPOSITION,
//                       "attachment; filename=" + nombreDelArchivo.replace(" ", "_"));
//        header.setContentLength(file.length());
//        HttpEntity<byte[]>  entity= new HttpEntity(new FileSystemResource(file), header);
//        return entity;
//    }
    
    @RequestMapping (value = "borrarPdf.do")
    @ResponseBody    
    public String borrarPdf(@RequestParam(required = true) String fileName){
        System.out.println("LLEGA: " + fileName);
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ConsultaFactura obj = (ConsultaFactura) ctx.getBean("ConsultaFactura");       
        
        String pdfPath = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/";
        File pdf = new File(pdfPath + fileName + ".pdf");
        pdf.delete();
        System.out.println("Elimino: " + pdfPath + fileName + ".pdf");
        return "nada";
    }
}
