package iva.puntacana.supercasas.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppUserController {

    @GetMapping("/welcome")
    @PreAuthorize("hasRole('ADMIN')")
    public String welcome() {
        return "Welcome to spring boot security Iva";
    }

    @GetMapping("/")
    public String home(){
        return "This the home text iva!";
    }

}
