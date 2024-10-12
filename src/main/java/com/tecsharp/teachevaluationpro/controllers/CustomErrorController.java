package com.tecsharp.teachevaluationpro.controllers;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Devuelve el nombre del archivo HTML de la página de error personalizada
        return "404";
    }

    public String getErrorPath() {
        return "/error";
    }
}
