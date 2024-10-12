package com.tecsharp.teachevaluationpro.controllers;

import javax.servlet.http.HttpServletRequest;

import com.tecsharp.teachevaluationpro.models.Init;
import com.tecsharp.teachevaluationpro.services.init.InitService;
import com.tecsharp.teachevaluationpro.services.user.UserService;
import com.tecsharp.teachevaluationpro.utils.Cons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.services.login.LoginService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @Autowired
    InitService initService;

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @GetMapping({"/login","/"})
    public String login(HttpServletRequest req, Model model) {

        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        if (userLogged != null) {

            return "redirect:/app/dashboard";
        } else {
            model.addAttribute("usernameTextField", "Nombre de usuario");
            model.addAttribute("passwordTextField", "Contraseña");
            model.addAttribute("welcome", "Bienvenido");
            model.addAttribute("welcomeText", "Por favor inicia sesión para acceder a la plataforma");
            model.addAttribute("title", "Inicio de sesión - TeachEvaluation");

            return "auth-login";
        }

    }

    @PostMapping({"/login/auth-login"})
    public String loginAuth(HttpServletRequest req, @RequestParam String username, String password){
        String user = (String) req.getSession().getAttribute("USERNAME");
        if (user == null) {
            try {

                User userLogin = loginService.getUserLogin(password, username);
                if (userLogin != null) {

                    req.getSession().setAttribute("ID", userLogin.getId());
                    req.getSession().setAttribute("USERNAME", userLogin.getUsername());
                    return "redirect:/app/dashboard";

                } else {
                    return "redirect:/login";
                }
            } catch (Exception e) {

            }
            return "redirect:/login";

        } else {
            return "redirect:/app/dashboard";
        }

    }

    @GetMapping("/logout-session")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

    /*
    REGISTRO DEL PRIMER ADMINISTRADOR
     */
    @GetMapping("/admin-register")
    public String adminRegisterView(Model model) {

        try {
            Init init = initService.getVerifyInitRegister(Cons.INIT_ID);

            if (init.getRegistered().getStatus() != 0) {

                logger.info("Ya hay un usuario Admin");
                return "redirect:/login";
            }
        } catch (Exception e) {
            logger.info("Falló el guardado del init");
        }

		/*
		ENVIA LOS DATOS A LA VISTA
		 */
        model.addAttribute("usernameTextField", Cons.USERNAME_TEXT_FIELD);
        model.addAttribute("passwordTextField", Cons.PASSWORD_TEXT_FIELD);
        model.addAttribute("welcome", Cons.WELCOME_TEXT);
        model.addAttribute("email", Cons.EMAIL_TEXT);
        model.addAttribute("welcomeText", Cons.WELCOME_REGISTER_TEXT);
        model.addAttribute("title", "Registrar usuario - TeachEvaluation");
        model.addAttribute("registerText", Cons.REGISTER_TEXT);


        return "/login/register-user";
    }

    @PostMapping({"/admin-register-user"})
    public String adminRegisterPost(@RequestParam String username, String password, String email){
        try {
			/*
		VALIDA EN LA BASE DE DATOS QUE NO SE HAYA EJECUTADO POR PRIMERA VEZ ESTE MÉTODO
		 */
            Init init = initService.getVerifyInitRegister(Cons.INIT_ID);

            if (init.getRegistered().getStatus() != 1) {
                userService.createAdminUser(username, password);
                logger.info("Se registró el usuario admin");
            }
        } catch (Exception e) {
            logger.info("Falló el guardado del init");
        }


        return "redirect:/app/dashboard";
    }

}
