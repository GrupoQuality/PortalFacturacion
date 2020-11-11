package com.mx.proyecto.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.json.*;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Rooter {
    @RequestMapping(value = "index.do")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();        
        mv.setViewName("login");        
        return mv;
    }
    
     @RequestMapping(value = "consulta.do")
    public ModelAndView lanzarConsultaFacturas() {
        ModelAndView mv = new ModelAndView();        
        mv.setViewName("consultaFacturas");        
        return mv;
    }
     @RequestMapping(value = "login.do")
    public ModelAndView lanzarLogin() {
        ModelAndView mv = new ModelAndView();        
        mv.setViewName("login");        
        return mv;
    }
    
     @RequestMapping(value = "home.do")
    public ModelAndView lanzarHome() {
        ModelAndView mv = new ModelAndView();        
        mv.setViewName("home");        
        return mv;
    }
}
