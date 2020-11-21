package com.mx.proyecto.controller;

import PdfResources.PdfGenerator;
//import PdfResources.PdfView2;
import com.itextpdf.text.DocumentException;
import com.mx.proyecto.dao.ConsultaFactura;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FacturasController {    
    
    @RequestMapping (value = "consultarFacturas.do")
    @ResponseBody
    public String consultarFacturas(){System.out.println("entro al controlador");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ConsultaFactura obj = (ConsultaFactura) ctx.getBean("ConsultaFactura");
        JSONObject retorno = new JSONObject();
        retorno.put("data", obj.selectInfoFacturas());
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
    
//    @RequestMapping (value = "borrarPdf.do")
//    @ResponseBody    
//    public String borrarPdf(@RequestParam(required = false) String fileName){
//        System.out.println("Eliminar llega: " + fileName);
//        String pdfPath = "C:\\Users\\erick\\Documents\\Acceso carpetas\\Practicas\\Portal clientes\\Portal de facturación\\spring proyect\\PortalFacturasLubriagsa\\src\\main\\webapp\\pdfs/";
//        File pdf = new File(pdfPath + fileName + ".pdf");
//        pdf.delete();
//        System.out.println("Elimino: " + pdfPath + fileName + ".pdf");
//        return "nada";
//    }
//    @RequestMapping(value = "generarPdf2.do", produces="application/json" ,method = RequestMethod.GET)
//    public ResponseEntity<InputStreamResource> generarPdf2(@RequestParam(required = false) String fc_factura)throws IOException {
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
//        try {
//            nombreDelArchivo = pdf.buildPdf(consultaParteUno, numeroConceptos, consultaParteDos, consultaParteTres, consultaParteCuatro);
//        } catch (DocumentException ex) {
//            Logger.getLogger(FacturasController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(FacturasController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        File file = new File(pdfPath + nombreDelArchivo + ".pdf");
//        System.out.println("FILE: " + pdfPath + nombreDelArchivo + ".pdf");
//        HttpHeaders respHeaders = new HttpHeaders();
//        respHeaders.setContentType(MediaType.valueOf("application/pdf"));
//        respHeaders.setContentLength(12345678);
//        respHeaders.setContentDispositionFormData("attachment", nombreDelArchivo);
//
//        InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
//        System.out.println("");
//        return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
//    }
    
//        HttpServletResponse arg1 = new HttpServletResponse() {
//        @Override
//        public void addCookie(Cookie cookie) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public boolean containsHeader(String string) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public String encodeURL(String string) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public String encodeRedirectURL(String string) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public String encodeUrl(String string) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public String encodeRedirectUrl(String string) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void sendError(int i, String string) throws IOException {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void sendError(int i) throws IOException {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void sendRedirect(String string) throws IOException {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void setDateHeader(String string, long l) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void addDateHeader(String string, long l) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void setHeader(String string, String string1) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void addHeader(String string, String string1) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void setIntHeader(String string, int i) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void addIntHeader(String string, int i) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void setStatus(int i) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void setStatus(int i, String string) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public String getCharacterEncoding() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public String getContentType() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public ServletOutputStream getOutputStream() throws IOException {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public PrintWriter getWriter() throws IOException {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void setCharacterEncoding(String string) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void setContentLength(int i) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void setContentType(String string) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void setBufferSize(int i) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public int getBufferSize() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void flushBuffer() throws IOException {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void resetBuffer() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public boolean isCommitted() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void reset() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void setLocale(Locale locale) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public Locale getLocale() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//    };
//    @RequestMapping (value = "generarPdf.do")
    @RequestMapping(value = "generarPdf2.do", method = RequestMethod.GET)
    @ResponseBody    
    public void  generarPdf2(@RequestParam(required = true) String fc_factura, HttpServletResponse arg1) throws IOException {
//        HttpServletResponse arg1 = null;
        
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
        
        //return new PdfView2(consultaParteUno, consultaParteDos, numeroConceptos, consultaParteTres, consultaParteCuatro);
//        try {        
//        String nombreFichero = pdfPath+nombreDelArchivo +".pdf";
//        String unPath = "";
//        arg1.setContentType("application/pdf");
//        arg1.setHeader("Content-Disposition", "attachment; filename=\""
//                + nombreFichero+ "\"");
//        InputStream is = new FileInputStream(unPath+nombreFichero);
//        IOUtils.copy(is, arg1.getOutputStream());
//        arg1.flushBuffer();                            
//        } catch (IOException ex) {
//            throw ex;
//        }  
//        return arg1;
//        return "";
    }
    
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
