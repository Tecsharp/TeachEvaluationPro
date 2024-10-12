package com.tecsharp.teachevaluationpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
public class TeachEvolutionPro {

	public static void main(String[] args) {
		SpringApplication.run(TeachEvolutionPro.class, args);
	}
	
	public void addViewControllers(ViewControllerRegistry registry) {
        // Mapea la URL "/error" a la vista de error personalizada
        registry.addViewController("/error").setViewName("error404");
    }

    @Bean
    public ErrorViewResolver customErrorViewResolver() {
        return (request, status, model) -> {
            // Personaliza la respuesta según el código de estado
            if (status == HttpStatus.NOT_FOUND) {
                return new ModelAndView("error404");
            }
            return null;
        };
    }

}
