package com.facens.appaula01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OlaMundoController {

    @GetMapping("/olaMundo")
    public String ola() {
        return "Bem-vindo à disciplina de Desenvolvimento Web Back-End 🚀";
    }
}
