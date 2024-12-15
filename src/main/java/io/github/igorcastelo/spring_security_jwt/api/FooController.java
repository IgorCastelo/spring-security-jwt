package io.github.igorcastelo.spring_security_jwt.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {
    @GetMapping("/public")
    public ResponseEntity<String> publicRoute(){
        return ResponseEntity.ok("Public route ok!");
    }
    @GetMapping("/private")
    public ResponseEntity<String> privateRoute(Authentication authentication){
        return ResponseEntity.ok("Private route ok!"+ "Usuario conectado: " + authentication.getName());
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admineRoute(){
        return ResponseEntity.ok("Private route ok!"+ "Usuario conectado: ");
    }
}
