/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.proyecto.controller;

import PdfResources.PdfGenerator;
import com.itextpdf.text.DocumentException;
import com.mx.proyecto.dao.ConsultaFactura;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        PdfGenerator pdf = new PdfGenerator();
        try {
            pdf.buildPdf(fc_factura);
        } catch (DocumentException ex) {
            Logger.getLogger(FacturasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacturasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "nada";
    }
}
