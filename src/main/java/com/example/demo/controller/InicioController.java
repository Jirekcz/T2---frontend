package com.example.demo.controller;

import com.example.demo.viewmodel.InicioModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class InicioController {

    @GetMapping("/inicio")
    public String inicio(Model model) {
        InicioModel inicioModel = new InicioModel("00", "", "");
        model.addAttribute("inicioModel", inicioModel);
        return "inicio";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam("placa") String placa, Model model) {
        // Validar que los campos no esten vacios
        InicioModel inicioModel = new InicioModel("01", "ERROR: Debe completar los campos correctamente", "");
        model.addAttribute("inicioModel", inicioModel);
        return "inicio";
    }
}
